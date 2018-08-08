package coursera.bio;

import static coursera.bio.SkewGenome.*;
import static coursera.bio.SkewGenome.skew;
import static coursera.bio.Utils.intArrayToString;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class SkewGenomeTest {

	@Test
	public void skew() {
		assertEquals("0 -1 -1 -1 0 1 2 1 1 1 0 1 2 1 0 0 0 0 -1 0 -1 -2",
				skew.andThen(intArrayToString).apply("CATGGGCATCGGCCATACGCC"));
	}

	@Test
	public void skewDataset() {
		assertEquals("0 1 1 2 1 0 0 -1 -2 -1 -2 -1 -1 -1 -1",
				skew.andThen(intArrayToString).apply("GAGCCACCGCGATA"));
	}

	@Test
	public void minimumSkew() {
		assertEquals("11 24", 
				minimumSkew.andThen(intArrayToString).apply("TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT"));
	}

	@Test
	public void minimumSkewExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("minimum_skew_data.txt");
		assertEquals(lines.get(3), minimumSkew.andThen(intArrayToString).apply(lines.get(1)));
	}

	@Test
	public void minimumSkewDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_7_6.txt");
		assertEquals("88817", minimumSkew.andThen(intArrayToString).apply(lines.get(0)));
	}

	@Test
	public void skewQuiz() {
		System.out.println(maximumSkew.andThen(intArrayToString).apply("CATTCCAGTACTTCATGATGGCGTGAAGA"));
	}
}