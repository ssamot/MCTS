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

package ssamot.test;

import java.util.Arrays;

import junit.framework.TestCase;
import ssamot.mcts.MCTS;
import ssamot.mcts.StatisticsNode;
import ssamot.mcts.UCT;
import ssamot.mcts.ucb.optimisation.HOOOptimiser;
import ssamot.utilities.SummaryStatistics;

public class HOOTest extends TestCase {
	


	@Override
	protected void setUp() throws Exception {

	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testOneDimensions() {

		OneDimensionLinearCoco func = new OneDimensionLinearCoco();

		int iterations = 10000;

		int dimension = 1;

		int min = -10;
		int max = 4;
		int splitPoints = 2;

		HOOOptimiser hoo = new HOOOptimiser(func, dimension, iterations, min,
				max, splitPoints);
		hoo.runForSim(iterations);
		System.out.println(Arrays.toString(hoo.getBestSample()) + ","
				+ hoo.getBestValue());
	}

	
	public void testTwoDimensions() {

		XSquaredCoco func = new XSquaredCoco();

		int iterations = 10000;

		int dimension = 2;

		int min = -1;
		int max = 1;
		int splitPoints = 2;
		MCTS.DEBUG = false;
		SummaryStatistics stats = new SummaryStatistics();
		for (int i = 0; i < 2000; i++) {
			HOOOptimiser hoo = new HOOOptimiser(func, dimension, iterations, min,
					max, splitPoints);
			hoo.runForSim(iterations);
//			System.out.println(Arrays.toString(hoo.getBestSample()) + ","
//					+ hoo.getBestValue());
			
			stats.addValue(hoo.getBestValue());
			//System.out.println(i);
		}
		
		System.out.println(stats);
	
	}

}