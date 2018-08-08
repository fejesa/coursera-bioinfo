package coursera.bio.iii;

import static coursera.bio.iii.GlobalAlignment.alignment;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class GlobalAlignmentTest {

	@Test
	public void align() {
		String a = "PLEASANTLY";
		String b = "MEANLY";
		Triplet<Integer, String, String> result = alignment(a, b);
		assertEquals(Integer.valueOf(8), result.x);
		assertEquals("PLEASANTLY", result.y);
		assertEquals("-ME--AN-LY", result.z);
	}

	@Test
	public void alignExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("global_alignment.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(1), lines.get(2));
		assertEquals(Integer.valueOf(lines.get(4)), result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}

	@Test
	public void alignDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_247_3.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(0), lines.get(1));
		assertEquals(Integer.valueOf(1632), result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}
}