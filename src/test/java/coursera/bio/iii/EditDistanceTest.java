package coursera.bio.iii;

import static coursera.bio.iii.EditDistance.alignment;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class EditDistanceTest {

	@Test
	public void distance() {
		String a = "PLEASANTLY";
		String b = "MEANLY";
		Triplet<Integer, String, String> result = alignment(a, b);
		assertEquals(Integer.valueOf(5), result.x);
	}

	@Test
	public void distanceExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("edit_distance.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(1), lines.get(2));
		assertEquals(Integer.valueOf(lines.get(4)), result.x);
	}

	@Test
	public void distanceDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_248_3.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(0), lines.get(1));
		assertEquals(Integer.valueOf(366), result.x);
	}
}