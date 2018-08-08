package coursera.bio;

import static coursera.bio.Hamming.distance;
import static coursera.bio.Utils.distinctKmers;

import java.util.List;
import java.util.function.BiFunction;

public class MedianString {

	public static BiFunction<String, List<String>, Integer> distanceBetweenPatternAndStrings = (pattern, dna) -> {
		int k = pattern.length();

		return dna.stream()
				.mapToInt(text -> {
					return distinctKmers(text, k).stream()
							.map(p -> distance.apply(pattern, p))
							.reduce(Integer.MAX_VALUE, (a, b) -> a > b ? b : a);
		}).sum();
	};

	public static BiFunction<List<String>, Integer, String> medianString = (dna, k) -> {
		int distance = Integer.MAX_VALUE;
		String median = null;
		for (String kmer : Utils.repetitions(k)) {
			int d = distanceBetweenPatternAndStrings.apply(kmer, dna);
			if (d < distance) {
				distance = d;
				median = kmer;
			}
		}
		return median;
	};
}