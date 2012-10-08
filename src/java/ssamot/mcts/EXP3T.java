package ssamot.mcts;

import ssamot.mcts.backpropagators.EXP3Backpropagator;
import ssamot.mcts.selectors.ChanceProportional;
import ssamot.mcts.selectors.exp3.EXP3;
import ssamot.mcts.selectors.exp3.EXP3Node;

public class EXP3T extends MCTS<EXP3Node> {

	public EXP3T(EXP3Node rootNode) {
		super(rootNode);
		setActionSelector(new EXP3());
		setBackpropagator(new EXP3Backpropagator<EXP3Node>());
		setChanceNodeSelector(new ChanceProportional());
		setDeterministicNodeSelector(new EXP3());
	}

}
