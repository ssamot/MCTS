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

import ssamot.mcts.StatisticsNode;

public class Egreedy<T extends StatisticsNode > implements Selector<StatisticsNode> {
    
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
