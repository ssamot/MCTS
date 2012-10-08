package ssamot.mcts.selectors.ucb;

import ssamot.mcts.MCTSNode;
import ssamot.mcts.StatisticsNode;

public class UCBTUNED extends UCB {

	// private double devisor = 10;

	
	private double UCB_MINIMUM_ITERATIONS = 100;
	
	@Override
	public double getNodeScore(StatisticsNode fatherNode, StatisticsNode childNode) {

		double score = 0;

		if (fatherNode.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
			score = (double) Integer.MAX_VALUE - twister.nextDouble();
		} else if (childNode.getStatistics().getN() != 0) {

			double absMax = 1;

			// if(absMax > Math.abs(childNode.getStatistics().getMin())) {
			// absMax = Math.abs(childNode.getStatistics().getMin());
			// }

			double mean = childNode.getStatistics().getMean() / absMax;
			double sd = childNode.getStatistics().getStandardDeviation()
					/ absMax;

			double nb = fatherNode.getStatistics().getN();
			double V = sd
					+ Math.sqrt(2.0 * Math.log(nb)
							/ (childNode.getStatistics().getN()));

			// System.out.println(childNode.getStatistics().getStandardDeviation());
			score = mean
					+ Math.sqrt((Math.log(nb) / childNode.getStatistics()
							.getN()) * Math.min(1.0 / 4.0, V));

		}

		else {
			score = (double) Integer.MAX_VALUE - twister.nextDouble();
		}

		return score;

	}

}
