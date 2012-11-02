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

package ssamot.mcts.ucb.optimisation;

import java.util.List;

import ssamot.mcts.StatisticsNode;

public class HOONodeExplorer {

	StatisticsNode node;

	public HOONodeExplorer(StatisticsNode node) {
		super();
		this.node = node;
	}

	public void generatePath(StatisticsNode node, int position, double result[]) {

		if (node.isLeaf()) {
			return;
		}

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

			if (cVal >= max) {
				index = i;
				max = cVal;
			}
		}

		if (index == -1) {
			return;
		}
		StatisticsNode best = children.get(index);

		if (best instanceof MCTSContinuousNode) {

			MCTSContinuousNode ccNode = (MCTSContinuousNode) best;
			// if(!ccNode.isHasBeenSplit()) {

			// if (depth == 0) {
			//
			// } else {
			// System.out.print(",");
			// }
			// System.out.println(ccNode.getContinousAction() + " " +
			// ccNode.getMin() + " " + ccNode.getMax() + " test");

			if (!ccNode.isHasBeenSplit()) {
				// System.out.print(ccNode.getContinousAction() + " " +
				// ccNode.getMin() + " " + ccNode.getMax() + " test");

				// }
				result[position] = ccNode.getContinuousAction();
				position++;
				// System.out.println( ccNode.contId);
			}
		} else {
			// System.out.print(cNode.getAction());
		}
		// System.err.println(cVal);

		generatePath(best, position, result);

	}

	public double[] generatePath(int dimensions) {
		System.out.println("");
		double[] result = new double[dimensions];
		generatePath(node, 0, result);
		System.out.println("");
		return result;

	}

}
