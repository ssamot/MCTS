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

package ssamot.mcts.selectors.exp3;

import ssamot.mcts.selectors.Selector;
import ec.util.MersenneTwister;

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
