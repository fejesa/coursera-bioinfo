package coursera.bio.iii;

import java.util.Arrays;
import java.util.List;

public class EditDistance {

	private static final List<String> acids = Arrays.asList("A", "C", "D", "E",
			"F", "G", "H", "I", "K", "L", "M", "N", "P", "Q", "R", "S", "T",
			"V", "W", "Y");

	private static final int[][] scoring;

	static {
		scoring = new int[20][20];
		for (int i = 0; i < 20; ++i) {
			for (int j = 0; j < 20; ++j) {
				scoring[i][j] = i == j ? 0 : -1;
			}
		}
	}

	private static int getScore(String column, String row) {
		int i = -1, j = -1;
		for (int k = 0; k < acids.size(); ++k) {
			if (acids.get(k).equals(column)) {
				i = k;
			}
			if (acids.get(k).equals(row)) {
				j = k;
			}
		}
		return scoring[i][j];
	}

	public static Triplet<Integer, String, String> alignment(String v, String w) {
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

				int score = getScore(String.valueOf(vc), String.valueOf(wc));

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

		return new Triplet<Integer, String, String>(-score, "", "");
	}
}
