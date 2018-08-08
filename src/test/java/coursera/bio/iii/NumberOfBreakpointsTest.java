package coursera.bio.iii;

import static coursera.bio.iii.NumberOfBreakpoints.breakpoints;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class NumberOfBreakpointsTest {

	@Test
	public void count() {
		String in = "(+3 +4 +5 -12 -8 -7 -6 +1 +2 +10 +9 -11 +13 +14)";
		assertEquals(Integer.valueOf(8), breakpoints.apply(in));
	}

	@Test
	public void countExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("number_of_breaks.txt");
		assertEquals(Integer.valueOf(lines.get(3)), breakpoints.apply(lines.get(1)));
	}

	@Test
	public void countDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_287_5.txt");
		assertEquals(Integer.valueOf(168), breakpoints.apply(lines.get(0)));
	}

	@Test
	public void countQuiz() {
		String in = "(-16 -20 +11 +12 -14 -13 -15 -6 -8 -19 -18 -17 -10 +4 -5 -2 +7 -3 +1 -9)";
		System.out.println(breakpoints.apply(in));
	}
}