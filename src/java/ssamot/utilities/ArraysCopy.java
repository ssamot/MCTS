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

import java.util.Arrays;

import javax.smartcardio.Card;

public class ArraysCopy {
    public static void multiArrayCopy(int[][] source, int[][] destination) {
        for (int a = 0; a < source.length; a++) {
            System.arraycopy(source[a], 0, destination[a], 0, source[a].length);
        }
    }
    
    public static void multiArrayCopy(double[][] source, double[][] destination) {
        for (int a = 0; a < source.length; a++) {
            System.arraycopy(source[a], 0, destination[a], 0, source[a].length);
        }
    }
    
//    public static void multiArrayCopy(double[][] source, double[][] destination) {
//        
//        for (int a = 0; a < source.length; a++) {
//            // if(source[a]!=null) {
//            
//            System.arraycopy(source[a], 0, destination[a], 0, source[a].length);
//            // }
//        }
//    }
    public static void multiArrayCopy(float[][] source, float[][] destination) {
        
        for (int a = 0; a < source.length; a++) {
            // if(source[a]!=null) {
            
            //System.arraycopy(source[a], 0, destination[a], 0, source[a].length);
        	for (int i = 0; i < source[0].length; i++) {
				destination[a][i] = source[a][i];
			}
            // }
        }
    }
    
    public static int[] fastShallowArrayCopy(int[] oldArray) {
        int[] rt = new int[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            rt[i] = oldArray[i];
        }
        
        return rt;
    }
    
    public static Card[] fastShallowArrayCopy(Card[] oldArray) {
        Card[] rt = new Card[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            rt[i] = oldArray[i];
        }
        
        return rt;
    }
    
    public static double[] fastShallowArrayCopy(double[] oldArray) {
        double[] rt = new double[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            rt[i] = oldArray[i];
        }
        
        return rt;
    }
    
    public static void fastShallowArrayCopy(double[] source, double[] destination) {
        
        for (int i = 0; i < source.length; i++) {
            destination[i] = source[i];
        }
        
        //return rt;
    }
    
    public static boolean[] fastShallowArrayCopy(boolean[] oldArray) {
        boolean[] rt = new boolean[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            rt[i] = oldArray[i];
        }
        
        return rt;
    }
    
    public static short[] fastShallowArrayCopy(short[] oldArray) {
        short[] rt = new short[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            rt[i] = oldArray[i];
        }
        
        return rt;
    }

    public static void multiArrayCopy(short[][] source, short[][] destination) {
        for (int a = 0; a < source.length; a++) {
            System.arraycopy(source[a], 0, destination[a], 0, source[a].length);
        }
    }
    
    public static void multiArrayCopy(Card[][] source, Card[][] destination) {
        for (int a = 0; a < source.length; a++) {
            System.arraycopy(source[a], 0, destination[a], 0, source[a].length);
        }
    }
    
    
    public static double[] flattenTwoDArray(float[][] twodArray, double[] senses,  int startPosition) {
        int newArrayLength = twodArray[0].length*(twodArray.length-startPosition + senses.length);
        double[] flattendArray = new double[newArrayLength];
       //System.out.println(startPosition);
       // System.out.println(Arrays.toString(twodArray[0]));
       // System.out.println(Arrays.toString(twodArray[1]));
       // System.out.println(Arrays.toString(twodArray[2]));
       // System.out.println(Arrays.toString(twodArray[3]));
        int index = 0;
        
        for (int i = 0; i < senses.length ; i++) {
            flattendArray[index] = (float) senses[i];
            index++;
        }
        
        for (int i = startPosition; i < twodArray.length; i++) {
            for (int j = 0; j < twodArray[0].length; j++) {
                flattendArray[index] = twodArray[i][j];
                index++;
            }
        }
        //System.out.println("---");
        //System.out.println(Arrays.toString(flattendArray));

        //System.out.println("======================");
        return flattendArray;
    }
    
    public static <T> T[] concat(T[] first, T[] second) {
    	  T[] result = Arrays.copyOf(first, first.length + second.length);
    	  System.arraycopy(second, 0, result, first.length, second.length);
    	  return result;
    }
    
    public static double[] concat(double[] first, double[] second) {
  	  double[] result = Arrays.copyOf(first, first.length + second.length);
  	  System.arraycopy(second, 0, result, first.length, second.length);
  	  return result;
  }

	
    
}
