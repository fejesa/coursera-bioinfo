package coursera.bio.iii;

import static coursera.bio.iii.GraphToGenome.toGenome;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class GraphToGenomeTest {

	@Test
	public void toGenome() {
		String graph = "(2, 4), (3, 6), (5, 1), (7, 9), (10, 12), (11, 8)";
		assertEquals("(+1 -2 -3)(-4 +5 -6)", toGenome.apply(graph));
		System.out.println(toGenome.apply(graph));
	}

	@Test
	public void toGenomeExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("GraphToGenome.txt");
		assertEquals(lines.get(3), toGenome.apply(lines.get(1)));
	}

	@Test
	public void toGenomeDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_8222_8.txt");
		System.out.println(toGenome.apply(lines.get(0)));
	}
}