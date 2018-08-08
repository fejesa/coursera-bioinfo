package coursera.bio;

import static coursera.bio.LinearPeptide.count;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class LinearPeptideTest {

	@Test
	public void count() {
		assertEquals(Long.valueOf(7), count.apply(3));
		assertEquals(Long.valueOf(12), count.apply(4));
		System.out.println(count.apply(22489));
	}

	@Test
	public void countDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_100_2.txt");
		assertEquals(Long.valueOf(252888806), count.apply(Integer.valueOf(lines.get(0))));
	}
}
