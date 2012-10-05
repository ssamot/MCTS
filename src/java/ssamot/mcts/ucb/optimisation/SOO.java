package ssamot.mcts.ucb.optimisation;

import ssamot.mcts.MCTSContinuousNode;
import ssamot.mcts.StatisticsNode;
import ssamot.mcts.selectors.ucb.UCB;

public class SOO extends UCB {

	private double UCB1_C = 1;
	private double UCB_MINIMUM_ITERATIONS = 1;

	@Override
	public double getNodeScore(StatisticsNode fatherNode, StatisticsNode childNode) {

		double score = 0;

		if (fatherNode.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
			score = (double) Integer.MAX_VALUE - twister.nextDouble();
		}

		else if (childNode.getStatistics().getN() != 0) {

			score = childNode.getStatistics().getMean()
					+ UCB1_C
					* Math.sqrt((2.0 * Math.log(fatherNode.getStatistics()
							.getN())) / childNode.getStatistics().getN());
			assert (fatherNode.getStatistics().getN() != 0);

			// System.out.println(fatherNode.getStatistics().getN() + "  " +
			// childNode.getStatistics().getN() + score);
		}

		else {
			score = (double) Integer.MAX_VALUE - twister.nextDouble();
			// System.err.println("random");
		}

		if (childNode instanceof MCTSContinuousNode) {
			MCTSContinuousNode castedNode = (MCTSContinuousNode) childNode;
			if (!castedNode.isHasBeenSplit()) {
				double n = fatherNode.getStatistics().getN();
				double leaf_k = childNode.getStatistics().getN();
				double log_n = Math.log(n);
				n = n / (log_n * log_n * log_n);

				if (leaf_k > n) {
					//System.out.println("spliting");
					castedNode.split();
				}

			}
		}

		assert (score != Double.NaN);
		return score;
	}

}
