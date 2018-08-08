package coursera.bio;

import static coursera.bio.GibbsSampler.distribute;
import static coursera.bio.GibbsSampler.doSearch;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class GibbsSamplerTest {

	@Test
	public void motifSearch() {
		List<String> dna = Arrays.asList(
				"CGCCCCTCTCGGGGGTGTTCAGTAAACGGCCA",
				"GGGCGAGGTATGTGTAAGTGCCAAGGTGCCAG",
				"TAGTACCGAGACCGAAAGAAGTATACAGGCGT",
				"TAGATCAAGTTTCAGGTGCACGTCGGTGAACC",
				"AATCCACCAGCTCCACGTGCAATGTTGGCCTA");
		String motifs = doSearch(dna, 8, 5, 20)
				.stream()
				.collect(Collectors.joining(" "));;

		String expected = Arrays
				.asList("TCTCGGGG", "CCAAGGTG", "TACAGGCG", "TTCAGGTG", "TCCACGTG")
				.stream()
				.collect(Collectors.joining(" "));

		assertEquals(expected, motifs);

	}

	@Test
	public void motifSearchExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("gibbs.txt");
		String[] nums = lines.get(1).split(" ");
		int k = Integer.parseInt(nums[0]);
		int t = Integer.parseInt(nums[1]);
		int n = Integer.parseInt(nums[2]);

		List<String> dna = IntStream
				.range(2, t + 2)
				.mapToObj(i -> lines.get(i).trim())
				.collect(Collectors.toList());

		String expected = IntStream
				.range(t + 3, t + 3 + t)
				.mapToObj(i -> lines.get(i).trim())
					.collect(Collectors.joining(" "));

		String motifs = doSearch(dna, k, t, n)
				 .stream()
				 .collect(Collectors.joining(" "));

		assertEquals(expected, motifs);
	}

	@Test
	public void motifSearchDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_163_4.txt");

		String[] nums = lines.get(0).split(" ");
		int k = Integer.parseInt(nums[0]);
		int t = Integer.parseInt(nums[1]);
		int n = Integer.parseInt(nums[2]);

		List<String> dna = IntStream
				.range(1, t + 1)
				.mapToObj(i -> lines.get(i).trim())
				.collect(Collectors.toList());

		String expected = Arrays
				.asList("TCGACTGCGAGGCCT", "TCGGCCCCATAGGTT",
						"TGATTTCCATAGGTT", "ATCATTCCATAGGTT",
						"TCGATTCGTAAGGTT", "TCGATTATCTAGGTT",
						"TCGTCCCCATAGGTT", "TCGATTCCATAGACA",
						"TCGATTCCATAACAT", "TCGATGGGATAGGTT",
						"TCGATGGTATAGGTT", "GAGATTCCATAGGTC",
						"TCTTGTCCATAGGTT", "TCGAAGGCATAGGTT",
						"TCGATTCCATGTTTT", "TCGATTCCAAGCGTT",
						"TCGAAGGCATAGGTT", "TCGATTGTTTAGGTT",
						"TCGATTCCTCGGGTT", "ACGATTCCATAGGAA")
				.stream()
				.collect(Collectors.joining(" "));

			String motifs = doSearch(dna, k, t, n)
					 .stream()
					 .collect(Collectors.joining(" "));

			assertEquals(expected, motifs);
	}

	@Test
	public void dist() {
		List<Double> list = Arrays.asList(0.1, 0.2, 0.3);
		System.out.println(distribute.apply(list));
	}
}