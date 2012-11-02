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

import java.util.List;

import ssamot.mcts.StatisticsNode;
import ssamot.mcts.selectors.Selector;

public class UCBActionSelector<T extends StatisticsNode> implements Selector<T> {

	private static final boolean PRINT_ACTIONS = true;

	@Override
	public StatisticsNode selectChild(StatisticsNode node) {
		List<StatisticsNode> children = node.getChildren();

		// if(children.size() == 0 ){
		// return rootNode.getMove();
		// }

		double max = Integer.MIN_VALUE;
		int index = -1;
		// System.out.println(max);
		StatisticsNode cNode = null;
		for (int i = 0; i < children.size(); i++) {
			cNode = children.get(i);

			double cVal = -1;

			cVal = cNode.getStatistics().getMean();

			// double cVal = ((double) cNode.nb);
			// PRINT_ACTIONS = false;
			if (PRINT_ACTIONS) {
				System.err.println("Possible Move: " + cNode.getType()
						+ ", Probability=" + cNode.getProbability()
						+ ", id=" + cNode.getRewardId() 
						+ ", Action " + cNode.getAction()
						+ ", Node.n=" + cNode.getStatistics().getN()
						+ ", Node.value=" + cNode.getStatistics().getSum()
						+ ", average value=" + cNode.getStatistics().getMean()
						+ ", variance="
						+ cNode.getStatistics().getStandardDeviation());
				// System.err.println(cNode.toString());
				//
			}
			// System.err.println(cVal);
			if (cVal >= max) {
				index = i;
				max = cVal;
			}
		}
		if (PRINT_ACTIONS) {
			System.err.println("");
		}

		if (index == -1) {
			return children.get(0);
		}
		return children.get(index);
	}

}
