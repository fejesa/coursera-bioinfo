package coursera.bio;

import static coursera.bio.CountingPeptides.count;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class CountingPeptidesTest {

	@Test
	public void count() {
		assertEquals(Long.valueOf(14712706211L), count.apply(1024));
	}

	@Test
	public void countExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("counting_peptides.txt");
		assertEquals(Long.valueOf(lines.get(3)), count.apply(Integer.valueOf(lines.get(1))));
	}

	@Test
	public void countDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_99_2.txt");
		System.out.println(count.apply(Integer.valueOf(lines.get(0))));
	}
}