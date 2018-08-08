package coursera.bio;

import static coursera.bio.PrefixMass.mass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class CyclicSpectrum {

	public static Function<String, List<Integer>> cyclicSpectrum = peptide -> {
		List<Integer> prefixMass = mass.apply(peptide);
		int peptideMass = prefixMass.get(prefixMass.size() - 1);

		List<Integer> cyclicSpectrum = new ArrayList<>();
		cyclicSpectrum.add(0);

		for (int i = 0; i < peptide.length(); ++i) {
			for (int j = i + 1; j <= peptide.length(); ++j) {
				cyclicSpectrum.add(prefixMass.get(j) - prefixMass.get(i));
				if (i > 0 && j < peptide.length()) {
					cyclicSpectrum.add(peptideMass - (prefixMass.get(j) - prefixMass.get(i)));
				}
			}
		}

		cyclicSpectrum.sort(Comparator.naturalOrder());

		return cyclicSpectrum;
	};
}
