package coursera.bio;

import static coursera.bio.LinearSpectrum.linearSpectrum;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class LinearScoring {

	public static BiFunction<String, List<Integer>, Integer> linearScoring = (peptide, spec) -> {
		List<Integer> bckSpec = new ArrayList<>(spec);
		List<Integer> cspec = linearSpectrum.apply(peptide);

		Predicate<Integer> contains = p -> bckSpec.remove(p);

		return cspec.stream().reduce(0, (s, m) -> {
			if (contains.test(m)) return s + 1; else return s;
		});
	};
}
