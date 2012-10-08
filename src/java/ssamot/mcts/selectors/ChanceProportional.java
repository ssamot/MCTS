package ssamot.mcts.selectors;

import java.util.List;

import ssamot.mcts.MCTSNode;
import ssamot.mcts.MCTSNode.NodeType;

public class ChanceProportional implements Selector {
    
    java.util.Random generator = new java.util.Random();
    
    @Override
    public MCTSNode selectChild(MCTSNode node) {
        List<MCTSNode> children = node.getChildren();
        int childrenSize = children.size();
        
        if (children.get(0).getType() == NodeType.STOCHASTIC) {
                        
            int index = -1;
            
            double prob = generator.nextDouble();
            double probTotal = 0;
            for (int i = 0; i < childrenSize; i++) {
                
                MCTSNode childNode = children.get(i);
                
                probTotal += (childNode.getProbability());

                if (probTotal > prob) {
                    index = i;
                    break;
                }
                
            }

            
            if (index == -1) {
                // System.out.println(childrenSize);
                for (int i = 0; i < childrenSize; i++) {
                    MCTSNode childNode = children.get(i);
                    // System.out.println(childNode);
                }
                return children.get(generator.nextInt(children.size()));
            }
            return children.get(index);
        } else {
            return null;
        }
    }
}
