package coursera.bio;

import static coursera.bio.LinearScoring.linearScoring;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class LeaderboardTrim {
	
	public static TriFunction<List<String>, List<Integer>, Integer, List<String>> trim = (leaderboard, spec, n) -> {

		Map<String, Integer> scores = leaderboard
				.stream()
				.collect(Collectors.toMap(
						l -> l, l -> linearScoring.apply(l, spec)));

		Comparator<Entry<String, Integer>> byValue = (entry1, entry2) -> {
			if (entry1.getValue().equals(entry2.getValue())) {
				return entry1.getKey().compareTo(entry2.getKey()); 
			}
			return entry1.getValue().compareTo(entry2.getValue());
		};

		List<Entry<String, Integer>> list = scores
				.entrySet()
				.stream()
				.sorted(byValue.reversed())
				.collect(Collectors.toList());

		if (list.size() > n) {
			Integer barrier = list.get(n - 1).getValue();
			return list
					.stream()
					.filter(e -> e.getValue() >= barrier)
					.map(Entry::getKey)
					.collect(Collectors.toList());
		}

		return list
				.stream()
				.map(Entry::getKey)
				.collect(Collectors.toList());
		
	};
}