package coursera.bio;

import static coursera.bio.MotifSearch.consensus;
import static coursera.bio.MotifSearch.profileMostProbableKmer;
import static coursera.bio.MotifSearch.score;
import static coursera.bio.Utils.distinctKmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GreedyMotifSearch {

	public static List<String> search(List<String> dna, Integer k, Integer t, Function<List<String>, Double[][]> profile) {
		List<String> best = dna
				.stream()
				.map(s -> s.substring(0, k))
				.collect(Collectors.toList());
		Set<String> kmers = distinctKmers(dna.get(0), k);

		int bestScore = score.applyAsInt(best, consensus.apply(best));

		for (String kmer : kmers) {
			List<String> motifs = new ArrayList<>();
			motifs.add(kmer);
			Double[][] matrix = profile.apply(motifs);
			for (int i = 1; i < t; ++i) {
				String motif = profileMostProbableKmer.apply(dna.get(i), k, matrix);
				motifs.add(motif);
				matrix = profile.apply(motifs);
			}

			int sc = score.applyAsInt(motifs, consensus.apply(motifs));
			if (sc < bestScore) {
				best = motifs;
				bestScore = sc;
			}
		}

		return best;
	}
}