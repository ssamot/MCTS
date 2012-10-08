package ssamot.mcts.ucb.optimisation;

import java.util.List;

import ssamot.mcts.MCTSContinuousNode;
import ssamot.mcts.StatisticsNode;
import ssamot.mcts.backpropagators.Backpropagator;

public class UCBOptimisingBackpropagator<T extends StatisticsNode> implements
		Backpropagator<T> {

	ContinuousProblem function;
	private int length;
	
	//boolean once = true;

	public UCBOptimisingBackpropagator(ContinuousProblem function, int length) {
		super();
		this.function = function;
		this.length = length;
	}

	@Override
	public void backpropagate(List<T> nodes, List<Double> reward) {
		// TODO Auto-generated method stub
		// System.out.println(reward);
		int nodeSize = nodes.size();

		double[] funcVal = new double[length];

		int j = 0;
		for (int i = 0; i < nodes.size(); i++) {
			T node = nodes.get(i);
			assert (i < nodes.size());
			if (node instanceof MCTSContinuousNode) {
				MCTSContinuousNode castedNode = (MCTSContinuousNode) node;
				// System.out.println(castedNode.isHasBeenSplit());
				if (!castedNode.isHasBeenSplit()) {
					funcVal[j] = castedNode.getContinousAction();
					j++;
				}
			}
		}

		double value = function.evaluate(funcVal);
		//System.out.println(Arrays.toString(funcVal) + length + " " + value);

		for (int i = nodeSize - 1; i >= 0; i--) {

			T node = nodes.get(i);
			int id = node.getRewardId();
			
			if (id > 0) {

				// System.err.println(id + " " + value);
				node.getStatistics().addValue(value);
				if (node instanceof MCTSContinuousNode) {
					MCTSContinuousNode castedNode = (MCTSContinuousNode) node;
					//if (castedNode.contId.equals("1")) {
					//System.out.println(node.getAction() + " "  + castedNode.contId + " "  + castedNode.getStatistics().getMean());
						castedNode.split();
					//}
					// castedNode.assignAction();
				}
			} else {
				// root or random nodes
				node.getStatistics().addValue(1.0);
			}

		}
		
		//System.out.println("==============");
	}

}
