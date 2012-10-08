package ssamot.mcts;

import ssamot.mcts.backpropagators.UCBBackpropagator;
import ssamot.mcts.selectors.ChanceProportional;
import ssamot.mcts.selectors.ucb.UCB1;
import ssamot.mcts.selectors.ucb.UCBActionSelector;

public class UCT extends MCTS<StatisticsNode> {

	public UCT(StatisticsNode rootNode) {
		super(rootNode);
		setActionSelector(new UCBActionSelector());
		setBackpropagator(new UCBBackpropagator<StatisticsNode>());
		setChanceNodeSelector(new ChanceProportional());
		setDeterministicNodeSelector(new UCB1());
	}
	
	public UCT() {
		super();
		setActionSelector(new UCBActionSelector());
		setBackpropagator(new UCBBackpropagator<StatisticsNode>());
		setChanceNodeSelector(new ChanceProportional());
		setDeterministicNodeSelector(new UCB1());
	}

}
