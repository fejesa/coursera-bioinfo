package coursera.bio;

import static coursera.bio.CyclicSpectrum.cyclicSpectrum;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class CyclopeptideScoring {

	public static BiFunction<String, List<Integer>, Integer> score = (peptide, spec) -> {
		List<Integer> bckSpec = new ArrayList<>(spec);
		List<Integer> cspec = cyclicSpectrum.apply(peptide);

		Predicate<Integer> contains = p -> bckSpec.remove(p);

		return cspec.stream().reduce(0, (s, m) -> {
			if (contains.test(m)) return s + 1; else return s;
		});
	};
}