package coursera.bio.iii;

import java.util.Arrays;
import java.util.function.BiFunction;

public class MiddleEdgeInLinearSpace {

	private static int getIndexMax(int[] a, int max) {
		for (int i = 0; i < a.length; ++i) {
			if (a[i] == max) {
				return i;
			}
		}
		return -1;
	}

	private static int[] lastColumnScore(String a, String b, int indel) {
		// compute the last column score of a,b global alignment
		int n = a.length();
		int m = b.length();
		int[][] s = new int[2][n + 1];
		for (int i = 0; i < n + 1; ++i) {
			s[0][i] = -indel * i;
		}
		if (m == 0) {
			// handling special one-column-only case
			return s[0];
		}
		for (int j = 0; j < m; ++j) {
			s[1][0] = s[0][0] - indel;
			for (int i = 0; i < n; ++i) {
				int score = Scoring.getBlosum(String.valueOf(a.charAt(i)), String.valueOf(b.charAt(j)));
				int x = s[1][i] - indel;
				int y = s[0][i + 1] - indel;
				int z = s[0][i] + score;
				s[1][i + 1] = Arrays.asList(x, y, z).stream().reduce(Integer::max).get();
			}
			s[0] = s[1];
		}
		return s[1];
	}
	    		
	public static BiFunction<String, String, Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>>> middleEdge = (v, w) -> {
		int indel = 5;
		int n = v.length();
		int m = w.length();
		int c = (m - 1) / 2;
		String wleft = w.substring(0, c);
		String wmid = w.substring(c, c + 1);
		String wright = w.substring(c + 1);

		// compute score of graph left-part
		int[] len1 = lastColumnScore(v, wleft, indel);
		int[] len2 = lastColumnScore(new StringBuilder(v).reverse().toString(), new StringBuilder(wright).reverse().toString(), indel);
		for (int i = 0; i < len2.length / 2; ++i) {
			int tmp = len2[i];
			len2[i] = len2[len2.length - i - 1];
			len2[len2.length - i - 1] = tmp;
		}
		// compute horizontal max score for any i-row [0,n] at column c 
		int[] hs = new int[n + 1];
		for (int i = 0; i < n + 1; ++i) {
			hs[i] = len1[i] + len2[i] - indel;
		}
		// compute diagonal max score for any i-row [0,n-1] at column c 
		int[] ds = new int[n];
		for (int i = 0; i < n; ++i) {
			ds[i] = len1[i] + len2[i + 1] + Scoring.getBlosum(String.valueOf(v.charAt(i)), wmid);
		}
		int hmax = Arrays.stream(hs).reduce(Integer::max).getAsInt();
		int dmax = Arrays.stream(ds).reduce(Integer::max).getAsInt();
		int i = 0, j = c, k = 0, l = c + 1;
		if (hmax > dmax) {
			// horizontal edge
			i = getIndexMax(hs, hmax);
			k = i;
		} else {
			i = getIndexMax(ds, dmax);
			k = i + 1;
		}
		return new Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>>(new Tuple<Integer, Integer>(i, j), new Tuple<Integer, Integer>(k, l));
	};
}