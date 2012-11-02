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

import junit.framework.TestCase;
import ssamot.mcts.StatisticsNode;
import ssamot.mcts.UCT;

public class UCTTest extends TestCase {
	UCT uct = null;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		UCBTestNode rootNode = new UCBTestNode(-1, 0, 0);
		uct = new UCT(rootNode);
		//uct.runForMs(500);
		uct.runForSim(10000);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testUCBBestActionCorrect() {

		StatisticsNode bestNode = uct.getHighestScoringChild(uct.getRootNode());
		assertEquals(0,bestNode.getAction());
	}
	
	public void testUCBStochasticNodesCorrect() {
		StatisticsNode bestNode = uct.getHighestScoringChild(uct.getRootNode());
		//bestNode = uct.getHighestScoringChild(bestNode);
		//bestNode = uct.getHighestScoringChild(bestNode);
		//
		assertTrue((bestNode.getChildren().get(0).getStatistics().getN() - bestNode.getChildren().get(1).getStatistics().getN()) >200);
		//bestNode = uct.getHighestScoringChild(bestNode);
	}
	
}