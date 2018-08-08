package coursera.bio;

import static coursera.bio.GreedyMotifSearch.search;
import static coursera.bio.MotifSearch.consensus;
import static coursera.bio.MotifSearch.laplaceRuleProfile;
import static coursera.bio.MotifSearch.probability;
import static coursera.bio.MotifSearch.profile;
import static coursera.bio.MotifSearch.profileMostProbableKmer;
import static coursera.bio.MotifSearch.score;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class GreedyMotifSearchTest {

	@Test
	public void profileMostProbableKmer() {
		String text = "ACCTGTTTATTGCCTAAGTTCCGAACAAACCCAATATAGCCCGAGGGCCT";
		int k = 5;
		Double[][] matrix = new Double[][] {
			{0.2, 0.2, 0.3, 0.2, 0.3},
			{0.4, 0.3, 0.1, 0.5, 0.1},
			{0.3, 0.3, 0.5, 0.2, 0.4},
			{0.1, 0.2, 0.1, 0.1, 0.2}
		};

		assertEquals("CCGAG", profileMostProbableKmer.apply(text, k, matrix));
	}

	@Test
	public void profileMostProbableKmerExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("profile_most_1.txt");
		String text = lines.get(1);
		int k = Integer.parseInt(lines.get(2));
		Double[][] matrix = new Double[][] {
			toDoubles(lines.get(3)),
			toDoubles(lines.get(4)),
			toDoubles(lines.get(5)),
			toDoubles(lines.get(6))
		};

		assertEquals(lines.get(8), profileMostProbableKmer.apply(text, k, matrix));
	}

	@Test
	public void profileMostProbableKmerDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_159_3.txt");
		String text = lines.get(0);
		int k = Integer.parseInt(lines.get(1));
		Double[][] matrix = new Double[][] {
			toDoubles(lines.get(2)),
			toDoubles(lines.get(3)),
			toDoubles(lines.get(4)),
			toDoubles(lines.get(5))
		};
		assertEquals("TCACTCAGGGGTGT", profileMostProbableKmer.apply(text, k, matrix));
	}

	private Double[] toDoubles(String s) {
		String[] array = s.split(" ");
		return Arrays.stream(array).map(Double::parseDouble).collect(Collectors.toList()).toArray(new Double[0]);
	}

	@Test
	public void consensusString() {
		assertEquals("GGC", consensus.apply(Arrays.asList("GGC")));
		assertEquals("AAG", consensus.apply(Arrays.asList("GGC", "AAG", "AAG", "CAC", "CAA")));
		assertEquals("AAT", consensus.apply(Arrays.asList("CGT", "AAG", "AAG", "AAT", "AAT")));
	}

	@Test
	public void scoring() {
		List<String> list = Arrays.asList("GGC", "AAG", "AAG", "CAC", "CAA");
		assertEquals(7, score.applyAsInt(list, consensus.apply(list)));
		
		list = Arrays.asList("CGT", "AAG", "AAG", "AAT", "AAT");
		assertEquals(4, score.applyAsInt(list, consensus.apply(list)));
	}

	@Test
	public void motifSearch() {
	     List<String> dna = Arrays.asList("GGCGTTCAGGCA", "AAGAATCAGTCA", "CAAGGAGTTCGC", "CACGTCAATCAC", "CAATAATATTCG");
		 String motifs = search(dna, 3, 5, profile)
				 .stream()
				 .collect(Collectors.joining(" "));
		 String expected = Arrays
					.asList("CAG", "CAG", "CAA", "CAA", "CAA")
					.stream()
					.collect(Collectors.joining(" "));
		 assertEquals(expected, motifs);
	}

	@Test
	public void laplaceMotifSearch() {
		List<String> dna = Arrays.asList("GGCGTTCAGGCA", "AAGAATCAGTCA",
				"CAAGGAGTTCGC", "CACGTCAATCAC", "CAATAATATTCG");
		String motifs = search(dna, 3, 5, laplaceRuleProfile)
				.stream()
				.collect(Collectors.joining(" "));

		String expected = Arrays.asList("TTC", "ATC", "TTC", "ATC", "TTC")
				.stream()
				.collect(Collectors.joining(" "));
		assertEquals(expected, motifs);
	}

	@Test
	public void laplaceMotifSearchExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("greedy_pseudo.txt");
		String[] nums = lines.get(1).split(" ");
		int k = Integer.parseInt(nums[0]);
		int t = Integer.parseInt(nums[1]);
		List<String> dna = IntStream
				.range(2, t + 2)
				.mapToObj(i -> lines.get(i).trim())
				.collect(Collectors.toList());

		String expected = IntStream
				.range(t + 3, t + 3 + t)
				.mapToObj(i -> lines.get(i).trim())
					.collect(Collectors.joining(" "));
		String motifs = search(dna, k, t, laplaceRuleProfile)
				 .stream()
				 .collect(Collectors.joining(" "));

		assertEquals(expected, motifs);
	}
	
	@Test
	public void motifSearchExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("greedy_data.txt");
		String[] nums = lines.get(1).split(" ");
		int k = Integer.parseInt(nums[0]);
		int t = Integer.parseInt(nums[1]);
		List<String> dna = IntStream
				.range(2, t + 2)
				.mapToObj(i -> lines.get(i))
				.collect(Collectors.toList());

		String expected = IntStream
				.range(t + 3, t + 3 + t)
				.mapToObj(i -> lines.get(i))
					.collect(Collectors.joining(" "));
		String motifs = search(dna, k, t, profile)
				 .stream()
				 .collect(Collectors.joining(" "));

		assertEquals(expected, motifs);
	}

	@Test
	public void laplaceMotifSearchDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_160_9.txt");
		String[] nums = lines.get(0).split(" ");
		int k = Integer.parseInt(nums[0]);
		int t = Integer.parseInt(nums[1]);
		List<String> dna = IntStream
				.range(1, t + 1)
				.mapToObj(i -> lines.get(i))
				.collect(Collectors.toList());
		                        
		
		String expected = Arrays.asList(
				"AGATCCGCTCCT", "GATTCTGCTCCT", "CCATCCGCTCCT",
				"CTTTCGGCTCCT", "AGATCTGCTCCT", "CGTTCGGCTCCT",
				"GTGTCAGCTCCT", "CACTCCGCTCCT", "TCGTCGGCTCCT",				
				"GGCTCTGCTCCT", "CGTTCGGCTCCT", "CGCTCTGCTCCT",
				"GAGTCGGCTCCT", "CCGTCGGCTCCT", "TTATCGGCTCCT",
				"CTATCAGCTCCT", "GCCTCTGCTCCT", "CCCTCGGCTCCT",				
				"CGCTCCGCTCCT", "CGGTCAGCTCCT", "AGTTCGGCTCCT",
				"GTGTCCGCTCCT", "CGGTCCGCTCCT", "GACTCAGCTCCT",
				"TGCTCCGCTCCT")
				.stream()
				.collect(Collectors.joining(" "));

		String motifs = search(dna, k, t, laplaceRuleProfile)
				 .stream()
				 .collect(Collectors.joining(" "));

		assertEquals(expected, motifs);
	}

	
	@Test
	public void motifSearchDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_159_5.txt");
		String[] nums = lines.get(0).split(" ");
		int k = Integer.parseInt(nums[0]);
		int t = Integer.parseInt(nums[1]);
		List<String> dna = IntStream
				.range(1, t + 1)
				.mapToObj(i -> lines.get(i))
				.collect(Collectors.toList());
		String expected = Arrays.asList(
				"ACGCCAGTTTGG", "GCTATGGATAGT", "TAGGGGCCTCTG",
				"AGGGTCGGCTGC", "ACATCGGCAGTG", "TCGAGCGAAAGG",
				"AAGACGGGCCGG", "GCGACGGAATTG", "GCGACAGTTGGT",
				"ACGAGGGGAATG", "AAGTCCGCTTGT", "ACGTGGGGCCTG",
				"GCCTACCGGATG", "GAGTGCGTCATG", "AACTCGGAGATG",
				"ACGCCAGTTCGG", "ACGTGACGTCTT", "ACGAGCGAGATG",
				"GCGAGCGAAAGG", "GCGTCGGACTTG", "ACGTGGGAGGTG",
				"ACAGCCGAGCGT", "GCAACCGCTCTC", "AGGTGCGATGTG",
				"ACGTCCGCAATT")
				.stream()
				.collect(Collectors.joining(" "));

		String motifs = search(dna, k, t, profile)
				 .stream()
				 .collect(Collectors.joining(" "));

		assertEquals(expected, motifs);
	}

	@Test
	public void profileMatrix() {
		printProfile(profile.apply(Arrays.asList("GGC")));
		printProfile(profile.apply(Arrays.asList(
				"TCGGGGGTTTTT", "CCGGTGACTTAC", "ACGGGGATTTTC",
				"TTGGGGACTTTT", "AAGGGGACTTCC", "TTGGGGACTTCC",
				"TCGGGGATTCAT", "TCGGGGATTCCT", "TAGGGGAACTAC",
				"TCGGGTATAACC")));
	}

	@Test
	public void laplaceProfileMatrix() {
		printProfile(laplaceRuleProfile.apply(Arrays.asList("ACCT")));
	}

	private void printProfile(Double[][] profile) {
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < profile[i].length; ++j) {
				System.out.printf("%.3f ", profile[i][j]);
			}
			System.out.println();
		}
	}

	@Test
	public void quiz() {
		List<String> dna = Arrays.asList(
				"CTCGATGAGTAGGAAAGTAGTTTCACTGGGCGAACCACCCCGGCGCTAATCCTAGTGCCC",
				"GCAATCCTACCCGAGGCCACATATCAGTAGGAACTAGAACCACCACGGGTGGCTAGTTTC",
				"GGTGTTGAACCACGGGGTTAGTTTCATCTATTGTAGGAATCGGCTTCAAATCCTACACAG");
		String motifs = search(dna, 7, 3, laplaceRuleProfile)
				.stream()
				.collect(Collectors.joining(" "));
		System.out.println(motifs);
	}

	@Test
	public void quiz2() {
		Double[][] matrix = new Double[][] {
				{0.4, 0.3, 0.0, 0.1, 0.0, 0.9},
				{0.2, 0.3, 0.0, 0.4, 0.0, 0.1},
				{0.1, 0.3, 1.0, 0.1, 0.5, 0.0},
				{0.3, 0.1, 0.0, 0.4, 0.5, 0.0}
			};

			System.out.println(probability.apply("GAGCTA", matrix));
	}
}