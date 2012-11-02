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

package ssamot.mcts.selectors.ucb;

import ssamot.mcts.StatisticsNode;
import ssamot.mcts.selectors.Selector;
import ec.util.MersenneTwister;

public abstract class UCB<T extends StatisticsNode> implements Selector<T> {

	protected MersenneTwister twister = new MersenneTwister();
	
	@Override
	public T selectChild(T node) {

		double max = Double.NEGATIVE_INFINITY;
		StatisticsNode maxChild = null;
		for (StatisticsNode childNode : node.getChildren()) {

			double childScore = getNodeScore(node, (T) childNode);
			//System.out.println(node);
			if (childScore >= max) {
				maxChild = childNode;
				max = childScore;
			}

		}

		if(maxChild == null) {
			for (StatisticsNode childNode : node.getChildren()) {

				double childScore = getNodeScore(node, (T) childNode);
				System.out.println(node + " " + childScore);
				

			}
		}
		
		assert(maxChild!=null);
		return (T) maxChild;

	}

	public abstract double getNodeScore(T fatherNode, T childNode);

}
