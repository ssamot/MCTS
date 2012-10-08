package ssamot.mcts.ucb.optimisation.nodes;

import java.util.ArrayList;
import java.util.List;

import ssamot.mcts.StatisticsNode;
import ssamot.mcts.MCTSContinuousNode;
import ssamot.utilities.SerialClone;

import com.rits.cloning.Cloner;

public class UCBContinousNodeOptimisation extends MCTSContinuousNode {

	public UCBContinousNodeOptimisation(double min, double max, int splitPoints) {
		super();
		this.setup(min, max, splitPoints, -1);
	}

	@Override
	public MCTSContinuousNode getNewInstance(List<StatisticsNode> oldChildren) {
		UCBContinousNodeOptimisation inst = new UCBContinousNodeOptimisation(
				min, max, splitPoints);
		// Cloner cloner=new Cloner();

		inst.children = (oldChildren);
		return inst;
	}

	public void addChild(StatisticsNode node) {
		// if (children == null && !isLeaf()) {
		children = new ArrayList<StatisticsNode>();

		// }
		// System.out.println(this + "here");
		children.add(node);
	}

	@Override
	public int getRewardId() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void generateChildren() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canBeEvaluated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double evaluate(int player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void runDefaultPolicy() {
		// TODO Auto-generated method stub

	}

	@Override
	public double evaluateDefaultPolicy(int player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGameTotalGamePlayers() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String toString() {
		return "Av [ac=" + String.format("%1.5f", actionStatistics.getMean())
				+ "," + contId + "]";
	}

}
