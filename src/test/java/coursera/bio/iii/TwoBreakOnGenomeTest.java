package coursera.bio.iii;

import static coursera.bio.iii.TwoBreakOnGenome.twoBreak;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class TwoBreakOnGenomeTest {

	@Test
	public void twoBreakSimple() {
		String genome = "(+1 -2 -4 +3)";
		int[] breakPoints = breakPoints("1, 6, 3, 8");
		System.out.println(twoBreak(genome, breakPoints[0],
				breakPoints[1], breakPoints[2], breakPoints[3]));
	}

	@Test
	public void twoBreakExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("2BreakOnGenome.txt");
		int[] breakPoints = breakPoints(lines.get(2));
		System.out.println(twoBreak(lines.get(1), breakPoints[0],
				breakPoints[1], breakPoints[2], breakPoints[3]));
		System.out.println();
		System.err.println(lines.get(4));
		
	}
	
	private int[] breakPoints(String in) {
		String[] array = in.split(",");
		int[] points = new int[array.length];
		for (int i = 0; i < array.length; ++i) {
			points[i] = Integer.parseInt(array[i].trim());
		}
		return points;
	}
}
