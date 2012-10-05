package ssamot.mcts.backpropagators;

import java.util.List;

import ssamot.mcts.StatisticsNode;

public class UCBBackpropagator<T extends StatisticsNode>implements Backpropagator<T> {

	@Override
	public void backpropagate(List<T> nodes, List<Double> reward) {
		// TODO Auto-generated method stub
		//System.exit(0);
		//System.out.println(reward);
		int nodeSize = nodes.size();

		for (int i = nodeSize - 1; i >= 0; i--) {

			T node = nodes.get(i);
			int id = node.getRewardId();
			
			if (id > 0) {
				double value = (reward.get(id));
				//System.err.println(id + " " + value);
				node.getStatistics().addValue(value);
				
			}
			else {
				//root or random nodes
				node.getStatistics().addValue(1.0);
			}

		}
	}

}
