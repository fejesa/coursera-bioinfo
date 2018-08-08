package coursera.bio;

import static coursera.bio.PatternUtil.computeFrequencies;
import static coursera.bio.PatternUtil.numberToPattern;
import static coursera.bio.PatternUtil.patternCount;
import static coursera.bio.PatternUtil.patternToNumber;
import static coursera.bio.Utils.intArrayToString;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class PatternUtilTest {

	@Test
	public void patternCount() {
		assertEquals(Integer.valueOf(2), patternCount.apply("GCGCG", "GCG"));
	}

	@Test
	public void patternCountExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("PatternCount.txt");
		String text = lines.get(1);
		String pattern = lines.get(2);
		assertEquals(Integer.valueOf(lines.get(4)),
				patternCount.apply(text, pattern));
	}

	@Test
	public void patternCountDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_2_6.txt");
		String text = lines.get(0);
		String pattern = lines.get(1);
		assertEquals(Integer.valueOf(28), patternCount.apply(text, pattern));
	}

	@Test
	public void patternCountQuiz() {
		int i = patternCount.apply(
				"CGCGATACGTTACATACATGATAGACCGCGCGCGATCATATCGCGATTATC", "CGCG");
		assertEquals(5, i);
	}

	@Test
	public void patternToNumber() {
		assertEquals(912, patternToNumber.applyAsInt("ATGCAA"));
	}

	@Test
	public void numberToPattern() {
		assertEquals("CCCATTC", numberToPattern.apply(5437, 7));
		assertEquals("ACCCATTC", numberToPattern.apply(5437, 8));
	}

	@Test
	public void computeFrequencies() {
		assertEquals("2 1 0 0 0 0 2 2 1 2 1 0 0 1 1 0",
				computeFrequencies.andThen(intArrayToString).apply("ACGCGGCTCTGAAA", 2));
	}

	@Test
	public void computeFrequenciesDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_2994_5.txt");
		assertEquals(
				"0 2 0 1 1 0 1 1 0 1 1 0 0 0 1 1 1 0 4 0 1 1 0 1 0 1 1 0 0 1 1 0 0 3 1 2 2 1 1 0 1 1 0 1 2 0 1 1 0 0 0 0 1 0 0 1 0 0 1 0 1 1 0 1 0 0 2 0 0 1 0 1 0 1 2 1 0 1 0 1 0 1 1 0 2 1 2 1 0 0 0 0 0 0 2 0 0 0 0 0 1 1 0 0 0 0 1 0 1 0 0 0 1 0 1 1 1 0 1 0 1 2 2 3 0 0 0 1 1 1 0 0 2 0 0 2 2 0 1 0 1 0 2 0 0 0 0 2 1 3 0 0 0 1 1 2 0 2 0 0 1 0 0 1 1 0 2 0 1 0 0 1 0 1 0 0 2 1 0 1 0 1 1 0 1 1 0 1 0 0 1 0 0 2 2 0 0 0 0 1 0 0 0 0 1 0 1 0 0 1 1 0 0 1 0 2 1 0 0 0 3 1 1 0 0 0 2 1 0 2 2 0 0 0 2 1 1 0 1 1 0 1 0 2 1 0 0 0 0 0 0 2 1 0 0 1 2 0 0 0 0 1 0 0 2 1 2 1 0 0 0 1 0 0 0 1 0 2 0 1 0 0 0 0 2 0 5 0 1 0 1 0 0 2 3 1 1 1 1 0 1 0 1 0 0 0 0 0 1 1 1 3 3 1 0 0 0 0 1 1 0 1 2 0 1 1 0 2 0 1 1 0 0 4 0 0 1 2 0 2 1 2 3 0 0 2 2 2 0 0 1 0 0 0 0 0 1 0 1 0 0 1 0 1 1 1 0 1 0 0 0 0 1 0 0 0 1 0 1 2 0 0 0 1 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 1 2 0 1 0 1 0 1 1 0 1 0 0 0 0 0 1 0 1 0 0 2 0 0 0 0 0 1 2 1 1 1 1 0 0 1 1 1 0 0 2 0 2 0 1 0 2 0 0 1 0 1 0 0 0 1 1 2 0 0 2 1 0 1 0 0 0 0 1 0 0 0 0 0 2 0 1 0 3 0 1 0 1 2 0 1 2 1 1 1 1 3 0 0 0 0 1 0 1 0 1 0 0 0 1 0 1 1 1 1 0 1 1 1 1 1 1 0 0 1 0 1 0 0 1 1 0 1 0 1 0 0 0 0 0 0 0 0 1 1 1 1 1 0 0 1 0 1 0 0 1 0 1 1 1 0 1 1 0 0 0 0 0 0 0 1 2 1 0 0 1 0 1 0 1 0 0 1 0 2 0 2 0 1 0 1 4 0 2 1 0 2 1 3 1 0 0 0 0 0 0 0 0 1 0 0 0 0 0 2 0 2 0 1 1 0 3 1 0 3 0 1 0 0 1 0 0 2 0 1 0 0 0 2 1 0 2 1 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 1 2 2 0 0 0 0 0 1 0 2 0 1 0 1 1 0 0 1 3 0 0 1 0 0 0 0 1 0 1 1 0 1 1 0 0 0 2 0 1 0 0 1 0 1 0 1 1 1 3 1 1 1 1 1 1 0 1 0 1 0 2 0 1 0 1 0 0 0 1 0 0 1 0 2 2 0 2 1 1 1 1 1 0 1 1 0 0 0 1 0 2 0 0 1 1 0 0 2 0 1 1 0 1 3 1 0 0 0 1 1 0 0 2 0 3 1 0 0 3 2 0 2 0 1 0 1 0 1 0 0 1 2 0 0 0 1 0 1 1 1 1 0 0 0 0 1 0 0 0 0 0 1 0 0 0 1 0 0 3 0 0 2 0 2 0 1 0 2 0 2 2 0 0 0 1 0 1 1 0 0 0 2 2 2 0 0 0 0 0 1 0 0 1 0 1 0 0 0 0 0 0 1 0 1 1 0 2 0 0 0 0 1 1 0 0 1 0 0 0 0 2 0 2 1 0 2 1 0 0 0 2 1 1 1 0 0 0 1 0 2 1 0 1 1 0 0 1 2 0 3 0 0 1 1 1 1 1 1 3 0 0 0 0 1 1 2 0 0 0 2 1 0 0 0 0 0 0 1 1 2 0 2 0 1 1 0 2 1 0 1 1 0 0 4 1 0 1 1 1 1 2 1 0 1 0 1 0 1 1 0 0 0 0 0 2 1 1 2 0 0 2 0 1 0 0 0 0 1 1 0 0 0 0 0 1 1 0 0 3 0 0 0 1 0 0 0 0 4 1 0 1 1 0 2 0 0 0 0 0 0 0 3 2 0 1 0",
				computeFrequencies
					.andThen(intArrayToString)
					.apply(lines.get(0), Integer.parseInt(lines.get(1))));
	}
}