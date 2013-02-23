package ssamot.mcts.ucb.optimisation;

public class URLockingContinuousProblem extends LockingContinuousProblem {
	double max = 20;
	double min = -20;

	@Override
	public void setScore(double score) {
		// TODO Auto-generated method stub
		if (score > max) {
			max = score;
		}

		if (score < min) {
			min = score;
		}

		if (max == min) {
			score =  0.0;
		}
		else {
			score = (score - min) / (max - min);
		}
		super.setScore(score);
	}

}
