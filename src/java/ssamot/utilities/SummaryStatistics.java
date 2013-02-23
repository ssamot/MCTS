/*
 *
 * *** BEGIN LICENSE
 *  Copyright (C) 2012 Spyridon Samothrakis spyridon.samothrakis@gmail.com
 *  
 *  Based on a Simon Lucas (sml@essex.ac.uk) Class of a similiar name. 
 *  
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to model the statistics of a fix of numbers. For the
 * statistics we choose here it is not necessary to store all the numbers - just
 * keeping a running total of how many, the sum and the sum of the squares is
 * sufficient (plus max and min, for max and min).
 * 
 * This is a simpler version of StatisticalSummary that does not include
 * statistical tests, or the Watch class.
 * 
 * < modification of Simons class to support fractions! >
 */

public class SummaryStatistics implements Serializable {

	// following line can cause prog to hang - bug in Java?
	// protected long serialVersionUID = new
	// Double("-1490108905720833569").longValue();
	// protected long serialVersionUID = 123;
	public String name; // defaults to ""
	private double sum;
	private double sumsq;
	private double min;
	private double max;
	
	private double mean;
	private double sd;

	// trick class loader into loading this now
	// private static StatisticalTests dummy = new StatisticalTests();

	double n;
	boolean valid;
	ArrayList<Double> values;
	private double variance;

	public SummaryStatistics() {
		this("", false);

	}

	public SummaryStatistics(String name, boolean keepAlValues) {
		// System.out.println("Creating SS");
		this.name = name;
		n = 0;
		sum = 0;
		sumsq = 0;
		// ensure that the first number to be
		// added will fix up min and max to
		// be that number
		min = Double.POSITIVE_INFINITY;
		max = Double.NEGATIVE_INFINITY;
		
		// System.out.println("Finished Creating SS");
		valid = false;

		if (keepAlValues) {
			values = new ArrayList<Double>();
		} else {
			values = null;
		}
	}

	public final void reset() {
		n = 0;
		sum = 0;
		sumsq = 0;
		// ensure that the first number to be
		// added will fix up min and max to
		// be that number
		min = Double.POSITIVE_INFINITY;
		max = Double.NEGATIVE_INFINITY;
	}

	

	public double getMax() {
		return max;
	}

	public double getMin() {
		return min;
	}

	public double getMean() {
		if (!valid)
			computeStats();
	
		return mean;
	}

	// returns the sum of the squares of the differences
	// between the mean and the ith values
	public double sumSquareDiff() {
		return sumsq - n * getMean() * getMean();
	}

	private void computeStats() {
		if (!valid) {
			mean = sum / n;
			double num = sumsq - (n * mean * mean);
			if (num < 0) {
				// avoids tiny negative numbers possible through imprecision
				num = 0;
			}
			// System.out.println("Num = " + num);
			if (n > 1) {
				variance = (num / (n - 1));
			} else {
				variance = 0;
			}

			sd = Math.sqrt(variance);
			// System.out.println(" Test: sd = " + sd);
			// System.out.println(" Test: n = " + n);
			valid = true;
		}
	}

	public double getStandardDeviation() {
		if (!valid)
			computeStats();
		return sd;
	}

	public double getVariance() {
		if (!valid)
			computeStats();
		return variance;
	}

	public double getEVStdDev() {
		if (n == 0) {
			return 0.0;
		}
		// System.err.println(getVariance());
		return Math.sqrt(getVariance() / getN());
	}

	public double getSum() {
		return sum;
	}

	public double getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
		valid = false;
	}

	public double stdErr() {
		return getStandardDeviation() / Math.sqrt(n);
	}

	public void add(SummaryStatistics ss) {
		// implications for Watch?
		n += ss.n;

		sum += ss.sum;
		sumsq += ss.sumsq;
		max = Math.max(max, ss.max);
		min = Math.min(min, ss.min);
		if (values != null) {
			values.addAll(ss.values);
		}
		valid = false;
	}

	public SummaryStatistics copy() {
		SummaryStatistics copy = new SummaryStatistics("", false);
		if (values != null) {
			copy.values = new ArrayList<Double>();
		}
	
		copy.add(this);
		return copy;

	}

	public void  addValue(double d) {
		n++;
		if (values != null) {
			values.add(d);
		}
		sum += d;
		sumsq += d * d;
		min = Math.min(min, d);
		max = Math.max(max, d);
		valid = false;
	}

//	// adding a fraction of a trial!!!!
//	public void addValue(double d, double fraction) {
//
//		n += fraction;
//		sum += d;
//		sumsq += d * d;
//		min = Math.min(min, d);
//		max = Math.max(max, d);
//		valid = false;
//	}
//
//	public void removeValue(double d, double fraction) {
//
//		n -= fraction;
//		sum -= d;
//		sumsq -= d * d;
//		min = Math.min(min, d);
//		max = Math.max(max, d);
//		valid = false;
//	}

	// public double[] getConfidenceInterval(double significance) {
	// computeStats();
	// TDistributionImpl d = new TDistributionImpl(n-1);
	// double coeff = 0;
	// try {
	// coeff = d.inverseCumulativeProbability((significance+1)/2);
	// } catch (MathException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// double[] confidenceIntervals = new double[2];
	// //System.out.println(coeff);
	// confidenceIntervals[0] = getMean() - coeff *
	// getStandardDeviation()/Math.sqrt(n);
	// confidenceIntervals[1] = getMean() + coeff *
	// getStandardDeviation()/Math.sqrt(n);
	// //double hi = xbar + 1.96 * stddev;
	// return confidenceIntervals;
	// }

	public String toString() {
		String s = (name == null) ? "" : name + "\n";
		s += " min = " + getMin() + "\n" + " max = " + getMax() + "\n"
				+ " ave = " + getMean() + "\n" + " sd  = "
				+ getStandardDeviation() + "\n" + " se  = " + stdErr() + "\n"
				+ " sum  = " + sum + "\n" + " sumsq  = " + sumsq + "\n"
				+ " n   = " + n;
		return s;

	}

	public double getMedian() {
		if (values == null) {
			throw new RuntimeException("Null Values");
		}
		Collections.sort(values);
		int middle = values.size() / 2;
		if (values.size() % 2 == 1) {
			return values.get(middle);
		} else {
			return (values.get(middle - 1) + values.get(middle)) / 2.0;
		}
	}
}