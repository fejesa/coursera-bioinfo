package coursera.bio.iii;

import static coursera.bio.iii.CycleToChromosome.decode;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class CycleToChromosomeTest {

	@Test
	public void decode() {
		String in = "(1 2 4 3 6 5 7 8)";
		assertEquals("(+1 -2 -3 +4)", decode.apply(in));
	}

	@Test
	public void decodeExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("CycleToChromosome.txt");
		assertEquals(lines.get(3), decode.apply(lines.get(1)));
	}

	@Test
	public void decodeDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_8222_5.txt");
		System.out.println(decode.apply(lines.get(0)));
	}
}