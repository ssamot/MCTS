package ssamot.mcts;

/*
 * Spyridon Samothrakis, 07-Feb-2010
 * An implementation of UCT+
 * Algorithm as presented in  
 * http://hal.inria.fr/docs/00/12/15/16/PDF/RR-6062.pdf
 * and 
 * http://www.springerlink.com/content/l7v1647363415h1t/
 * 
 * Supports expectimax-n trees ( expectimax, minimax, planning, max-n, probmax-n)
 * 
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
//import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.List;

import ssamot.mcts.MCTSNode.NodeType;
import ssamot.mcts.backpropagators.Backpropagator;
import ssamot.mcts.selectors.Selector;
import ssamot.utilities.ElapsedCpuTimer;

/**
 * @author spyros
 * 
 */
public class MCTS<N extends MCTSNode> {


	public static boolean DEBUG = true;
	
	private N rootNode;

	private int treePolicyMaxTreeDepth = 3000;
	private boolean enableDefaultPolicy = false;

	private Selector<N> chanceNodeSelector;
	private Selector<N> deterministicNodeSelector;;
	private Selector<N> actionSelector;
	private Backpropagator<N> backpropagator;;



	public Selector<N> getChanceNodeSelector() {
		return chanceNodeSelector;
	}

	public Selector<N> getDeterministicNodeSelector() {
		return deterministicNodeSelector;
	}

	public Selector<N> getActionSelector() {
		return actionSelector;
	}

	public Backpropagator<N> getBackpropagator() {
		return backpropagator;
	}

	public void setChanceNodeSelector(Selector<N> chanceNodeSelector) {
		this.chanceNodeSelector = chanceNodeSelector;
	}

	public void setDeterministicNodeSelector(Selector<N> deterministicNodeSelector) {
		this.deterministicNodeSelector = deterministicNodeSelector;
	}

	public void setActionSelector(Selector<N> actionSelector) {
		this.actionSelector = actionSelector;
	}

	public void setBackpropagator(Backpropagator<N> backpropagator) {
		this.backpropagator = backpropagator;
	}

	public N getRootNode() {
		return rootNode;
	}

	public void setRootNode(N rootNode) {
		this.rootNode = rootNode;
	}

	public MCTS(N rootNode) {
		super();
		this.rootNode = rootNode;
	}
	
	public MCTS() {
		super();
	}

	// public double getRootNodeValue() {
	// return this.rootNode.value;
	// }

	public void runForMs(long time) {

		int totalSimulations = 0;

		// reset the timer
		ElapsedCpuTimer cput = new ElapsedCpuTimer(
				ElapsedCpuTimer.TimerType.WALL_TIME);

		
		for (;;) {
			// Do a simulation
			totalSimulations += 1;
			playOneSequence(rootNode);
			//System.out.println(cput.elapsedMillis());
			if (time <= cput.elapsedMillis()) {
				
				break;
			}

		}
	

		if (DEBUG) {
			System.err.println("Total Time Spent is: "
					+ (double) (cput.elapsedMillis()) + "ms");
			System.err.println("Total Simulations: " + totalSimulations);
		}
	

	}

	public void runForSim(int simulations) {

		ElapsedCpuTimer cput = new ElapsedCpuTimer(
				ElapsedCpuTimer.TimerType.WALL_TIME);

		int totalSimulations = 0;
		for (; totalSimulations < simulations; totalSimulations++) {
			playOneSequence(rootNode);
		}
		

		if (DEBUG) {
			System.err.println("Total Time Spent is: "
					+ (double) (cput.elapsedMillis()) + "ms");
			System.err.println("Total Simulations: " + totalSimulations);
		}
		

	}

	@SuppressWarnings("unused")
	public N getHighestScoringChild(N node) {
		return actionSelector.selectChild(node);
	}

	private boolean playOneSequence(N rootNode) {
		//System.err.println("playing one sequence");
		List<N> nodes = new ArrayList<N>();
		nodes.add(rootNode);

		N node = rootNode;
		// System.err.println("Starging Loop");
		// just for two people games, pick a random move for the opponent
		for (int depth = 0;; depth++) {
			// System.err.println(i + "inside UCT");
			node = descend(nodes.get(depth));
			if (node == null) {
				// We have found a null node, this should never happen
				throw new RuntimeException("Found a null node at depth " + depth);
			}

			nodes.add(node);
			// These can be put in the for loop, but it's easier to see what's
			// going on here
			// System.out.println(node);
			if (node.isLeaf()) {
				break;
			}

			
			if (node.canBeEvaluated() && enableDefaultPolicy
					&& node.isFirstTime() ) {
				//System.out.println(node.canBeEvaluated() + " " +  enableDefaultPolicy + " " + node.isFirstTime() + depth);
				break;

			}

			if (depth >= treePolicyMaxTreeDepth && node.canBeEvaluated()) {

				break;
			}
		}
		List<Double> reward = null;
		if(node.isFirstTime() && enableDefaultPolicy) {
			
			reward = node.evaluateDefaultPolicy();
			node.setFirstTime(false);
			
			
			
		}
		else {
			reward = node.evaluate();
		}
		
		backpropagator.backpropagate(nodes, reward);

		return false;

	}

	@SuppressWarnings("unused")
	private N descend(N node) {
		// System.out.println("????");
		N preferedChild = null;

		if (((MCTSNode) node.getChildren().get(0)).getType() == NodeType.STOCHASTIC) {
			preferedChild = chanceNodeSelector.selectChild(node);
		} else {
			preferedChild = deterministicNodeSelector.selectChild(node);
		}

		// System.out.println(node);
		return preferedChild;

	}

	
}