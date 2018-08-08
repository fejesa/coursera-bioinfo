package coursera.bio.iii;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import coursera.bio.FileUtil;

public class Scoring {

	private static final List<String> blosumAcids;
	private static final List<String> pamAcids;

	private final static int[][] blosum62;
	private final static int[][] pam250;

	static {
		try {

			blosumAcids = acids("BLOSUM62.txt");
			blosum62 = matrix("BLOSUM62.txt");
			pamAcids = acids("PAM250_1.txt");
			pam250 = matrix("PAM250_1.txt");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static int[][] matrix(String file) throws Exception {
		List<String> lines = FileUtil.loadFile(file);
		String[] first = lines.get(0).split(" +");
		List<String> acids = Arrays.stream(first).filter(s -> !s.isEmpty())
				.collect(Collectors.toList());
		int[][] matrix = new int[acids.size()][acids.size()];

		for (int i = 0; i < acids.size(); ++i) {
			String line = lines.get(i + 1);
			String[] values = line.split(" +");
			for (int j = 0; j < acids.size(); ++j) {
				matrix[i][j] = Integer.parseInt(values[j + 1].trim());
			}
		}

		return matrix;
	}

	private static List<String> acids(String file) throws Exception {
		List<String> lines = FileUtil.loadFile(file);
		String[] first = lines.get(0).split(" +");
		return Arrays.stream(first).filter(s -> !s.isEmpty())
				.collect(Collectors.toList());
	}

	public static int getPam(String column, String row) {
		int i = -1, j = -1;
		for (int k = 0; k < pamAcids.size(); ++k) {
			if (pamAcids.get(k).equals(column)) {
				i = k;
			}
			if (pamAcids.get(k).equals(row)) {
				j = k;
			}
		}
		return pam250[i][j];
	}

	public static int getBlosum(String column, String row) {
		int i = -1, j = -1;
		for (int k = 0; k < blosumAcids.size(); ++k) {
			if (blosumAcids.get(k).equals(column)) {
				i = k;
			}
			if (blosumAcids.get(k).equals(row)) {
				j = k;
			}
		}
		return blosum62[i][j];
	}

	public static void main(String[] args) {
		System.out.println(blosum62[0][0]);
		System.out
				.println(blosum62[blosumAcids.size() - 1][blosumAcids.size() - 1]);
	}
}
