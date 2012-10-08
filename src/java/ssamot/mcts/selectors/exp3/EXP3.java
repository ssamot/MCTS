package ssamot.mcts.selectors.exp3;

import ssamot.mcts.MCTSNode;
import ssamot.mcts.selectors.Selector;
import ssamot.utilities.MersenneTwister;

public class EXP3 implements Selector<EXP3Node>  {

	protected static MersenneTwister twister = new MersenneTwister();

	
	@Override
	public EXP3Node selectChild(EXP3Node node) {

		
		
		
	
		
		// sum up the probability of children actions
		
		double rewardSum = 0;
		
		for (EXP3Node childNode : node.getChildren()) {
			double reward = childNode.getReward();
			rewardSum+=reward;
		}

		double probTotal = 0;
		double kn = node.getChildren().size();
		EXP3Node selectedChild = null;
	
		for (EXP3Node childNode : node.getChildren()) {

			double prob = twister.nextDouble();
            
			double probability = (1-node.getGamma())*(node.getReward()/rewardSum)+ 1.0/kn;
			
			childNode.setProbability(probability);
			
			probTotal += probability;
		
			// System.out.println(node);
			 if (probTotal > prob && selectedChild == null) {
				 selectedChild  = childNode;
             }

		}
		
		
		return selectedChild;

	}


}
