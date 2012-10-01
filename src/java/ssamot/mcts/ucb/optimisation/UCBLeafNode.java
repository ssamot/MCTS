package ssamot.mcts.ucb.optimisation;

import ssamot.mcts.StatisticsNode;

public class UCBLeafNode extends StatisticsNode {

	@Override
	public int getAction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRewardId() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void generateChildren() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canBeEvaluated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public double evaluate(int player) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double evaluateDefaultPolicy(int player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGameTotalGamePlayers() {
		// TODO Auto-generated method stub
		return 0;
	}

}
