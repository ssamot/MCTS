package ssamot.test;

import ssamot.mcts.ucb.optimisation.ContinuousProblem;

public class XSquaredCoco extends ContinuousProblem {

	@Override
	public double getFtarget() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double evaluate(double[] x) {
		double res = 0;
		//System.err.println(Arrays.toString(x));
		for (int i = 0; i < x.length; i++) {
			//System.err.println(res);
			res += ((x[i]-0.6)*(x[i]-0.6));
		}
		
		return -res;
	}

}
