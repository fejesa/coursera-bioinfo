package coursera.bio.iii;

import static coursera.bio.Utils.stringToInts;
import static coursera.bio.iii.ManhattanTourist.longestPath;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class ManhattanTouristTest {

	@Test
	public void longest() {
		int[][] down = new int[][] {
				{1, 0, 2, 4, 3},
			    {4, 6, 5, 2, 1},
			    {4, 4, 5, 2, 1},
			    {5, 6, 8, 5, 3}
		};
		int[][] right = new int[][] {
				{3, 2, 4, 0},
				{3, 2, 4, 2},
				{0, 7, 3, 3},
				{3, 3, 0, 2},
				{1, 3, 2, 2}
		};

		assertEquals(34, longestPath(4, 4, down, right));
	}

	@Test
	public void longestExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("longest_path_1.txt");
		String[] numbers = lines.get(1).split(" ");
		int n = Integer.parseInt(numbers[0]);
		int m = Integer.parseInt(numbers[1]);

		int[][] down = matrix(n, m + 1, lines.subList(2, n + 2));
		int[][] right = matrix(n + 1, m, lines.subList(n + 3, n + n + 4));

		int path = Integer.parseInt(lines.get(lines.size() - 1));

		assertEquals(path, longestPath(n, m, down, right));
	}

	@Test
	public void longestDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_261_9.txt");
		String[] numbers = lines.get(0).split(" ");
		int n = Integer.parseInt(numbers[0]);
		int m = Integer.parseInt(numbers[1]);

		int[][] down = matrix(n, m + 1, lines.subList(1, n + 1));
		int[][] right = matrix(n + 1, m, lines.subList(n + 2, n + n + 3));

		assertEquals(97, longestPath(n, m, down, right));
	}
	
	private int[][] matrix(int n, int m, List<String> lines) {
		int[][] array = new int[n][m];
		for (int i = 0; i < n; ++i) {
			List<Integer> list = stringToInts.apply(lines.get(i));
			for (int j = 0; j < m; ++j) {
				array[i][j] = list.get(j);
			}
		}
		return array;
	}
}
