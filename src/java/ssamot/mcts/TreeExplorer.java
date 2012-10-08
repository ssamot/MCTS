/*
 *
 * E-EPNAC Poker A.I.
 * Spyridon Samothrakis ssamot@essex.com
 * All code (c) 2011 Spyridon Samothrakis All Rights Reserved
 *
 */

package ssamot.mcts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TreeExplorer {
	private Random generator = new Random();
	private static final boolean ENABLE_DOT = true;

	// UCT uct = new UCT();

	public double expectimax(StatisticsNode node, int maxDepth, int regretPlayer) {

		if(maxDepth < 1) {
			return -1;
		}
		if (ENABLE_DOT) {


			String label = "";

			//System.out.println(node.getChildren().size());
			
			for (StatisticsNode childNode : node.getChildren()) {
				// System.out.println("-=====Starting random action " +
				// node.getRewardId());
				
				if(childNode.getChildren() == null) {
					return -1;
				}
				if (childNode.getChildren().size() == 0) {

					label = "label=\""
							+ String.format("%1.3f",
									childNode.evaluate(regretPlayer)) + "\"";
				} else {
					label = "label=\""
							+ childNode.getChildren().get(0).getRewardId()
							+ " " + childNode.toString() + "\"";
				}
				System.out.println("\"" + childNode.hashCode() + "\"" + "["
						+ label + "];");

				String dot = "";

				if (childNode instanceof MCTSContinuousNode) {
					MCTSContinuousNode ccNode = (MCTSContinuousNode) childNode;
					dot = toDot("" + node.hashCode(),
							"" + childNode.hashCode(), ccNode.getMin() + "-"
									+ ccNode.getMax(), childNode.getAction());
				}

				else {
					dot = toDot("" + node.hashCode(),
							"" + childNode.hashCode(),
							childNode.getProbability(), childNode.getAction());
				}
				System.out.println(dot);

			}
		}
		// }
		double a = -1;

		if (node.isLeaf()) {

			double score = node.evaluate(regretPlayer);

			// System.out.println("score=" + score);
			// System.out.println(maxDepth + " " + score);
			return score;
		}

	

		if (node.getChildren().get(0).getType() != MCTSNode.NodeType.STOCHASTIC) {

			a = Double.NEGATIVE_INFINITY;

			List<StatisticsNode> children = node.getChildren();
			int[] order = new int[children.size()];
			for (int i = 0; i < children.size(); i++) {
				order[i] = i;
			}
			// System.out.println(node.getChildren().get(0).getRewardId());
			// shuffle(order);
			// System.out.println(Arrays.toString(order));
			for (int i = 0; i < order.length; i++) {
				// System.out.println("-=====Starting player actions");
				StatisticsNode childNode = children.get(order[i]);
				double score = expectimax(childNode, maxDepth - 1, regretPlayer);

				if (score > a) {
					a = score;
				}
				// childNode = null;
			}

		}

		else {
			a = 0;

			List<StatisticsNode> children = node.getChildren();
			// int[] order = new int[children.size()];
			// for (int i = 0; i < children.size(); i++) {
			// order[i] = i;
			// }

			// shuffle(order);
			for (int i = 0; i < children.size(); i++) {
				StatisticsNode childNode = children.get(i);
				double score = childNode.getProbability()
						* expectimax(childNode, maxDepth - 1, regretPlayer);
				a += score;

				// childNode = null;
			}

		}

		// node = null;
		return a;
	}

	

	public static String toDot(String father, String child, double probability,
			int action) {
		String label = "label=\"" + "a:" + action + ",p:"
				+ String.format("%1.3f", probability) + "\"";
		// String label =
		return (" \"" + father + "\"" + " ->" + " \"" + child + "\"[" + label + "];");
	}

	public static String toDot(String father, String child, String minmax,
			int action) {
		String label = "label=\"" + "a:" + action + ",p:" + minmax + "\"";
		// String label =
		return (" \"" + father + "\"" + " ->" + " \"" + child + "\"[" + label + "];");
	}

}
