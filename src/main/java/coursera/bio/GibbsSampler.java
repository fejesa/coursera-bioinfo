package coursera.bio;

import static coursera.bio.MotifSearch.*;
import static coursera.bio.Utils.getNextIndex;
import static coursera.bio.Utils.randomKmer;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GibbsSampler {

	public static List<String> randomSearch(List<String> dna, int k, int t, int n) {
		List<String> motifs = dna.stream()
				.map(text -> randomKmer.apply(text, k))
				.collect(Collectors.toList());

		List<String> best = motifs;
		for (int j = 0; j < n; ++j) {

			// Choose one of the sequences at random and
			// create profile P from kmers in remaining sequences
			int index = getNextIndex.apply(t);
			List<String> rlist = remove.apply(motifs, index);

			Double[][] matrix = laplaceRuleProfile.apply(rlist);
			
			// Create every possible kmer from the removed sequence and
			// Calculate the prob(a|P) for every possible kmer in the removed sequence
			List<String> textKmers = kmers.apply(dna.get(index), k);
			List<Double> probabilities = kmerProbability.apply(textKmers, k, matrix);

			// Create a distribution of probabilities of kmers prob(a|P),
			// and randomly select a new starting position based on this distribution
			List<Double> dist = distribute.apply(probabilities);

			motifs.set(index, genProfileRandomKmer.apply(dist, textKmers, index));

			if (score.applyAsInt(motifs, consensus.apply(motifs)) < score.applyAsInt(best, consensus.apply(best))) {
				best = motifs;
			}
		}

		return best;
	}

	public static List<String> doSearch(List<String> dna, int k, int t, int n) {
		List<String> best = null;
		int bestScore = Integer.MAX_VALUE;

		for (int i = 0; i < 100; ++i) {
			List<String> motifs = randomSearch(dna, k, t, n);
			int sc = score.applyAsInt(motifs, consensus.apply(motifs));
			if (bestScore > sc) {
				bestScore = sc;
				best = motifs;
			}
		}

		return best;
	}

	private static TriFunction<List<Double>, List<String>, Integer, String> genProfileRandomKmer = (dist, kmers, index) -> {
		Double max = Double.valueOf(0);
		int maxIndex = 0;
		for (int i = 0; i < dist.size(); ++i) {
			if (dist.get(i) > max) {
				max = dist.get(i);
				maxIndex = i;
			}
		}
		return kmers.get(maxIndex);
	};

	private static BiFunction<List<String>, Integer, List<String>> remove = (dna, index) -> {
		// Return list does not contain random selected element
		return IntStream.range(0, dna.size())
				.filter(i -> i != index)
				.mapToObj(i -> dna.get(i))
				.collect(Collectors.toList());
	};

	// Create a list of kmers of the given text
	private static BiFunction<String, Integer, List<String>> kmers = (text, k) -> 
		IntStream
			.range(0, text.length() - k + 1)
			.mapToObj(i -> text.substring(i, i + k))
			.collect(Collectors.toList());

	private static TriFunction<List<String>, Integer, Double[][], List<Double>> kmerProbability = (kmers, k, matrix) -> 
		// Calculate the prob(a|P) for every possible kmer in the removed sequence
		kmers.stream()
				.map(kmer -> probability.apply(kmer, matrix))
				.collect(Collectors.toList());

	public static Function<List<Double>, List<Double>> distribute = (probs) -> {
		// To create this distribution, divide each probability prob(a|P) by the lowest probability
		Double min = probs.stream().filter(p -> p > 0).min(Double::compare).get();
		List<Double> dist = probs.stream().map(p -> p / min).collect(Collectors.toList());

		// Define probabilities of starting positions according to computed ratios
		Double sum = dist.stream().reduce(0d, Double::sum);
		List<Double> list = dist.stream().map(d -> d / sum).collect(Collectors.toList());

		return list;
	};
}