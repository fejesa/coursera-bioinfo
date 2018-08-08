package coursera.bio;

import static coursera.bio.Hamming.distance;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class PatternMatch {

	public static BiFunction<String, String, int[]> positions = (genome, pattern) -> {
		BiPredicate<String, String> equalTo = (p, f) -> p.equals(f);
		return find(genome, pattern, equalTo);
	};

	public static TriFunction<String, String, Integer, int[]> approximatePositions = (genome, pattern, dist) -> {
		BiPredicate<String, String> lessThanOrEqualTo = (p, f) -> distance.apply(p, f) <= dist;
		return find(genome, pattern, lessThanOrEqualTo);
	};

	public static TriFunction<String, String, Integer, Integer> approximatePatternCount = (genome, pattern, dist) -> {
		return approximatePositions.apply(genome, pattern, dist).length;
	};

	private static int[] find(String genome, String pattern, BiPredicate<String, String> predicate) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i <= genome.length() - pattern.length(); ++i) {
			String fragment = genome.substring(i, i + pattern.length());
			if (predicate.test(pattern,  fragment)) {
				result.add(i);
			}
		}
		return result.stream().mapToInt(Integer::intValue).toArray();
	}
}
