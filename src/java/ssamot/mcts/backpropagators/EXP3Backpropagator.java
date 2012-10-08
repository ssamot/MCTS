package ssamot.mcts.backpropagators;

import java.util.List;

import ssamot.mcts.selectors.exp3.EXP3Node;

public class EXP3Backpropagator<T extends EXP3Node>implements Backpropagator<T> {

	@Override
	public void backpropagate(List<T> nodes, List<Double> reward) {
		// TODO Auto-generated method stub
		int nodeSize = nodes.size();

		for (int i = nodeSize - 1; i >= 0; i--) {

			T node = nodes.get(i);
			int id = node.getRewardId();
			if (node.getRewardId() != 0) {
				double value = (reward.get(id));
				double probability = node.getProbability();
				double nodeReward = node.getReward();
				nodeReward = Math.exp(value/probability);
				node.setReward(nodeReward);
			}

		}
	}

}
