package coursera.bio;

import static coursera.bio.CyclopeptideScoring.score;
import static coursera.bio.LeaderboardTrim.trim;
import static coursera.bio.Utils.expand;
import static coursera.bio.Utils.massTable;
import static coursera.bio.Utils.peptideMass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class LeaderboardCyclopeptideSequencing {

	public static BiFunction<List<Integer>, Integer, String> leaderboardSequence = (s, n) -> {
		List<String> leaderboard = new ArrayList<>();
		String leaderPeptide = "";
		leaderboard.add(leaderPeptide);

		int max = s.stream().max(Comparator.naturalOrder()).get();
		while (!leaderboard.isEmpty()) {
			leaderboard = expand.apply(leaderboard);
			for (Iterator<String> i = leaderboard.iterator(); i.hasNext();) {
				String p = i.next();
				int mass = peptideMass.apply(p);
				if (s.contains(mass)) {
					if (score.apply(p, s) > score.apply(leaderPeptide, s)) {
						leaderPeptide = p;
					}
				} else if (mass > max) {
					i.remove();
				}
			}
			leaderboard = trim.apply(leaderboard, s, n);
		}

		List<Integer> list = leaderPeptide
				.chars()
				.mapToObj(c -> massTable.get((char) c))
				.collect(Collectors.toList());

		return list
				.stream()
				.map(i -> Integer.toString(i))
				.collect(Collectors.joining("-"));
	};
}