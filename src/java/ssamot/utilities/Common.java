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

package ssamot.utilities;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import ec.util.MersenneTwister;

public class Common {
	public static MersenneTwister twister = new MersenneTwister();

	public static boolean arrayRepresentsProbability(double[] probs) {
		double sum = 0;
		for (int i = 0; i < probs.length; i++) {
			sum += probs[i];
			if (probs[i] < 0) {
				return false;
			}
		}
		// System.err.println(sum);

		if (Math.abs(sum - 1) < 0.00001) {

			return true;
		} else {

			return false;
		}
	}

	public static boolean testIfpotIsEqual(short[] pot, boolean[] folded) {
		boolean flag = true;

		int first = -1;

		for (int i = 1; i < pot.length && flag; i++) {
			if (!folded[i]) {
				if (first == -1) {
					first = pot[i];
				}
				if (pot[i] != first) {
					System.err.println(Arrays.toString(pot));
					System.err.println(Arrays.toString(folded));
					System.err.println(i + " " + pot[i]);
					System.exit(0);
					flag = false;
				}
			}
		}
		return flag;
	}

	public static boolean rewardsAreZeroSum(List<Double> rewards, Object game) {
		double sum = 0;

		for (Double reward : rewards) {
			sum += reward;
		}

		if (!(Math.abs(sum) < 0.00001)) {

			System.out.println(Arrays.toString(rewards.toArray()));
			System.out.println(game.toString());
			return false;
		}
		return true;
		// LimitHoldemMCTSNode g = (LimitHoldemMCTSNode)game;
	}

	public static boolean arrayRepresentsProbability(float[] probs) {
		double sum = 0;
		for (int i = 0; i < probs.length; i++) {
			sum += probs[i];
		}
		// System.err.println(sum);

		if (Math.abs(sum - 1) < 0.00001) {

			return true;
		} else {

			return false;
		}
	}

	public static int pickRandomProportionally(double[] probs) {
		double rand = twister.nextDouble();
		//System.out.println(rand);
		double probSum = 0;
		for (int i = 0; i < probs.length; i++) {
			probSum += probs[i];
			if (probSum >= rand) {
				return i;
			}

		}

		return probs.length-1;

	}

	private static double SIGMOID[];
	private static double TANH[];
	static {
		if (SIGMOID == null) {
			SIGMOID = new double[10000];
			TANH = new double[10000];
			for (int i = 0; i < 10000; i++) {
				SIGMOID[i] = 1 / (1 + Math.exp(-((double) (i - 5000) / 500)));

			}
			for (int i = 0; i < 10000; i++) {
				TANH[i] = Math.tanh((double) (i - 5000) / 500);
			}
		}
	}

	public static double sigmoidt(double temp) {
		if (temp < -10)
			return SIGMOID[0];
		else if (temp >= 10)
			return SIGMOID[9999];
		else
			return SIGMOID[(int) (temp * 500) + 5000];
	}

	public static double tanht(double temp) {
		if (temp < -10)
			return TANH[0];
		else if (temp >= 10)
			return TANH[9999];
		else
			return TANH[(int) (temp * 500) + 5000];
		// return Math.tanh(temp);
	}

	public static double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}

	public static double step(double d) {
		double tbr = -10;
		// System.out.println(d);
		if (d < 0.0) {
			tbr = 0;
		} else if (d > 1.0) {
			tbr = 1;
		} else {
			BigDecimal result = new BigDecimal(d).setScale(6,
					BigDecimal.ROUND_HALF_UP);
			tbr = result.doubleValue();
		}
		// System.out.println(tbr);
		return tbr;
	}

	public static void shuffle(int[] targetArray) {
		int targetIndex;
		int swapBuffer;
		int maxInd = targetArray.length - 1;
		// System.out.println("Before Shufflying" +
		// Arrays.toString(targetArray));
		for (int curIndex = 0; curIndex < targetArray.length; curIndex++) {
			targetIndex = (int) Math.round(Math.random() * maxInd);
			swapBuffer = targetArray[curIndex];
			targetArray[curIndex] = targetArray[targetIndex];
			targetArray[targetIndex] = swapBuffer;
		}

		// System.out.println("After Shufflying" +
		// Arrays.toString(targetArray));
	}

	public static double normalise(double value, double max, double range) {
		return (value / max) * 2 * range - range;
	}

	public static double normaliseZeroToOne(double value, double min, double max) {
		return (value - min) / (max - min);
	}

	public static void normaliseActions(double[] actions) {
		double sum = 0.0;
		for (int j = 0; j < actions.length; j++) {

			sum += actions[j];

		}

		for (int j = 0; j < actions.length; j++) {

			actions[j] = actions[j] / sum;

		}
	}

}
