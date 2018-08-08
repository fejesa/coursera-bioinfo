package coursera.bio;

import static coursera.bio.Neighbors.neighbors;
import static coursera.bio.Utils.getCommonElements;
import static coursera.bio.Utils.distinctKmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MotifEnumeration {

	public static TriFunction<List<String>, Integer, Integer, List<String>> findMotifs = (text, k, dist) -> internalFind(text, k, dist);

	private static List<String> internalFind(List<String> text, int k, int dist) {

		List<Set<String>> list = text
				.stream()
				.map(dna -> distinctKmers(dna, k)
								.stream()
								.map(s -> neighbors.apply(s, dist))
								.flatMap(Set::stream)
								.collect(Collectors.toSet()))
				.collect(Collectors.toList());

		List<String> patterns = new ArrayList<>(getCommonElements(list));
		patterns.sort(Comparator.naturalOrder());

		return patterns;
	}
}
