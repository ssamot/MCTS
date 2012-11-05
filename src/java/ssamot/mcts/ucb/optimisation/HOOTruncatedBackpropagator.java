/*
 *
 * *** BEGIN LICENSE
 *  Copyright (C) 2012 Spyridon Samothrakis spyridon.samothrakis@gmail.com
 *  This program is free software: you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 3, as published
 *  by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranties of
 *  MERCHANTABILITY, SATISFACTORY QUALITY, or FITNESS FOR A PARTICULAR
 *  PURPOSE.  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program.  If not, see <http://www.gnu.org/licenses/>.
 * *** END LICENSE
 *
 */

package ssamot.mcts.ucb.optimisation;

import java.util.List;

import ssamot.mcts.MCTSNode;
import ssamot.mcts.StatisticsNode;
import ssamot.mcts.backpropagators.Backpropagator;

public class HOOTruncatedBackpropagator implements
		Backpropagator<MCTSContinuousNode> {

	ContinuousProblem function;
	private int length;

	double bestValue = Double.NEGATIVE_INFINITY;
	double[] bestSample = null;
	HOOB hoob = null;
	double[][] samples;
	double[] rewards;
	private int iteration;

	// boolean once = true;

	public HOOTruncatedBackpropagator( int iterations,
			ContinuousProblem function, int length, HOOB hoob) {
		super();
		this.function = function;
		this.length = length;
		this.hoob = hoob;
		samples = new double[iterations][length];
		rewards = new double[iterations];
		this.iteration = 0;
	}

	@Override
	public void backpropagate(List<MCTSContinuousNode> nodes,
			List<Double> reward) {
		// TODO Auto-generated method stub
		// System.out.println(reward);
		int nodeSize = nodes.size();

		double[] funcVal = new double[length];

		int j = 0;

		funcVal = nodes.get(nodes.size() - 1).sampleAction();

		double value = function.evaluate(funcVal);

		nodes.get(nodes.size() - 1).addSample(funcVal, value);

		if (value > bestValue) {
			bestValue = value;
			bestSample = funcVal;
			// System.out.println(bestValue);

		}

		samples[iteration] = funcVal.clone();
		rewards[iteration] = value;
		iteration++;
		//System.out.println(iteration + "iteration");
		// System.out.println(Arrays.toString(funcVal) + length + " " + value);

		for (int i = nodeSize - 1; i >= 0; i--) {

			MCTSContinuousNode node = nodes.get(i);
			int id = node.getRewardId();
			// System.out.println(i + " " + (nodeSize -2));
			if (id > 0) {
				node.getStatistics().addValue(value);
			} else {
				// root or random nodes
				node.getStatistics().addValue(1.0);
			}

			// if we are at the father of the last Node;
			if (i == nodeSize - 2) {
				// System.out.println(i);
				// System.out.println(node.getChildren().get(0));
				double B1 = hoob.getU((MCTSContinuousNode) node.getChildren()
						.get(0));
				double B2 = hoob.getU((MCTSContinuousNode) node.getChildren()
						.get(1));
				double U = hoob.getU(node);
				double maxB = Math.max(B1, B2);
				double B = Math.min(U, maxB);
				node.setB(B);
			}

			if (i < nodeSize - 2) {
				double B1 = ((MCTSContinuousNode) node.getChildren().get(0))
						.getB();
				double B2 = ((MCTSContinuousNode) node.getChildren().get(1))
						.getB();
				double U = hoob.getU(node);
				double maxB = Math.max(B1, B2);

				double B = Math.min(U, maxB);
				// System.out.println(B);
				node.setB(B);
			}

		}

		// try splitting the last node
		nodes.get(nodes.size() - 1).split();
		// System.out.println("==============");
	}

	public double getBestValue() {
		return bestValue;
	}

	public double[] getBestSample() {
		return bestSample;
	}

	public double[][] getSamples() {
		return samples;
	}

	public void setSamples(double[][] samples) {
		this.samples = samples;
	}

	public double[] getRewards() {
		return rewards;
	}

	public void setRewards(double[] rewards) {
		this.rewards = rewards;
	}

}
