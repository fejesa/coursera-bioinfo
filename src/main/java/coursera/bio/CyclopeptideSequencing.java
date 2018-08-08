package coursera.bio;

import static coursera.bio.CyclicSpectrum.cyclicSpectrum;
import static coursera.bio.LinearSpectrum.linearSpectrum;
import static coursera.bio.Utils.expand;
import static coursera.bio.Utils.massTable;
import static coursera.bio.Utils.peptideMass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CyclopeptideSequencing {

	public static BiFunction<List<Integer>, List<Integer>, Boolean> isConsistent = (pspectrum, spectrum) -> {
		List<Integer> backup = new ArrayList<>(spectrum);
		for (Integer m : pspectrum) {
			if(!backup.remove(m)) {
				return false;
			}
		}
		return true;
	};

	public static Function<List<Integer>, List<String>> sequences = s -> {
		List<String> candidates = new ArrayList<>();
		List<String> peptides = new ArrayList<>();
		peptides.add("");

		while (!peptides.isEmpty()) {
			peptides = expand.apply(peptides);
			for (Iterator<String> i = peptides.iterator(); i.hasNext();) {
				String p = i.next();
				if (s.contains(peptideMass.apply(p))) {
					if (cyclicSpectrum.apply(p).equals(s)) {
						candidates.add(p);
						i.remove();
					}
				}
				if (!isConsistent.apply(linearSpectrum.apply(p), s)) {
					i.remove();
				}
			}
		}

		Set<List<Integer>> set = candidates.stream()
				.map(p -> p.chars()
						.mapToObj(c -> massTable.get((char) c))
						.collect(Collectors.toList()))
				.collect(Collectors.toSet());

		List<String> result = set.stream()
				.map(l -> l.stream()
						.map(m -> Integer.toString(m))
						.collect(Collectors.joining("-")))
				.collect(Collectors.toList());

		Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
		result.sort(comparator.reversed());
		return result;
	};

	public static List<String> subpepdites(String p) {
		String s = p + p.substring(0, p.length() - 2);

		List<String> list = new ArrayList<>();

		for (int i = 0; i < p.length(); ++i) {
			for (int j = 0; j < p.length() - 1; ++j) {
				list.add(s.substring(i, i + j + 1));
			}
		}
		return list;
	}

	public static List<Integer> theoreticalSpectrum(String p) {

		String s = p + p.substring(0, p.length() - 2);

		List<Integer> result = new ArrayList<>();
		result.add(0);
		result.add(peptideMass.apply(p));

		for (int i = 0; i < p.length(); ++i) {
			for (int j = 0; j < p.length() - 1; ++j) {
				String sub = s.substring(i, i + j + 1);
				result.add(peptideMass.apply(sub));
			}
		}
		result.sort(Comparator.naturalOrder());
		return result;
	}

	public static int countSubpepdites(int p) {
		int counter = p;
		counter += p * (p - 2);
		return counter;
	}
}