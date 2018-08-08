package coursera.bio;

import static coursera.bio.RandomizedMotifSearch.doSearch;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class RandomizedMotifSearchTest {

	@Test
	public void motifSearch() {
		List<String> dna = Arrays.asList(
				"CGCCCCTCTCGGGGGTGTTCAGTAAACGGCCA",
				"GGGCGAGGTATGTGTAAGTGCCAAGGTGCCAG",
				"TAGTACCGAGACCGAAAGAAGTATACAGGCGT",
				"TAGATCAAGTTTCAGGTGCACGTCGGTGAACC",
				"AATCCACCAGCTCCACGTGCAATGTTGGCCTA");

		String motifs = doSearch.apply(dna, 8, 5)
				 .stream()
				 .collect(Collectors.joining(" "));

		 String expected = Arrays
					.asList("TCTCGGGG", "CCAAGGTG", "TACAGGCG", "TTCAGGTG", "TCCACGTG")
					.stream()
					.collect(Collectors.joining(" "));
		 assertEquals(expected, motifs);
	}

	@Test
	public void motifSearchExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("randomized.txt");
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

		String motifs = doSearch.apply(dna, k, t)
				 .stream()
				 .collect(Collectors.joining(" "));

		assertEquals(expected, motifs);
	}

	@Test
	public void motifSearchDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_161_5.txt");
		String[] nums = lines.get(0).split(" ");
		int k = Integer.parseInt(nums[0]);
		int t = Integer.parseInt(nums[1]);
		
		List<String> dna = IntStream
				.range(1, t + 1)
				.mapToObj(i -> lines.get(i).trim())
				.collect(Collectors.toList());

		String expected = Arrays
				.asList("GAACCACCTCATTGG", "GAGACGCCTGTGTGT",
						"GAGCAACTGTTGTGT", "GAGCAACCTCCTTGT",
						"CAGCAACCTGTGTTA", "GCTGAACCTGTGTGT",
						"GAGCAACCTGTTGAT", "AGCCAACCTGTGTGT",
						"CTGCAACCTGTGTGA", "GAGCTGACTGTGTGT",
						"GAGCAAAAAGTGTGT", "GAGCAACCATAGTGT",
						"GAGTGGCCTGTGTGT", "GAGCAACCTGTGCCG",
						"GAGCTCTCTGTGTGT", "GAGCACTATGTGTGT",
						"GAGCAACCTGACAGT", "GAGCAAAAGGTGTGT",
						"GACTCACCTGTGTGT", "GAGCACATTGTGTGT")
						.stream()
						.collect(Collectors.joining(" "));

		for (int i = 0; i < 10; ++i) {
			String motifs = doSearch.apply(dna, k, t)
				 .stream()
				 .collect(Collectors.joining(" "));

			assertEquals(expected, motifs);
		}
	}
}
