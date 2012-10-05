package ssamot.mcts;

/*
 *   Copyright (C) 2010  Spyridon Samothrakis
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

import java.util.ArrayList;
import java.util.List;

public abstract class MCTSNode<T> {

	public enum NodeType {
		STOCHASTIC, DETERMINISTIC
	};

	private boolean firstTime = true;

	private NodeType type = NodeType.DETERMINISTIC;

	protected List<T> children;
	protected List<Double> scores;

	private double probability = 0;

	protected boolean autoGenerateChildren;

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public List<T> getChildren() {
		if (children == null && !isLeaf() && autoGenerateChildren) {
			children = new ArrayList<T>();
			generateChildren();
		}
		return children;
	}

	public abstract int getAction();

	public abstract int getRewardId();

	public abstract boolean isLeaf();

	public abstract void generateChildren();

	public abstract boolean canBeEvaluated();

	public abstract double evaluate(int player);
	
	//public abstract void runDefaultPolicy();
	

	public abstract double evaluateDefaultPolicy(int player);

	public abstract int getGameTotalGamePlayers();

	public List<Double> evaluate() {
		if (scores == null) {
			scores = new ArrayList<Double>(getGameTotalGamePlayers());
			scores.add(0.0);
			for (int i = 1; i < getGameTotalGamePlayers(); i++) {
				scores.add(evaluate(i));
			}
		}

		return scores;
	}

	public List<Double> evaluateDefaultPolicy() {
		List<Double> dScores = new ArrayList<Double>(getGameTotalGamePlayers());

		dScores.add(0.0);
		for (int i = 1; i < getGameTotalGamePlayers(); i++) {
			dScores.add(evaluateDefaultPolicy(i));
		}

		return dScores;

	}
	


	public void setType(NodeType type) {
		this.type = type;
	}

	public NodeType getType() {
		return type;
	}

	public void prune() {
		// clear all the children and call the garbage collector
		children = null;
		System.gc();
	}

	public void reset() {
		children.clear();
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

}
