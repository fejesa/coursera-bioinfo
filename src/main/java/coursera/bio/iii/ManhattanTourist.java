package coursera.bio.iii;

public class ManhattanTourist {

	public static int longestPath(int n, int m, int[][] down, int[][] right) {
		int[][] s = new int[n + 1][m + 1];
		for (int i = 1; i <= n; ++i) {
			s[i][0] = s[i - 1][0] + down[i - 1][0];
		}
		for (int j = 1; j <= m; ++j) {
			s[0][j] = s[0][j - 1] + right[0][j - 1];
		}
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				s[i][j] = Integer.max(s[i - 1][j] + down[i - 1][j], s[i][j - 1] + right[i][j - 1]);
			}
		}
		return s[n][m];
	}
}