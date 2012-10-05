package ssamot.mcts.ucb.optimisation;

import java.util.List;

import ssamot.mcts.MCTSContinuousNode;
import ssamot.mcts.StatisticsNode;

public class UCBNodeExplorer {

	StatisticsNode node;

	public UCBNodeExplorer(StatisticsNode node) {
		super();
		this.node = node;
	}

	public void generatePath(StatisticsNode node, int position, double result[]) {

		if (node.isLeaf()) {
			return;
		}

		List<StatisticsNode> children = node.getChildren();

		// if(children.size() == 0 ){
		// return rootNode.getMove();
		// }

		double max = Integer.MIN_VALUE;
		int index = -1;
		// System.out.println(max);
		StatisticsNode cNode = null;

		for (int i = 0; i < children.size(); i++) {
			cNode = children.get(i);

			double cVal = -1;

			cVal = cNode.getStatistics().getMean();

			if (cVal >= max) {
				index = i;
				max = cVal;
			}
		}

		if (index == -1) {
			return;
		}
		StatisticsNode best = children.get(index);

		if (best instanceof MCTSContinuousNode) {

			MCTSContinuousNode ccNode = (MCTSContinuousNode) best;
			// if(!ccNode.isHasBeenSplit()) {

			// if (depth == 0) {
			//
			// } else {
			// System.out.print(",");
			// }
			// System.out.println(ccNode.getContinousAction() + " " +
			// ccNode.getMin() + " " + ccNode.getMax() + " test");

			if (!ccNode.isHasBeenSplit()) {
				// System.out.print(ccNode.getContinousAction() + " " +
				// ccNode.getMin() + " " + ccNode.getMax() + " test");

				// }
				result[position] = ccNode.getContinousAction();
				position++;
				// System.out.println( ccNode.contId);
			}
		} else {
			// System.out.print(cNode.getAction());
		}
		// System.err.println(cVal);

		generatePath(best, position, result);

	}

	public double[] generatePath(int dimensions) {
		System.out.println("");
		double[] result = new double[dimensions];
		generatePath(node, 0, result);
		System.out.println("");
		return result;

	}

}
