package coursera.bio;

import static coursera.bio.LinearScoring.linearScoring;
import static coursera.bio.Utils.stringToInts;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LinearScoringTest {

	@Test
	public void linearScoring() {
		List<Integer> spec = Arrays.asList(0, 99, 113, 114, 128, 227, 257, 299, 355, 356, 370, 371, 484);
		assertEquals(Integer.valueOf(8), linearScoring.apply("NQEL", spec));
	}

	@Test
	public void linearScoringExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("LinearScoring.txt");
		String peptide = lines.get(1);
		List<Integer> spec = stringToInts.apply(lines.get(2));
		assertEquals(Integer.valueOf(lines.get(4)), linearScoring.apply(peptide, spec));
	}

	@Test
	public void linearScoringDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_4913_1.txt");
		String peptide = lines.get(0);
		List<Integer> spec = stringToInts.apply(lines.get(1));
		assertEquals(Integer.valueOf(327), linearScoring.apply(peptide, spec));
	}

	@Test
	public void quiz() {
		List<Integer> spec = Arrays.asList(0, 97, 97, 129, 194, 196, 226, 226, 244, 258, 323, 323, 452);
		System.out.println(linearScoring.apply("PEEP", spec));
		
		
	}
}