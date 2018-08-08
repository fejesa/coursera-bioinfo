package coursera.bio;

import static coursera.bio.MedianString.distanceBetweenPatternAndStrings;
import static coursera.bio.MedianString.medianString;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MedianStringTest {

	@Test
	public void distanceBetweenPatternAndStrings() {
		assertEquals(Integer.valueOf(5),
				distanceBetweenPatternAndStrings.apply(
						"AAA",
						Arrays.asList("TTACCTTAAC", "GATATCTGTC", "ACGGCGTTCG", "CCCTAAAGAG", "CGTCAGAGGT")));
	}

	@Test
	public void distanceBetweenPatternAndStringsDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_5164_1.txt");
		assertEquals(Integer.valueOf(59),
				distanceBetweenPatternAndStrings.apply(
						lines.get(0),
						Arrays.asList(lines.get(1).split(" "))));
	}

	@Test
	public void medianString() {
		List<String> dna = Arrays.asList("AAATTGACGCAT", "GACGACCACGTT", "CGTCAGCGCCTG", "GCTGAGCACCGG", "AGTTCGGGACAG");
		assertEquals("GAC", medianString.apply(dna, 3));
	}

	@Test
	public void medianStringDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_158_9.txt");
		List<String> dna = Arrays.asList(lines.get(1), lines.get(2),
				lines.get(3), lines.get(4), lines.get(5), lines.get(6),
				lines.get(7), lines.get(8), lines.get(9), lines.get(10));
		assertEquals("AAAACG",
				medianString.apply(dna, Integer.parseInt(lines.get(0))));
	}
}