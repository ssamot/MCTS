package ssamot.mcts.ucb.optimisation;


public abstract class ContinuousProblem {

	
	public abstract double getFtarget();
	public abstract double evaluate(double[] x);
	

}
