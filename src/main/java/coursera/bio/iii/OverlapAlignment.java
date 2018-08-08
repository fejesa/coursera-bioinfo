package coursera.bio.iii;

import java.util.Arrays;

public class OverlapAlignment {

	public static Triplet<Integer, String, String> alignment(String v, String w) {
		int penalty = 2;

		int n = v.length();
		int m = w.length();

		int[][] s = new int[n + 1][m + 1];
		for (int i = 0; i <= n; ++i) {
			// free-ride taxi from source on v to take account of of the
			// overlap-alignment-nature for a prefix-source
			s[i][0] = 0;
		}
		for (int j = 0; j <= m; ++j) {
			s[0][j] = -penalty * j;
		}

		int[][] backtrack = new int[n + 1][m + 1];

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				char vc = v.charAt(i - 1);
				char wc = w.charAt(j - 1);

				int score = vc == wc ? 1 : -penalty;

				int a = s[i - 1][j] - penalty;
				int b = s[i][j - 1] - penalty;
				int c = s[i - 1][j - 1] + score;

				s[i][j] = Arrays.asList(a, b, c).stream().reduce(Integer::max)
						.get();

				if (s[i][j] == c && vc == wc) {
					backtrack[i][j] = 1; // - matches
				} else if (s[i][j] == c && vc != wc) {
					backtrack[i][j] = 2; // - mismatches
				} else if (s[i][j] == a) {
					backtrack[i][j] = 3; // "↓" - deletion
				} else if (s[i][j] == b) {
					backtrack[i][j] = 4; // "→" - insertion
				}

			}
		}

		// for overlap alignment 
	    // allow free-taxi ride from the end of source to the sink on v
	    // thus ensure s[i, j] is maximum over the last row of graph
		int sm = 0;
		int max = Integer.MIN_VALUE;
		for (int j = 0; j < m + 1; ++j) {
			if (s[n][j] > max) {
				max = s[n][j];
				sm = j;
			}
		}

		int score = s[n][sm];
		StringBuilder valign = new StringBuilder();
		StringBuilder walign = new StringBuilder();

		output(backtrack, n, sm, v, w, valign, walign);

		return new Triplet<Integer, String, String>(score, valign.reverse()
				.toString(), walign.reverse().toString());
	}

	private static void output(int[][] backtrack, int i, int j, String v,
			String w, StringBuilder valign, StringBuilder walign) {

		while (i > 0 && j > 0) {
			int value = backtrack[i][j];

			if (value == 1 || value == 2) {
				valign.append(v.charAt(i - 1));
				walign.append(w.charAt(j - 1));
				--i;
				--j;
			} else if (value == 3) {
				walign.append("-");
				valign.append(v.charAt(i - 1));
				--i;
			} else if (value == 4) {
				walign.append(w.charAt(j - 1));
				valign.append("-");
				--j;
			} else if (value == 5) {
				break;
			}
		}
	}
}
