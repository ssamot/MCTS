package ssamot.mcts;

import java.util.ArrayList;
import java.util.List;

import ssamot.utilities.MersenneTwister;
import ssamot.utilities.SummaryStatistics;

public abstract class MCTSContinuousNode extends StatisticsNode {

	public MersenneTwister twister = new MersenneTwister();

	protected double action = -1;

	boolean hasBeenSplit = false;

	public boolean isHasBeenSplit() {
		return hasBeenSplit;
	}

	public String contId = "default";

	// includes min
	protected double min = 0;
	// does not include max
	protected double max = 1;
	protected int splitPoints = 2;

	private int point = -1;

	private double oldAction;

	private double sigma = 1.5;

	double oldValue = Double.NEGATIVE_INFINITY;
	
	int timeshit = 0;
	
	int depth = 0;


	public void setup(double min, double max, int splitPoints, int point) {
		this.min = min;
		this.max = max;
		this.splitPoints = splitPoints;
		this.point = point;

		assignAction();

	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public int getSplitPoints() {
		return splitPoints;
	}

	public double getContinousAction() {
		return action;
	}

	@Override
	public int getAction() {
		// TODO Auto-generated method stub
		return point;
	}

	public void assignAction() {
		 action = min + Math.abs(max - min) * twister.nextDouble();
		//action = min + Math.abs(max - min) / 2.0;
	}

	public void split() {
		
		//timeshit++;
		
		//if(timeshit < 6) {
			
		//	return;
		//}
			
		if(depth > 8) {
			//System.out.println(min + ", " + max);
			assignAction();
			return ;
			
		}

		if (!hasBeenSplit) {

			List<StatisticsNode> oldChildren = children;
			children = new ArrayList<StatisticsNode>();
			double interval = Math.abs(max - min) / (double) splitPoints;
			for (int i = 0; i < splitPoints; i++) {

				double nMin = min + i * interval;
				double nMax = min + (i + 1) * interval;

				// System.out.println(nMin + " " + nMax);
				MCTSContinuousNode node = getNewInstance(oldChildren);
				node.setup(nMin, nMax, splitPoints, i);
				node.actionStatistics = new SummaryStatistics();
				// node.actionStatistics.addValue(actionStatistics.getMean());
				node.contId = contId;
				// node.actionStatistics.setN(1);
				node.assignAction();
				node.depth = depth +1;
				//System.out.println(node.depth);
				children.add(node);
			}
			hasBeenSplit = true;
		}

	}

	public void mutate() {
		oldAction = action;
		action = action + sigma * twister.nextGaussian();
		if (action > max) {
			action = max;
		}
		if (action < min) {
			action = min;
		}
		// System.out.println(action + " " + sigma);
	}

	

	public void response(double value) {
		// throw away mutation

		if (value >= oldValue) {
			oldValue = value;
			sigma = 1.5 * sigma;
		} else {

			action = oldAction;
			sigma = sigma * 0.9036020036098448;
		}

	}

	public abstract MCTSContinuousNode getNewInstance(List<StatisticsNode> oldChildren);

}
