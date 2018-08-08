package coursera.bio.iii;

import java.util.Arrays;
import java.util.function.BiFunction;

public class Backtracking {

	private static BiFunction<String, String, int[][]> lcsBacktrack = (v, w) -> {

		int n = v.length();
		int m = w.length();
		
		int[][] s = new int[n + 1][m + 1];

		int[][] backtrack = new int[n + 1][m + 1];

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				char vc = v.charAt(i - 1);
				char wc = w.charAt(j - 1);

				int a = s[i - 1][j];
				int b = s[i][j - 1];
				int c = vc == wc ? s[i - 1][j - 1] + 1 : 0;

				s[i][j] = Arrays.asList(a, b, c).stream().reduce(Integer::max).get();

				if (s[i][j] == s[i - 1][j - 1] + 1 && vc == wc) {
					backtrack[i][j] = 3; // - matches
				}else if (s[i][j] == s[i - 1][j]) {
					backtrack[i][j] = 1; // "↓" - deletion
				} else if (s[i][j] == s[i][j - 1]) {
					backtrack[i][j] = 2; // "→" - insertion
				}
			}
		}

		return backtrack;
	};

	private static void outputLcs(int[][] backtrack, String v, int i, int j, StringBuilder builder) {
		if (i == 0 || j == 0) {
			return;
		}

		int value = backtrack[i][j];

		if (value == 1) {
			outputLcs(backtrack, v, i - 1, j, builder);
		} else if (value == 2) {
			outputLcs(backtrack, v, i, j - 1, builder);
		} else {
			outputLcs(backtrack, v, i - 1, j - 1, builder);
			//System.out.print(v.charAt(i - 1));
			builder.append(v.charAt(i - 1));
		}
	}

	public static BiFunction<String, String, String> lcs = (s, t) -> {
		int[][] backtrack = lcsBacktrack.apply(s, t);
		StringBuilder builder = new StringBuilder();
		outputLcs(backtrack, s, s.length(), t.length(), builder);
		return builder.toString();
	};
}