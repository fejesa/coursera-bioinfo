package coursera.bio.iii;

import static coursera.bio.iii.ColoredEdges.toEdges;
import static coursera.bio.iii.ColoredEdges.format;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class ColoredEdgesTest {

	@Test
	public void toEdges() {
		String genome = "(+1 -2 -3)(+4 +5 -6)";
		assertEquals("(2, 4), (3, 6), (5, 1), (8, 9), (10, 12), (11, 7)",
				toEdges.andThen(format).apply(genome));
	}

	@Test
	public void toEdgesExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("ColoredEdges.txt");
		assertEquals(lines.get(3), toEdges.andThen(format).apply(lines.get(1)));
	}

	@Test
	public void toEdgesDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_8222_7.txt");
		System.out.println(toEdges.andThen(format).apply(lines.get(0)));
	}
}
