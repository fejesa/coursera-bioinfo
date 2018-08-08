package coursera.bio;

import static coursera.bio.PrefixMass.mass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class LinearSpectrum {

	public static Function<String, List<Integer>> linearSpectrum = peptide -> {
		List<Integer> prefixMass = mass.apply(peptide);

		List<Integer> linearSpectrum = new ArrayList<>();
		linearSpectrum.add(0);

		for (int i = 0; i < peptide.length(); ++i) {
			for (int j = i + 1; j <= peptide.length(); ++j) {
				linearSpectrum.add(prefixMass.get(j) - prefixMass.get(i));
			}
		}
		linearSpectrum.sort(Comparator.naturalOrder());
		return linearSpectrum;
	};
}
