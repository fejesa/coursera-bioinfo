package coursera.bio.iii;

import static coursera.bio.iii.SharedKmers.shared;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import coursera.bio.FileUtil;

public class SharedKmersTest {

	@Test
	public void sharedSimple() {
		String p = "AAACTCATC";
		String q = "TTTCAAATC";
		List<Tuple<Integer, Integer>> kmers = shared(3, p, q);
		List<Tuple<Integer, Integer>> expected = Arrays.asList(
				new Tuple<Integer, Integer>(0, 4), new Tuple<Integer, Integer>(
						0, 0), new Tuple<Integer, Integer>(4, 2),
				new Tuple<Integer, Integer>(6, 6));

		assertEquals(expected.size(), kmers.size());
		expected.forEach(t -> {
			assertTrue(kmers.contains(t));
		});
		// kmers.forEach(k -> System.out.println(tuple2String(k)));
	}

	@Test
	public void sharedFromFaq() {
		String p = "AGCAGGTTATCTACCTGT";
		String q = "AGCAGGAGATAAACCTGT";
		List<Tuple<Integer, Integer>> kmers = shared(3, p, q);

		System.out.println(kmers.size());
		kmers.forEach(k -> System.out.println(tuple2String(k)));
	}

	@Test
	public void sharedQuiz() {
		String p = "TCAGTTGGCCTACAT";
		String q = "CCTACATGAGGTCTG";
		List<Tuple<Integer, Integer>> kmers = shared(3, p, q);

		System.out.println(kmers.size());
		kmers.forEach(k -> System.out.println(tuple2String(k)));
	}
	
	@Test
	public void sharedExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("shared_kmer.txt");
		List<Tuple<Integer, Integer>> kmers = shared(
				Integer.parseInt(lines.get(1)), lines.get(2), lines.get(3));
		List<Tuple<Integer, Integer>> expected = lines
				.subList(5, lines.size()).stream().map(this::string2Tuple)
				.collect(Collectors.toList());
		assertEquals(expected.size(), kmers.size());
		expected.forEach(t -> {
			assertTrue(kmers.contains(t));
		});
	}

	@Test
	public void sharedDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_289_5.txt");
		List<Tuple<Integer, Integer>> kmers = shared(
				Integer.parseInt(lines.get(0)), lines.get(1), lines.get(2));
		kmers.forEach(k -> System.out.println(tuple2String(k)));
	}

	private String tuple2String(Tuple<Integer, Integer> tuple) {
		return String.format("(%d, %d)", tuple.a, tuple.b);
	}

	private Tuple<Integer, Integer> string2Tuple(String s) {
		String[] nums = s.substring(1, s.length() - 1).split(",");
		return new Tuple<Integer, Integer>(Integer.parseInt(nums[0].trim()),
				Integer.parseInt(nums[1].trim()));
	}
}