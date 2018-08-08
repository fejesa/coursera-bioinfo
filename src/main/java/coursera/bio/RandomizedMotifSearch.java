package coursera.bio;

import static coursera.bio.MotifSearch.consensus;
import static coursera.bio.MotifSearch.laplaceRuleProfile;
import static coursera.bio.MotifSearch.profileMostProbableKmer;
import static coursera.bio.MotifSearch.score;
import static coursera.bio.Utils.randomKmer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomizedMotifSearch {
	
	private static TriFunction<List<String>, Integer, Integer, List<String>> randomSearch = (dna, k, t) -> {

		List<String> motifs = dna.stream()
				.map(text -> randomKmer.apply(text, k))
				.collect(Collectors.toList());

		List<String> best = motifs;
		while (true) {
			Double[][] matrix = laplaceRuleProfile.apply(motifs);
			motifs = IntStream.range(0, t)
					.mapToObj(i -> profileMostProbableKmer.apply(dna.get(i), k, matrix))
					.collect(Collectors.toList());

			if (score.applyAsInt(motifs, consensus.apply(motifs)) < score.applyAsInt(best, consensus.apply(best))) {
				best = motifs;
			} else {
				return best;
			}
		}
	};

	public static TriFunction<List<String>, Integer, Integer, List<String>> doSearch = (dna, k, t) -> {
		List<String> best = null;
		int bestScore = Integer.MAX_VALUE;

		for (int i = 0; i < 1000; ++i) {
			List<String> motifs = randomSearch.apply(dna, k, t);
			int sc = score.applyAsInt(motifs, consensus.apply(motifs));
			if (bestScore > sc) {
				bestScore = sc;
				best = motifs;
			}
		}
		
		return best;
	};
}