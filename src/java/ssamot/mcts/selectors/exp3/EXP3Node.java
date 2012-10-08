package ssamot.mcts.selectors.exp3;

import ssamot.mcts.MCTSNode;

public abstract class EXP3Node extends MCTSNode<EXP3Node> {

	private double gamma = 0.0;
	
	
	
	public EXP3Node() {
		super();
		setProbability(1.0);
	}
	public double getGamma() {
		return gamma;
	}
	public double getReward() {
		return reward;
	}
	public void setGamma(double gamma) {
		this.gamma = gamma;
	}
	public void setReward(double reward) {
		this.reward = reward;
	}
	private double reward = 0.0;
	
	
	

}
