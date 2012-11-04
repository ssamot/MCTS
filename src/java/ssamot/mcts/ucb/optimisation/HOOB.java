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

import ssamot.mcts.selectors.ucb.UCB;

public class HOOB extends UCB<MCTSContinuousNode> {

	private double UCB1_C = 1.0;
	private double UCB_MINIMUM_ITERATIONS = 1;
	private double alpha = 2;
	// private

	private int dimension;
	private int iterations;
	private double rho;
	private double vOne;
	private int maxDepth;

	public HOOB(int dimension, int iterations) {
		super();
		this.dimension = dimension;
		this.rho = Math.pow(2, (-alpha / dimension));
		this.vOne = Math.pow(Math.sqrt(dimension / 2), alpha);
		this.iterations = iterations;
		this.maxDepth = calculateMaximumDepth();

	}

	public int calculateMaximumDepth() {
		double num = Math.log(iterations / 2.0) - Math.log(1.0 / vOne);
		// if(1.0/(vOne*vOne) <= iterations) {
		// throw new RuntimeException("You need more iterations");
		// }

		if (vOne == 0) {
			// just depth of one, keep on going;
			return 100;
		}

		double denom = Math.log(1 / rho);
		// System.err.println(this.vOne + ", " + denom);
		return (int) Math.ceil(num / denom);
	}

	public double getU(MCTSContinuousNode childNode) {

		if (childNode.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
			return (double) Integer.MAX_VALUE - twister.nextDouble();
		}

		double score = childNode.getStatistics().getMean()
				+ UCB1_C
				* Math.sqrt((2.0 * Math.log(iterations))
						/ childNode.getStatistics().getN()) + vOne
				* Math.pow(rho, childNode.getDepth());
		return score;
	}

	public double getU(MCTSContinuousNode fatherNode,
			MCTSContinuousNode childNode) {

		if (childNode.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
			return (double) Integer.MAX_VALUE - twister.nextDouble();
		}

		double score = childNode.getStatistics().getMean()
				+ UCB1_C
				* (Math.sqrt((2.0 * Math.log(fatherNode.getStatistics().getN()))) / childNode
						.getStatistics().getN()) + vOne
				* Math.pow(rho, childNode.getDepth());
		return score;
	}

	@Override
	public double getNodeScore(MCTSContinuousNode fatherNode,
			MCTSContinuousNode childNode) {
		// System.out.println("test");
		double score = 0;
		score = childNode.getB();
		//
		// if (fatherNode.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
		// score = (double) Integer.MAX_VALUE - twister.nextDouble();
		// }
		//
		// else if (childNode.getStatistics().getN() != 0) {
		//
		// double U = getU(fatherNode, childNode);
		// assert (fatherNode.getStatistics().getN() != 0);
		//
		// // System.out.println(fatherNode.getStatistics().getN() + "  " +
		// childNode.getStatistics().getN() + score);

		// if (childNode.getChildren() == null) {
		// score = (double) Integer.MAX_VALUE - twister.nextDouble();
		// return score;
		// }
		//
		// MCTSContinuousNode child1 = (MCTSContinuousNode) childNode
		// .getChildren().get(0);
		// MCTSContinuousNode child2 = (MCTSContinuousNode) childNode
		// .getChildren().get(1);
		//
		// if (child1.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
		// score = (double) Integer.MAX_VALUE - twister.nextDouble();
		// return score;
		// }
		//
		// if (child2.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
		// score = (double) Integer.MAX_VALUE - twister.nextDouble();
		// return score;
		// }
		//
		// double B1 = getU(childNode, child1);
		// double B2 = getU(childNode,child2);
		// // System.out.println(U + " " + B1 + "   " + B2);
		// double maxB = Math.max(B1, B2);
		// score = Math.min(U, maxB);
		//
		// }
		//
		// else {
		// score = (double) Integer.MAX_VALUE - twister.nextDouble();
		// // System.err.println("random");
		// }
		//
		assert (score != Double.NaN);
		return score;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

}
