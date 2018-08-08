package coursera.bio;

import static coursera.bio.Hamming.distance;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;

public class MotifSearch {

	public static Function<List<String>, String> consensus = list -> {
		int k = list.get(0).length();
		char[] result = new char[k];
		for (int i = 0; i < k; ++i) {
			char[] array = new char[list.size()];
			for (int j = 0; j < array.length; ++j) {
				array[j] = list.get(j).charAt(i);
			}
			result[i] = findMost(array);
		}
		return String.valueOf(result);
	};

	private static char findMost(char[] array) {
		int[] counters = new int[4];
		int maxindex = 0;
		for (int i = 0; i < array.length; ++i) {

			int index = 0;
			if (array[i] == 'C') index = 1;
			else if (array[i] == 'G') index = 2;
			else if (array[i] == 'T') index = 3;

			counters[index] += 1;
			if (counters[index] > counters[maxindex]) {
				maxindex = index;
			}
		}

		if (maxindex == 0) return 'A';
		else if (maxindex == 1) return 'C';
		else if (maxindex == 2) return 'G';
		return 'T';
	}

	public static Function<List<String>, Double[][]> profile = list -> {
		int k = list.get(0).length();
		int t = list.size();
		double[][] count = new double[4][k];
		for (int i = 0; i < k; ++i) {
			for (String s : list) {
				char c = s.charAt(i);
				if (c == 'A') count[0][i] += 1d/t;
				else if (c == 'C') count[1][i] += 1d/t;
				else if (c == 'G') count[2][i] += 1d/t;
				else count[3][i] += 1d/t;
			}
		}
		Double[][] profile = new Double[4][k];
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < k; ++j) {
				profile[i][j] = count[i][j];
			}
		}
		return profile;
	};

	public static Function<List<String>, Double[][]> laplaceRuleProfile = list -> {
		int k = list.get(0).length();
		int t = list.size();
		double[][] count = new double[4][k];
		for (int i = 0; i < k; ++i) {
			for (String s : list) {
				char c = s.charAt(i);
				if (c == 'A') count[0][i] += 1;
				else if (c == 'C') count[1][i] += 1;
				else if (c == 'G') count[2][i] += 1;
				else count[3][i] += 1;
			}
		}
		Double[][] profile = new Double[4][k];
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < k; ++j) {
				profile[i][j] = (count[i][j] + 1d) / (t + 4);
			}
		}
		return profile;
	};

	public static ToIntBiFunction<List<String>, String> score = (list, consensus) -> {
		return list.stream().mapToInt(s -> distance.apply(s, consensus)).sum();
	};

	private static Function<Character, Integer> index = (c) -> {
		switch (c) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'G':
			return 2;
		case 'T':
			return 3;
		default:
			throw new IllegalArgumentException();
		}
	};

	public static BiFunction<String, Double[][], Double> probability = (kmer, matrix) -> {
		double pr = 1;
		for (int i = 0; i < kmer.length(); ++i) {
			pr *= matrix[index.apply(kmer.charAt(i))][i];
		}
		return pr;
	};

	public static TriFunction<String, Integer, Double[][], String> profileMostProbableKmer = (text, k, matrix) -> {
		String pkmer = null;
		double maxpr = 0;
		for (int i = 0; i <= text.length() - k; ++i) {
			String kmer = text.substring(i, i + k);
			double pr = probability.apply(kmer, matrix); 
			if (pr > maxpr) {
				maxpr = pr;
				pkmer = kmer;
			}
		}
		return pkmer != null ? pkmer : text.substring(0, k);
	};
}
