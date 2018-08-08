package coursera.bio;

import static coursera.bio.LeaderboardTrim.trim;
import static coursera.bio.Utils.stringToInts;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class LeaderboardTrimTest {

	@Test
	public void trim() {
		List<String> peptides = Arrays.asList("LAST", "ALST", "TLLT", "TQAS");
		List<Integer> spec = Arrays.asList(0, 71, 87, 101, 113, 158, 184, 188, 259, 271, 372);
		List<String> expected = Arrays.asList("LAST", "ALST");
		assertEquals(expected, trim.apply(peptides, spec, 2));
	}

	@Test
	public void trimExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("Trim.txt");
		List<String> peptides = Arrays.asList(lines.get(1).split(" "));
		List<Integer> spec = stringToInts.apply(lines.get(2));
		Integer n = Integer.valueOf(lines.get(3));
		List<String> expected = Arrays.asList(lines.get(5).split(" "));

		assertEquals(expected, trim.apply(peptides, spec, n));

	}

	@Test
	public void trimDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_4913_3.txt");
		List<String> peptides = Arrays.asList(lines.get(0).split(" "));
		List<Integer> spec = stringToInts.apply(lines.get(1));
		Integer n = Integer.valueOf(lines.get(2));
		System.out.println(
				trim.apply(peptides, spec, n)
				.stream()
				.collect(Collectors.joining(" ")));
	}
}