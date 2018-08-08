package coursera.bio.iii;

import java.util.Arrays;

public class GlobalAlignment {

	public static Triplet<Integer, String, String> alignment(String v, String w) {
		int penalty = 5;

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

				int score = Scoring.getBlosum(String.valueOf(vc),
						String.valueOf(wc));

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

		int score = s[n][m];
		StringBuilder valign = new StringBuilder();
		StringBuilder walign = new StringBuilder();

		output(backtrack, v, w, valign, walign);

		return new Triplet<Integer, String, String>(score, valign.reverse()
				.toString(), walign.reverse().toString());
	}

	private static void output(int[][] backtrack, String v, String w,
			StringBuilder valign, StringBuilder walign) {
		int i = v.length();
		int j = w.length();
		while (i > 0 && j > 0) {
			switch (backtrack[i][j]) {
			case 1:
			case 2:
				valign.append(v.charAt(i - 1));
				walign.append(w.charAt(j - 1));
				--i;
				--j;
				break;
			case 3:
				walign.append("-");
				valign.append(v.charAt(i - 1));
				--i;
				break;
			case 4:
				walign.append(w.charAt(j - 1));
				valign.append("-");
				--j;
				break;
			default:
			}
		}

		while (i > 0) {
			walign.append("-");
			valign.append(v.charAt(i - 1));
			--i;
		}
		while (j > 0) {
			valign.append("-");
			walign.append(w.charAt(j - 1));
			--j;
		}
	}
}