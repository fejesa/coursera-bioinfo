package coursera.bio;

import static coursera.bio.Hamming.distance;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Neighbors {

	public static BiFunction<String, Integer, Set<String>> neighbors = (pattern, dist) -> internalNeighbors(pattern, dist);

	private static Set<String> NUCLEOTIDES = new HashSet<>(Arrays.asList("A", "C", "T", "G"));

	private static Set<String> internalNeighbors(String pattern, int d) {
		if (d == 0) {
			return new HashSet<String>(Arrays.asList(pattern));
		}
		if (pattern.length() == 1) {
			return NUCLEOTIDES;
		}
		Set<String> suffixNeighbors = internalNeighbors(suffix.apply(pattern), d);
		Set<String> neighborhood = new HashSet<>();
		for (String sn : suffixNeighbors) {
			if (distance.apply(suffix.apply(pattern), sn) < d) {
				for (String n : NUCLEOTIDES) {
					neighborhood.add(n + sn);
				}
			} else {
				neighborhood.add(firstSymbol.apply(pattern) + sn);
			}
		}
		return neighborhood;
	}

	private static Function<String, Character> firstSymbol = (pattern) -> pattern.charAt(0);

	private static UnaryOperator<String> suffix = (pattern) -> pattern.length() == 0 ? pattern : pattern.substring(1);
}