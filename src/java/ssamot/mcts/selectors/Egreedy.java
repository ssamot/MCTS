package ssamot.mcts.selectors;

import java.util.List;

import ssamot.mcts.StatisticsNode;

public class Egreedy implements Selector<StatisticsNode> {
    
    public static final double E = 0.2;
    java.util.Random generator = new java.util.Random();
    
    @Override
    public StatisticsNode selectChild(StatisticsNode node) {
        
        List<StatisticsNode> children = node.getChildren();
        int childrenSize = children.size();
        double max = Integer.MIN_VALUE;
        int maxNode = -1;

        
       
        for (int i = 0; i < childrenSize; i++) {
            
        	StatisticsNode childNode = children.get(i);
           
            
            //System.out.println(node);
            double nodeStatistic = childNode.getStatistics().getMean();
            if (nodeStatistic > max) {
                maxNode = i;
                max = nodeStatistic;
            }
        }
        
        if(generator.nextDouble() < E && maxNode!=-1) {
            return children.get(maxNode);
        }
        else {
            return children.get(generator.nextInt(childrenSize));
        }
        
    }
    
   
}
