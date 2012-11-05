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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MDPRootActionReplayer {
	

	public double[] replay(double[][] samples,double[] rewards, int dimension,
			int iterations, double min, double max, double gamma) {
		
		//System.out.println(samples.length);
		double[] minA = new double[1];
		double[] maxA = new double[1];


		System.out.println(min + " " + max);
		
		for (int i = 0; i < minA.length; i++) {
			minA[i] = min;
			maxA[i] = max;
		}
		HOOB hoob = new HOOB(dimension, iterations);
		MCTSContinuousNode rootNode = new MCTSContinuousNode(minA, maxA, 2, -1,
				0, hoob.getMaxDepth(), gamma);
		rootNode.split();

		for (int i = 0; i< samples.length ; i++) {
			insertSample(samples[i][0],rewards[i], rootNode, new ArrayList<MCTSContinuousNode>());
		}
		return getBestScore(rootNode);
	}

	private void insertSample(double action, double reward,
			MCTSContinuousNode node, List<MCTSContinuousNode> nodes) {
		double max = node.getMax()[0];
		double min = node.getMin()[0];
		
		nodes.add(node);
		
		
		if (!node.isLeaf()) {
			MCTSContinuousNode leftChild = (MCTSContinuousNode) node
					.getChildren().get(0);
			MCTSContinuousNode rightChild = (MCTSContinuousNode) node
					.getChildren().get(1);
			double boundary = min + Math.abs(max - min) / 2.0;
			//System.out.println(leftChild.getMin()[0] + " " + leftChild.getMax()[0] + " " +  boundary);
			
			if (action <= boundary) {
				// self._left.insertSample(sampleAction, reward)
				
				insertSample(action, reward, leftChild,nodes);
			} else {
				insertSample(action, reward, rightChild,nodes);
			}
		} else {
			//System.exit(0);
			
			addSample(nodes,action, reward);
		}
	}

	private void addSample(List<MCTSContinuousNode> nodes, double action, double reward) {
		int nodeSize = nodes.size();
		for (int i = nodeSize - 1; i >= 0; i--) {

			MCTSContinuousNode node = nodes.get(i);
			int id = node.getRewardId();
			// System.out.println(i + " " + (nodeSize -2));
			if (id > 0) {
				node.getStatistics().addValue(reward);
			} else {
				// root or random nodes
				node.getStatistics().addValue(1.0);
			}

		}
		nodes.get(nodes.size() - 1).addSample(new double[]{action}, reward);

		nodes.get(nodes.size() - 1).split();
	}
	
	private double[] getBestScore(MCTSContinuousNode rootNode) {
		while(!rootNode.isLeaf()) {
			MCTSContinuousNode leftChild = (MCTSContinuousNode) rootNode
					.getChildren().get(0);
			MCTSContinuousNode rightChild = (MCTSContinuousNode) rootNode
					.getChildren().get(1);
			
			
			if(rightChild.getStatistics().getN() == 0 && leftChild.getStatistics().getN() == 0) {
				break;
			}
			
			if(rightChild.getStatistics().getN() == 0 ) {
				rootNode = leftChild;
			}
			else if(leftChild.getStatistics().getN() == 0) {
				rootNode = rightChild;
			}
			else if(leftChild.getStatistics().getMean() > rightChild.getStatistics().getMean()) {
				rootNode = leftChild;
			}
			else {
				rootNode = rightChild;
			}
			
			//System.out.println(leftChild.getStatistics().getMean()  + ", " + rightChild.getStatistics().getMean());
			
			
		}
		//System.out.println(Arrays.toString(new double[]{rootNode.getMin()[0], rootNode.getMax()[0]}));
		return rootNode.sampleAction();
	}

}
