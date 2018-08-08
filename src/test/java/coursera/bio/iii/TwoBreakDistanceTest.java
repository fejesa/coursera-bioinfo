package coursera.bio.iii;

import static coursera.bio.iii.TwoBreakDistance.distance;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class TwoBreakDistanceTest {

	@Test
	public void distanceSimple() {
		String p = "(+1 +2 +3 +4 +5 +6)";
		String q = "(+1 -3 -6 -5)(+2 -4)";
		assertEquals(3, distance(p, q));
	}

	@Test
	public void distanceExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("2_break.txt");
		assertEquals(Integer.parseInt(lines.get(4)), distance(lines.get(1), lines.get(2)));
	}

	@Test
	public void distanceDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_288_4.txt");
		assertEquals(8883, distance(lines.get(0), lines.get(1)));
	}
}
