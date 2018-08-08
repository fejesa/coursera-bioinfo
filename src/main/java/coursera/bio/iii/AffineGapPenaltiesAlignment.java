package coursera.bio.iii;

import java.util.Arrays;

public class AffineGapPenaltiesAlignment {

	public static Triplet<Integer, String, String> alignment(String v, String w) {
		int sigma = 11;
		int epsilon = 1;

		int n = v.length();
		int m = w.length();

		int[][] middle = new int[n + 1][m + 1];
		int[][] lower = new int[n + 1][m + 1];
		int[][] upper = new int[n + 1][m + 1];
		for (int i = 0; i <= n; ++i) {
			middle[i][0] = -sigma * i;
			lower[i][0] = -epsilon * i;
		}
		for (int j = 0; j <= m; ++j) {
			middle[0][j] = -sigma * j;
			lower[0][j] = -epsilon * j;
		}

		int[][][] backtrack = new int[n + 1][m + 1][3];
		
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				char vc = v.charAt(i - 1);
				char wc = w.charAt(j - 1);

				int score = Scoring.getBlosum(String.valueOf(vc), String.valueOf(wc));
				lower[i][j] = Integer.max(lower[i - 1][j] - epsilon, middle[i - 1][j] - sigma);
				upper[i][j] = Integer.max(upper[i][j - 1] - epsilon, middle[i][ j - 1] - sigma);
				middle[i][j] = Arrays.asList(lower[i][j], upper[i][j], middle[i - 1][j - 1] + score).stream().reduce(Integer::max).get();

				if (lower[i][j] == lower[i - 1][j] - epsilon) {
					backtrack[i][j][0] = 1; // | continuing vertical gap
				} else {
					backtrack[i][j][0] = 2; // + opening vertical gap
				}

				if (upper[i][j] == lower[i][j - 1] - epsilon) {
					backtrack[i][j][2] = 3; // - continuing horizontal gap
				} else {
					backtrack[i][j][2] = 2; // + opening vertical gap
				}

				if (middle[i][j] == lower[i][j]) {
					backtrack[i][j][1] = 1; // | vertical gap closing
				} else if (middle[i][j] == upper[i][j]) {
					backtrack[i][j][1] = 3; // - horizontal gap closing
				} else if (middle[i][j] == middle[i - 1][j - 1] + score) {
					backtrack[i][j][1] = vc == wc ? 4 : 5; // / 4 or * 5
				}
			}
		}

		int score = middle[n][m];
		StringBuilder valign = new StringBuilder();
		StringBuilder walign = new StringBuilder();

		output(backtrack, v, w, valign, walign);

		return new Triplet<Integer, String, String>(score, valign.reverse()
				.toString(), walign.reverse().toString());
	}

	private static void output(int[][][] backtrack, String v, String w,
			StringBuilder valign, StringBuilder walign) {
		// '|': 1   '+': 2   '-': 3   
//		if (level == 1):
//            if backtrack[i, j, level] == '|':
//                level = 0
//            elif backtrack[i, j, level] == '-':
//                level = 2
//            else:
//                walign.append(w[j])
//                valign.append(v[i])
//                i -= 1
//                j -= 1
//        elif (level == 0):
//            # back tracking a vertical gap
//            walign.append('-')
//            valign.append(v[i])
//            if (backtrack[i, j, level] == '+'):
//                # back tracking a gap opening
//                level = 1
//            i -=1
//        elif (level == 2):
//            # back tracking an horizontal gap
//            walign.append(w[j])
//            valign.append('-')
//            if (backtrack[i, j, level] == '+'):
//                # back tracking a gap opening
//                level = 1
//            j -=1
		
		int i = v.length();
		int j = w.length();
		int level = 1;
		while (i > 0 && j > 0) {
			if (level == 1) {
				if (backtrack[i][j][level] == 1) {
					level = 0;
				} else if (backtrack[i][j][level] == 3) {
					level = 2;
				} else {
					valign.append(v.charAt(i - 1));
					walign.append(w.charAt(j - 1));
					--i;
					--j;
				}
			} else if (level == 0) {
				// back tracking a vertical gap
				walign.append("-");
				valign.append(v.charAt(i - 1));
				if (backtrack[i][j][level] == 2) {
					// back tracking a gap opening
					level = 1;
				}
				--i;
			} else {
				//back tracking an horizontal gap
				walign.append(w.charAt(j - 1));
				valign.append("-");
				if (backtrack[i][j][level] == 2) {
					// back tracking a gap opening
					level = 1;
				}
				--j;
			}
		}

//		while (i > 0) {
//			walign.append("-");
//			valign.append(v.charAt(i - 1));
//			--i;
//		}
//		while (j > 0) {
//			valign.append("-");
//			walign.append(w.charAt(j - 1));
//			--j;
//		}
	}
}
