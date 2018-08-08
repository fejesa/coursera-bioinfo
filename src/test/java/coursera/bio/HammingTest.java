package coursera.bio;

import static coursera.bio.Hamming.distance;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class HammingTest {

	@Test
	public void distance() {
		assertEquals(Integer.valueOf(3), distance.apply("GGGCCGTTGGT", "GGACCGTTGAC"));
	}

	@Test
	public void distanceExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("HammingDistance.txt");
		assertEquals(Integer.valueOf(lines.get(4)), distance.apply(lines.get(1), lines.get(2)));
	}

	@Test
	public void distanceDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_9_3.txt");
		assertEquals(Integer.valueOf(834), distance.apply(lines.get(0), lines.get(1)));
	}

	@Test
	public void distanceQuiz() {
		System.out.println(distance.apply("CTTGAAGTGGACCTCTAGTTCCTCTACAAAGAACAGGTTGACCTGTCGCGAAG", "ATGCCTTACCTAGATGCAATGACGGACGTATTCCTTTTGCCTCAACGGCTCCT"));
	}
}