package coursera.bio.iii;

import static coursera.bio.iii.ChromosomeToCycle.encode;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class ChromosomeToCycleTest {

	@Test
	public void encode() {
		String in = "(+1 -2 -3 +4)";
		assertEquals("(1 2 4 3 6 5 7 8)", encode.apply(in));
	}

	@Test
	public void encodeExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("ChromosomeToCycle.txt");
		assertEquals(lines.get(3), encode.apply(lines.get(1)));
	}

	@Test
	public void encodeDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_8222_4.txt");
		System.out.println(encode.apply(lines.get(0)));
	}
}
