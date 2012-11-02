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

package ssamot.mcts.backpropagators;

import java.util.List;

import ssamot.mcts.StatisticsNode;

public class UCBBackpropagator<T extends StatisticsNode>implements Backpropagator<T> {

	@Override
	public void backpropagate(List<T> nodes, List<Double> reward) {
		// TODO Auto-generated method stub
		//System.exit(0);
		//System.out.println(reward);
		int nodeSize = nodes.size();

		for (int i = nodeSize - 1; i >= 0; i--) {

			T node = nodes.get(i);
			int id = node.getRewardId();
			
			if (id > 0) {
				double value = (reward.get(id));
				//System.err.println(id + " " + value);
				node.getStatistics().addValue(value);
				
			}
			else {
				//root or random nodes
				node.getStatistics().addValue(1.0);
			}

		}
	}

}
