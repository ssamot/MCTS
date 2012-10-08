package ssamot.mcts.ucb.optimisation;

import ssamot.mcts.MCTS;
import ssamot.mcts.StatisticsNode;
import ssamot.mcts.selectors.ChanceProportional;
import ssamot.mcts.selectors.ucb.UCB1;
import ssamot.mcts.selectors.ucb.UCBActionSelector;
import ssamot.mcts.ucb.optimisation.nodes.UCBContinousNodeOptimisation;

public class UCTOptimiser extends MCTS<StatisticsNode> {

	public UCTOptimiser(ContinuousProblem func, int length, int min, int max,
			int splitPoints) {
		super();
		setActionSelector(new UCBActionSelector());
		setBackpropagator(new UCBOptimisingBackpropagator(func, length));
		setChanceNodeSelector(new ChanceProportional());
		setDeterministicNodeSelector(new UCB1());
		StatisticsNode rootNode = getRootNode(length, min, max, splitPoints);
		setRootNode(rootNode);

	}

	public UCBContinousNodeOptimisation getRootNode(int length, int min,
			int max, int splitPoints) {
		UCBContinousNodeOptimisation rootNode = new UCBContinousNodeOptimisation(
				min, max, splitPoints);
		rootNode.contId = "root";

		UCBContinousNodeOptimisation node = rootNode;
		for (int i = 0; i < length - 1; i++) {
			UCBContinousNodeOptimisation newNode = new UCBContinousNodeOptimisation(
					min, max, splitPoints);

			newNode.getStatistics();
			node.addChild(newNode);
			node = newNode;
			// System.out.println(i);
		}

		node.addChild(new UCBLeafNode());
		return rootNode;
	}

}
