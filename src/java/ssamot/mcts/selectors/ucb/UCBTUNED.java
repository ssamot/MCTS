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

public class UCBTUNED extends UCB {

	// private double devisor = 10;

	
	private double UCB_MINIMUM_ITERATIONS = 100;
	
	@Override
	public double getNodeScore(StatisticsNode fatherNode, StatisticsNode childNode) {

		double score = 0;

		if (fatherNode.getStatistics().getN() < UCB_MINIMUM_ITERATIONS) {
			score = (double) Integer.MAX_VALUE - twister.nextDouble();
		} else if (childNode.getStatistics().getN() != 0) {

			double absMax = 1;

			// if(absMax > Math.abs(childNode.getStatistics().getMin())) {
			// absMax = Math.abs(childNode.getStatistics().getMin());
			// }

			double mean = childNode.getStatistics().getMean() / absMax;
			double sd = childNode.getStatistics().getStandardDeviation()
					/ absMax;

			double nb = fatherNode.getStatistics().getN();
			double V = sd
					+ Math.sqrt(2.0 * Math.log(nb)
							/ (childNode.getStatistics().getN()));

			// System.out.println(childNode.getStatistics().getStandardDeviation());
			score = mean
					+ Math.sqrt((Math.log(nb) / childNode.getStatistics()
							.getN()) * Math.min(1.0 / 4.0, V));

		}

		else {
			score = (double) Integer.MAX_VALUE - twister.nextDouble();
		}

		return score;

	}

}
