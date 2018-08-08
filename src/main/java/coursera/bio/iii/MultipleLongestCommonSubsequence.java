package coursera.bio.iii;

import java.util.Arrays;

public class MultipleLongestCommonSubsequence {

	public static class ThreeWayAlignmentResult {
		public final int longest;
		public final String alignA;
		public final String alignB;
		public final String alignC;

		public ThreeWayAlignmentResult(int longest, String alignA,
				String alignB, String alignC) {
			this.longest = longest;
			this.alignA = alignA;
			this.alignB = alignB;
			this.alignC = alignC;
		}
	}

	public static ThreeWayAlignmentResult align(String v, String w, String u) {
		int n = v.length(); int m = w.length(); int p = u.length();
		int[][][] s = new int[n + 1][m + 1][p + 1];
		int[][][] backtrack = new int[n + 1][m + 1][p + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				for (int k = 1; k <= p; ++k) {
					char vc = v.charAt(i - 1);
					char wc = w.charAt(j - 1);
					char uc = u.charAt(k - 1);
					int score = vc == wc && vc == uc ? 1 : 0;

					int[] ps = new int[] {
							s[i - 1][j][k],
							s[i][j - 1][k],
							s[i][j][k - 1],
							s[i - 1][j - 1][k],
							s[i - 1][j][k - 1],
							s[i][j - 1][k - 1],
							s[i - 1][j - 1][k - 1] + score};
					s[i][j][k] = Arrays.stream(ps).reduce(Integer::max).getAsInt();
					for (int x = 0; x < ps.length; ++x) {
						if (ps[x] == s[i][j][k]) {
							backtrack[i][j][k] = x;
							//break;
						}
					}
				}
			}
		}
		
		return output(v, w, u, backtrack, s[n][m][p]);
	}

	private static ThreeWayAlignmentResult output(String v, String w, String u, int[][][] backtrack, int smax) {
		int n = v.length(); int m = w.length(); int p = u.length();
		StringBuilder valign = new StringBuilder();
		StringBuilder walign = new StringBuilder();
		StringBuilder ualign = new StringBuilder();
		int i = n; int j = m; int k = p;
		//--i; --j; --k;
		
		while (i > 0 && j > 0 && k > 0) {
			int value = backtrack[i][j][k];
			switch (value) {
			case 0:
				valign.append(v.charAt(i - 1));
				walign.append('-');
				ualign.append('-');
				--i; break;
			case 1:
				valign.append('-');
				walign.append(w.charAt(j - 1));
				ualign.append('-');
				--j; break;
			case 2:
				valign.append('-');
				walign.append('-');
				ualign.append(u.charAt(k - 1));
				--k; break;
			case 3:
				valign.append(v.charAt(i - 1));
				walign.append(w.charAt(j - 1));
				ualign.append('-');
				--i; --j; break;
			case 4:
				valign.append(v.charAt(i - 1));
				walign.append('-');
				ualign.append(u.charAt(k - 1));
				--i; --k; break;
			case 5:
				valign.append('-');
				walign.append(w.charAt(j - 1));
				ualign.append(u.charAt(k - 1));
				--j; --k; break;
			case 6:
				valign.append(v.charAt(i - 1));
				walign.append(w.charAt(j - 1));
				ualign.append(u.charAt(k - 1));
				--i; --j; --k; break;
			default:
				throw new RuntimeException();
			}
		}

		while (i > 0) {
			walign.append("-");
			ualign.append('-');
			valign.append(v.charAt(i - 1));
			--i;
		}
		while (j > 0) {
			valign.append("-");
			ualign.append('-');
			walign.append(w.charAt(j - 1));
			--j;
		}
		while (k > 0) {
			valign.append("-");
			walign.append('-');
			ualign.append(u.charAt(k - 1));
			--k;
		}
		return new ThreeWayAlignmentResult(smax, valign.reverse().toString(), walign.reverse().toString(), ualign.reverse().toString());
	}
}