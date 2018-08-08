package coursera.bio;

import static coursera.bio.Neighbors.neighbors;
import static coursera.bio.PatternMatch.approximatePatternCount;
import static coursera.bio.PatternUtil.internalNumberToPattern;
import static coursera.bio.PatternUtil.internalPatternToNumber;
import static coursera.bio.PatternUtil.patternCount;
import static coursera.bio.ReverseComplement.reverseComplement;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiFunction;

public class FrequentWords {

	public static BiFunction<String, Integer, Set<String>> frequentWords = (text, k) -> internalFrequentWords(text, k);

	private static Set<String> internalFrequentWords(String text, int k) {
		Set<String> frequentPatterns = new LinkedHashSet<>();
		int max = 0;
		int[] counts = new int[text.length() - k];
		for (int i = 0; i < text.length() - k; ++i) {
			String pattern = text.substring(i, i + k);
			counts[i] = patternCount.apply(text, pattern);
			max = Math.max(counts[i], max);
		}
		for (int i = 0; i < text.length() - k; ++i) {
			if (counts[i] == max) {
				frequentPatterns.add(text.substring(i, i + k));
			}
		}
		return frequentPatterns;
	}

	public static BiFunction<String, Integer, Set<String>> frequentWordsBySorting = (text, k) -> internalFrequentWordsBySorting(text, k);

	public static TriFunction<String, Integer, Integer, Set<String>> frequentWordsWithMismatches = (genome, k, dist) -> {
		Set<String> list = new HashSet<>();
		for (int i = 0, max = 0; i < genome.length() - k; ++i) {
			String next = genome.substring(i, i + k);
			for (String n : neighbors.apply(next, dist)) {
				int pc = approximatePatternCount.apply(genome, n, dist);
				if (pc == max) {
					list.add(n);
				} else if (pc > max) {
					list = new HashSet<>();
					list.add(n);
					max = pc;
				}
			}
		}
		return list;
	};

	public static TriFunction<String, Integer, Integer, Set<String>> frequentWordsWithMismatchesAndReverseComplements = (genome, k, dist) -> {
		Set<String> list = new HashSet<>();
		for (int i = 0, max = 0; i < genome.length() - k; ++i) {
			String next = genome.substring(i, i + k);
			for (String n : neighbors.apply(next, dist)) {
				String rc = reverseComplement.apply(n);
				int pc = approximatePatternCount.apply(genome, n, dist);
				int rcpc = approximatePatternCount.apply(genome, rc, dist); 
				int sum = pc + rcpc;
				if (sum == max) {
					list.add(n);list.add(rc);
				} else if (sum > max) {
					list = new HashSet<>();
					list.add(n);list.add(rc);
					max = sum;
				}
			}
		}
		return list;
	};

	private static Set<String> internalFrequentWordsBySorting(String text, int k) {

		int[] counts = new int[text.length() - k + 1];
		int[] indexes = new int[text.length() - k + 1];

		for (int i = 0; i <= text.length() - k; ++i) {
			String pattern = text.substring(i, i + k);
			indexes[i] = internalPatternToNumber(pattern);
			counts[i] = 1;
		}
		Arrays.sort(indexes);

		for (int i = 1; i <= text.length() - k; ++i) {
			if (indexes[i] == indexes[i - 1]) {
				counts[i] = counts[i - 1] + 1;
			}
		}

		int max = counts[0];
		for (int i = 1; i < counts.length; ++i) {
			max = Math.max(counts[i], max);
		}

		Set<String> result = new HashSet<>();
		for (int i = 0; i < text.length() - k; ++i) {
			if (counts[i] == max) {
				String pattern = internalNumberToPattern(indexes[i], k);
				result.add(pattern);
			}
		}
		return result;
	}
}
