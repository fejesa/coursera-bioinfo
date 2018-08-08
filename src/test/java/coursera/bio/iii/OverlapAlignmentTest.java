package coursera.bio.iii;

import static coursera.bio.iii.OverlapAlignment.alignment;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class OverlapAlignmentTest {

	@Test
	public void align() {
		String v = "PAWHEAE";
		String w = "HEAGAWGHEE";
		Triplet<Integer, String, String> result = alignment(v, w);
		assertEquals(Integer.valueOf(1), result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}

	@Test
	public void alignExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("overlap_alignment.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(1), lines.get(2));
		assertEquals(Integer.valueOf(lines.get(4)), result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}

	@Test
	public void alignDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_248_7.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(0), lines.get(1));
		assertEquals(Integer.valueOf(51), result.x);
		System.out.println(result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}
}
