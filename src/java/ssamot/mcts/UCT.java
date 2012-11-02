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

package ssamot.mcts;

import ssamot.mcts.backpropagators.UCBBackpropagator;
import ssamot.mcts.selectors.ChanceProportional;
import ssamot.mcts.selectors.ucb.UCB1;
import ssamot.mcts.selectors.ucb.UCBActionSelector;

public class UCT extends MCTS<StatisticsNode> {

	public UCT(StatisticsNode rootNode) {
		super(rootNode);
		setActionSelector(new UCBActionSelector());
		setBackpropagator(new UCBBackpropagator<StatisticsNode>());
		setChanceNodeSelector(new ChanceProportional());
		setDeterministicNodeSelector(new UCB1());
	}
	
	public UCT() {
		super();
		setActionSelector(new UCBActionSelector());
		setBackpropagator(new UCBBackpropagator<StatisticsNode>());
		setChanceNodeSelector(new ChanceProportional());
		setDeterministicNodeSelector(new UCB1());
	}

}
