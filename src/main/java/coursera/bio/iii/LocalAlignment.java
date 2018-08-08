package coursera.bio.iii;

import java.util.Arrays;

public class LocalAlignment {

	public static Triplet<Integer, String, String> alignment(String v, String w) {
		//int penalty = 5;
		int penalty = 1;

		int n = v.length();
		int m = w.length();

		int[][] s = new int[n + 1][m + 1];
		for (int i = 0; i <= n; ++i) {
			s[i][0] = -penalty * i;
		}
		for (int j = 0; j <= m; ++j) {
			s[0][j] = -penalty * j;
		}

		int[][] backtrack = new int[n + 1][m + 1];

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				char vc = v.charAt(i - 1);
				char wc = w.charAt(j - 1);

//				int score = Scoring.getPam(String.valueOf(vc),
//						String.valueOf(wc));
				int score = vc == wc ? 1 : -3;

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

				// allow free-taxi ride from the source e.g. min s = 0
				// thus ensure s[i+1, j+1] > 0
				if (s[i][j] < 0) {
					s[i][j] = 0;
					backtrack[i][j] = 5; // free taxi ride
				}
			}
		}

		int sn = 0, sm = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n + 1; ++i) {
			for (int j = 0; j < m + 1; ++j) {
				if (s[i][j] > max) {
					max = s[i][j];
					sn = i;
					sm = j;
				}
			}
		}

		int score = max;
		StringBuilder valign = new StringBuilder();
		StringBuilder walign = new StringBuilder();

		output(backtrack, sn, sm, v, w, valign, walign);

		return new Triplet<Integer, String, String>(score, valign.reverse()
				.toString(), walign.reverse().toString());
	}

	private static void output(int[][] backtrack, int i, int j, String v, String w,
			StringBuilder valign, StringBuilder walign) {

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
