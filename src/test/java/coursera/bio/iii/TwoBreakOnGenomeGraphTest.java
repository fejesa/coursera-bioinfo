package coursera.bio.iii;

import static coursera.bio.iii.ColoredEdges.format;
import static coursera.bio.iii.TwoBreakOnGenomeGraph.twoBreakOnGraph;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class TwoBreakOnGenomeGraphTest {

	@Test
	public void twoBreakSimple() {
		String graph = "(2, 4), (3, 8), (7, 5), (6, 1)";
		int[] breakPoints = breakPoints("1, 6, 3, 8");
		assertEquals("(2, 4), (3, 1), (7, 5), (6, 8)", format.apply(twoBreakOnGraph(
				graph, breakPoints[0], breakPoints[1], breakPoints[2],
				breakPoints[3])));
	}

	@Test
	public void twoBreakExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("2BreakOnGenomeGraph.txt");
		int[] breakPoints = breakPoints(lines.get(2));
		System.out.println(format.apply(twoBreakOnGraph(lines.get(1), breakPoints[0],
				breakPoints[1], breakPoints[2], breakPoints[3])));
	}

	@Test
	public void twoBreakDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_8224_2.txt");
		int[] breakPoints = breakPoints(lines.get(1));
		System.out.println(format.apply(twoBreakOnGraph(lines.get(0), breakPoints[0],
				breakPoints[1], breakPoints[2], breakPoints[3])));
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
