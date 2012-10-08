package ssamot.mcts.selectors.ucb;

import ssamot.mcts.StatisticsNode;
import ssamot.mcts.selectors.Selector;
import ssamot.utilities.MersenneTwister;

public abstract class UCB<T extends StatisticsNode> implements Selector<T> {

	protected MersenneTwister twister = new MersenneTwister();
	
	@Override
	public T selectChild(T node) {

		double max = Double.NEGATIVE_INFINITY;
		StatisticsNode maxChild = null;
		for (StatisticsNode childNode : node.getChildren()) {

			double childScore = getNodeScore(node, (T) childNode);
			//System.out.println(node);
			if (childScore >= max) {
				maxChild = childNode;
				max = childScore;
			}

		}

		if(maxChild == null) {
			for (StatisticsNode childNode : node.getChildren()) {

				double childScore = getNodeScore(node, (T) childNode);
				System.out.println(node + " " + childScore);
				

			}
		}
		
		assert(maxChild!=null);
		return (T) maxChild;

	}

	public abstract double getNodeScore(T fatherNode, T childNode);

}
