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

import ssamot.mcts.MCTSNode;
import ssamot.mcts.StatisticsNode;

public class UCBTestNode extends StatisticsNode {

	

	int action = -1;
	int depth = 0;
	private int correctMoves;

	@Override
	public int getAction() {
		return action;
	}

	@Override
	public int getRewardId() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isLeaf() {
		if (depth > 5) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void generateChildren() {
		
		if(depth !=3) {
			
			UCBTestNode node = new UCBTestNode(0, depth + 1,correctMoves+1);
			node.setType(MCTSNode.NodeType.DETERMINISTIC);
			children.add(node);
			
			for (int i = 1; i < 4; i++) {
				node = new UCBTestNode(i, depth + 1,correctMoves);
				node.setType(MCTSNode.NodeType.DETERMINISTIC);
				children.add(node);
			}
			
			
		}
		else {
			for (int i = 0; i < 4; i++) {
				UCBTestNode node = new UCBTestNode(i, depth + 1,correctMoves);
				node.setType(MCTSNode.NodeType.STOCHASTIC);
				node.setProbability(1.0/4.0);
				children.add(node);
			}
		}
		
	}

	public UCBTestNode(int action, int depth, int correctMoves) {
		super();
		this.action = action;
		this.depth = depth;
		this.correctMoves = correctMoves;
	}

	@Override
	public boolean canBeEvaluated() {
		return isLeaf();
	}

	@Override
	public double evaluate(int player) {
		if(correctMoves > 3) {
			return 1;
		}
		else {
			return 0;
		}
	}

	

	@Override
	public int getGameTotalGamePlayers() {
		return 2;
	}

	

	@Override
	public double evaluateDefaultPolicy(int player) {
		// TODO Auto-generated method stub
		return 0;
	}

}
