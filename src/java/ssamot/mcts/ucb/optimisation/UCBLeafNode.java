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

import ssamot.mcts.StatisticsNode;

public class UCBLeafNode extends StatisticsNode {

	@Override
	public int getAction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRewardId() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void generateChildren() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canBeEvaluated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public double evaluate(int player) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double evaluateDefaultPolicy(int player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGameTotalGamePlayers() {
		// TODO Auto-generated method stub
		return 0;
	}

}
