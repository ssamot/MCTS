package ssamot.mcts.selectors.ucb;

import ssamot.config.GameConfig;
import ssamot.mcts.StatisticsNode;


public class UCB1 extends UCB {
    
    private static double UCB1_C = GameConfig.getInstance().getUcb1C();
    private static double UCB_MINIMUM_ITERATIONS = GameConfig.getInstance().getUcb1MinimumIterations();
   
    
    @Override
    public double getNodeScore(StatisticsNode fatherNode, StatisticsNode childNode) {
        
    	double score = 0;
    	
    	if(fatherNode.getStatistics().getN() < UCB_MINIMUM_ITERATIONS ) {
    		score = (double)Integer.MAX_VALUE - twister.nextDouble();
    	}
    
    	else if(childNode.getStatistics().getN() != 0 ) {
    		
    		score =  childNode.getStatistics().getMean() + UCB1_C
                    * Math.sqrt((2.0*Math.log(fatherNode.getStatistics().getN())) / childNode.getStatistics().getN());	
    		assert(fatherNode.getStatistics().getN()!=0);
    		
    		//System.out.println(fatherNode.getStatistics().getN() + "  " + childNode.getStatistics().getN() + score);
    	}
    	
    	else {
    		score = (double)Integer.MAX_VALUE - twister.nextDouble();
    		//System.err.println("random");
    	}
    	
    	assert(score!=Double.NaN);
        return score;
    }
    
}
