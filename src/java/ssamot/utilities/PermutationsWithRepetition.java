package ssamot.utilities;
/* Copyright (c) 2013 the authors listed at the following URL, and/or
the authors of referenced articles or incorporated external code:
http://en.literateprograms.org/Permutations_with_repetition_(Java)?action=history&offset=20080109002711

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Retrieved from: http://en.literateprograms.org/Permutations_with_repetition_(Java)?oldid=11971
*/

import java.util.ArrayList;
import java.util.List;

public class PermutationsWithRepetition {
	private String a;
	private int n;
	public PermutationsWithRepetition(String a, int n) {
		this.a = a;
		this.n = n;
	}
	public List<String> getVariations() {
		int l = a.length();
		int permutations = (int) Math.pow(l, n);
		char[][] table = new char[permutations][n];
		
		for (int x = 0; x < n; x++) {
		    int t2 = (int) Math.pow(l, x);
		    for (int p1 = 0; p1 < permutations;) {
		        for (int al = 0; al < l; al++) {
		            for (int p2 = 0; p2 < t2; p2++) {
		                table[p1][x] = a.charAt(al);
		                p1++;
		            }
		        }
		    }
		}

		
		List<String> result = new ArrayList<String>();
		for (char[] permutation : table) {
		    result.add(new String(permutation));
		}
		return result;
	}
	public static void main(String[] args) {
		
		PermutationsWithRepetition gen = new PermutationsWithRepetition("0123", 8);
		List<String> v = gen.getVariations();
		List<String> v2 = new ArrayList<String>();
		for (String s : v) {
		    //System.out.println(s);
			int p1 = Integer.parseInt(s.substring(0,1));
			int p2 = Integer.parseInt(s.substring(1,2));
			int p3 = Integer.parseInt(s.substring(2,3));
			int p4 = Integer.parseInt(s.substring(3,4));
			int p5 = Integer.parseInt(s.substring(4,5));
			int p6 = Integer.parseInt(s.substring(5,6));
			int p7 = Integer.parseInt(s.substring(6,7));
			int p8 = Integer.parseInt(s.substring(7,8));
			if(p1+p5 < 5 && p2 + p6 < 5 && p3 + p7 < 5 && p4 + p8 < 5) {
				v2.add(s);
			}
			
//			int p1 = Integer.parseInt(s.substring(0,1));
//			int p2 = Integer.parseInt(s.substring(1,2));
//			
//			if(p1+p2 < 5) {
//				v2.add(s);
//			}
			
		}
		
		for (String s : v2) {
		    System.out.println(s);
			
		}
		
		System.err.println(v2.size());
	}
}
