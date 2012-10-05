package ssamot.mcts.ucb.optimisation;

import ssamot.mcts.MCTSContinuousNode;
import ssamot.mcts.MCTSNode;
import ssamot.mcts.StatisticsNode;
import ssamot.mcts.selectors.ucb.UCB;
import ssamot.utilities.SummaryStatistics;

public class HOOB extends UCB<MCTSContinuousNode> {

	private double UCB1_C = 1;
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
		System.err.println(this.vOne + ", " + denom);
		return (int) Math.ceil(num / denom);
	}

	private double getU(MCTSContinuousNode childNode) {
		double score = childNode.getStatistics().getMean()
				+ UCB1_C
				* (Math.sqrt((2.0 * Math.log(iterations))) / childNode
						.getStatistics().getN()) + vOne
				* Math.pow(rho, childNode.getDepth());
		return score;
	}

	@Override
	public double getNodeScore(MCTSContinuousNode fatherNode,
			MCTSContinuousNode childNode) {

		double score = 0;

		if (fatherNode.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
			score = (double) Integer.MAX_VALUE - twister.nextDouble();
		}

		else if (childNode.getStatistics().getN() != 0) {

			double U = getU(childNode);
			assert (fatherNode.getStatistics().getN() != 0);

			// System.out.println(fatherNode.getStatistics().getN() + "  " +
			// childNode.getStatistics().getN() + score);

			if (childNode.getChildren() == null) {
				score = (double) Integer.MAX_VALUE - twister.nextDouble();
				return score;
			}

			MCTSContinuousNode child1 = (MCTSContinuousNode) childNode
					.getChildren().get(0);
			MCTSContinuousNode child2 = (MCTSContinuousNode) childNode
					.getChildren().get(1);

			if (child1.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
				score = (double) Integer.MAX_VALUE - twister.nextDouble();
				return score;
			}

			if (child2.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
				score = (double) Integer.MAX_VALUE - twister.nextDouble();
				return score;
			}

			double B1 = getU(child1);
			double B2 = getU(child2);
			// System.out.println(U + " " + B1 + "   " + B2);
			double maxB = Math.max(B1, B2);
			score = Math.min(U, maxB);

		}

		else {
			score = (double) Integer.MAX_VALUE - twister.nextDouble();
			// System.err.println("random");
		}

		// SOO STUFF
		// if (childNode instanceof MCTSContinuousNode) {
		// MCTSContinuousNode castedNode = (MCTSContinuousNode) childNode;
		// if (!castedNode.isHasBeenSplit()) {
		// double n = fatherNode.getStatistics().getN();
		// double leaf_k = childNode.getStatistics().getN();
		// double log_n = Math.log(n);
		// n = n / (log_n * log_n * log_n);
		//
		// if (leaf_k > n) {
		// //System.out.println("spliting");
		// castedNode.split();
		// }
		//
		// }
		// }

		assert (score != Double.NaN);
		return score;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

}
