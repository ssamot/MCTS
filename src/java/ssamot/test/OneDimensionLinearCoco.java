package ssamot.test;

import ssamot.mcts.ucb.optimisation.ContinuousProblem;

public class OneDimensionLinearCoco extends ContinuousProblem {

	@Override
	public double getFtarget() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double evaluate(double[] x) {
	
		
		
		return Math.min(x[0],5);
	}

}
