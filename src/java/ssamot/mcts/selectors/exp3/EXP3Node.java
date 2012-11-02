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

package ssamot.mcts.selectors.exp3;

import ssamot.mcts.MCTSNode;

public abstract class EXP3Node extends MCTSNode<EXP3Node> {

	private double gamma = 0.0;
	
	
	
	public EXP3Node() {
		super();
		setProbability(1.0);
	}
	public double getGamma() {
		return gamma;
	}
	public double getReward() {
		return reward;
	}
	public void setGamma(double gamma) {
		this.gamma = gamma;
	}
	public void setReward(double reward) {
		this.reward = reward;
	}
	private double reward = 0.0;
	
	
	

}
