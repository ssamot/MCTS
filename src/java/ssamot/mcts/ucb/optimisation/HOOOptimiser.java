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

import ssamot.mcts.MCTS;
import ssamot.mcts.selectors.ChanceProportional;
import ssamot.mcts.selectors.ucb.UCBActionSelector;

public class HOOOptimiser extends MCTS<MCTSContinuousNode> {

	private HOOTruncatedBackpropagator bp;
	private HOOB hoob;
	private double min;
	private double max;
	private double gamma;

	public HOOOptimiser(ContinuousProblem func, int dimension, int iterations,
			double min, double max, double gamma) {
		super();
		
		this.min = min;
		this.max = max;
		this.gamma = gamma;
		
		hoob = new HOOB(dimension, iterations);

		
		setActionSelector(new UCBActionSelector());
		bp = new HOOTruncatedBackpropagator(iterations, func, dimension,hoob);
		setBackpropagator(bp);
		setChanceNodeSelector(new ChanceProportional());
		setDeterministicNodeSelector(hoob);

		double[] minA = new double[dimension];
		double[] maxA = new double[dimension];

		for (int i = 0; i < minA.length; i++) {
			minA[i] = min;
			maxA[i] = max;
		}

		//System.out.println("maxDepth = " + hoob.getMaxDepth());
		MCTSContinuousNode rootNode = new MCTSContinuousNode(minA, maxA,
				2, -1, 0, hoob.getMaxDepth(), gamma);
		rootNode.split();
		rootNode.contId = "root";
		setRootNode(rootNode);

	}
	
	public double getBestValue() {
		return bp.getBestValue();
	}

	public double[] getBestRootSample() {
		double[][] samples = bp.getSamples();
		double[] rewards = bp.getRewards();
		int dimensions = hoob.getDimensions();
	    int iterations = hoob.getIterations();
	    
		MDPRootActionReplayer mr = new MDPRootActionReplayer();
		double[] score = mr.replay(samples, rewards, dimensions, iterations, min, max, gamma);
		return score;
	}
	
	public double[] getBestSample() {
		return bp.getBestSample();
	}

}
