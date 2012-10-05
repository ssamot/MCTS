package ssamot.mcts.ucb.optimisation;

import java.util.List;

import ssamot.mcts.MCTSContinuousNode;
import ssamot.mcts.MCTSNode;
import ssamot.mcts.StatisticsNode;
import ssamot.mcts.backpropagators.Backpropagator;

public class HOOTruncatedBackpropagator implements
		Backpropagator<MCTSContinuousNode> {

	ContinuousProblem function;
	private int length;

	
	double bestValue = Double.NEGATIVE_INFINITY;
	double[] bestSample = null;
	// boolean once = true;

	public HOOTruncatedBackpropagator(ContinuousProblem function, int length) {
		super();
		this.function = function;
		this.length = length;
	}

	@Override
	public void backpropagate(List<MCTSContinuousNode> nodes,
			List<Double> reward) {
		// TODO Auto-generated method stub
		// System.out.println(reward);
		int nodeSize = nodes.size();

		double[] funcVal = new double[length];

		int j = 0;

		funcVal = nodes.get(nodes.size()-1).sampleAction();

		double value = function.evaluate(funcVal);
		
		if(value > bestValue) {
			bestValue = value;
			bestSample = funcVal;
		}
		
		// System.out.println(Arrays.toString(funcVal) + length + " " + value);

		for (int i = nodeSize - 1; i >= 0; i--) {

			MCTSContinuousNode node = nodes.get(i);
			int id = node.getRewardId();

			if (id > 0) {
				node.getStatistics().addValue(value);		
			} else {
				// root or random nodes
				node.getStatistics().addValue(1.0);
			}

		}

		// try splitting the last node
		nodes.get(nodes.size()-1).split();
		// System.out.println("==============");
	}

	public double getBestValue() {
		return bestValue;
	}

	public double[] getBestSample() {
		return bestSample;
	}

}
