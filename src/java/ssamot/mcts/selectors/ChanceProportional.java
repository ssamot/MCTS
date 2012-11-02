/*
 *
 * *** BEGIN LICENSE
 *  Copyright (C) 2012 Spyridon Samothrakis spyridon.samothrakis@gmail.com
 *  This program is free software: you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 3, as published
 *  by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranties of
 *  MERCHANTABILITY, SATISFACTORY QUALITY, or FITNESS FOR A PARTICULAR
 *  PURPOSE.  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program.  If not, see <http://www.gnu.org/licenses/>.
 * *** END LICENSE
 *
 */

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
