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

import ssamot.mcts.ucb.optimisation.ContinuousProblem;

public class XSquaredCoco extends ContinuousProblem {
	double max = Double.NEGATIVE_INFINITY;
	double min = Double.POSITIVE_INFINITY;
	@Override
	public double getFtarget() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double evaluate(double[] x) {
		double res = 0;
		//System.err.println(Arrays.toString(x));
		
		
		for (int i = 0; i < x.length; i++) {
			//System.err.println(res);
			res += ((x[i]-0.6)*(x[i]-0.6));
		}
		
		double score = -res;
		
//		if (score > max) {
//			max = score;
//		}
//
//		if (score < min) {
//			min = score;
//		}
//
//		if (max == min) {
//			score =  0.0;
//		}
//		else {
//			score = (score - min) / (max - min);
//		}
		//System.out.println(score);
		return score;
	}

}
