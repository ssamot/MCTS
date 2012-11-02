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

import ssamot.mcts.backpropagators.EXP3Backpropagator;
import ssamot.mcts.selectors.ChanceProportional;
import ssamot.mcts.selectors.exp3.EXP3;
import ssamot.mcts.selectors.exp3.EXP3Node;

public class EXP3T extends MCTS<EXP3Node> {

	public EXP3T(EXP3Node rootNode) {
		super(rootNode);
		setActionSelector(new EXP3());
		setBackpropagator(new EXP3Backpropagator<EXP3Node>());
		setChanceNodeSelector(new ChanceProportional());
		setDeterministicNodeSelector(new EXP3());
	}

}
