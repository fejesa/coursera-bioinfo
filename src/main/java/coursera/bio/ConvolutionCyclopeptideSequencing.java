package coursera.bio;

import static coursera.bio.LeaderboardCyclopeptideSequencing.leaderboardSequence;
import static coursera.bio.SpectralConvolution.convolution;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvolutionCyclopeptideSequencing {

	private static Function<List<Integer>, Map<Character, Integer>> massTable = s -> {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.size(); ++i) {
			map.put(chars.charAt(i), s.get(i));
		}
		return map;
	};

	public static TriFunction<List<Integer>, Integer, Integer, String> convolutionSequencing = (spec, m, n) -> {
		List<Integer> list = convolution.apply(spec);
		Map<Integer, Long> counted = list
				.stream()
				.collect(Collectors.groupingBy(o -> o, Collectors.counting()));

		Comparator<Entry<Integer, Long>> byValue = (entry1, entry2) -> {
			return entry1.getValue().compareTo(entry2.getValue());
		};

		List<Entry<Integer, Long>> ordered = counted
				.entrySet()
				.stream()
				.filter(e -> e.getKey() >= 57 && e.getKey() <= 200)
				.sorted(byValue.reversed())
				.collect(Collectors.toList());

		Long barrier = ordered.get(m - 1).getValue();
		List<Integer> masses = ordered
				.stream()
				.filter(e -> e.getValue() >= barrier)
				.map(Entry::getKey)
				.collect(Collectors.toList());

		// Create the extended amino acid map
		Utils.massTable = massTable.apply(masses);

		return leaderboardSequence.apply(spec, n);
	};
}