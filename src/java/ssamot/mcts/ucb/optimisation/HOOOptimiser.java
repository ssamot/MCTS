package ssamot.mcts.ucb.optimisation;

import ssamot.mcts.MCTS;
import ssamot.mcts.MCTSContinuousNode;
import ssamot.mcts.selectors.ChanceProportional;
import ssamot.mcts.selectors.ucb.UCBActionSelector;

public class HOOOptimiser extends MCTS<MCTSContinuousNode> {

	private HOOTruncatedBackpropagator bp;

	public HOOOptimiser(ContinuousProblem func, int dimension, int iterations,
			double min, double max, int splitPoints) {
		super();
		setActionSelector(new UCBActionSelector());
		bp = new HOOTruncatedBackpropagator(func, dimension);
		setBackpropagator(bp);
		setChanceNodeSelector(new ChanceProportional());
		HOOB hoob = new HOOB(dimension, iterations);
		setDeterministicNodeSelector(hoob);

		double[] minA = new double[dimension];
		double[] maxA = new double[dimension];

		for (int i = 0; i < minA.length; i++) {
			minA[i] = min;
			maxA[i] = max;
		}

		System.out.println("maxDepth = " + hoob.getMaxDepth());
		MCTSContinuousNode rootNode = new MCTSContinuousNode(minA, maxA,
				splitPoints, -1, 0, hoob.getMaxDepth());
		rootNode.split();
		rootNode.contId = "root";
		setRootNode(rootNode);

	}
	
	public double getBestValue() {
		return bp.getBestValue();
	}

	public double[] getBestSample() {
		return bp.getBestSample();
	}

}
