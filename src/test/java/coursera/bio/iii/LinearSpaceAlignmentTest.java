package coursera.bio.iii;

import static coursera.bio.iii.LinearSpaceAlignment.align;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class LinearSpaceAlignmentTest {

	@Test
	public void alignSimple() {
		String v = "PLEASANTLY";
		String w = "MEANLY";
		Triplet<Integer, String, String> result = align(v, w);
		assertEquals(Integer.valueOf(8), result.x);
		assertEquals("PLEASANTLY", result.y);
		assertEquals("-MEA--N-LY", result.z);
	}

	@Test
	public void alignSimple2() {
		String v = "PRTEINS";
		String w = "PRTWPSEIN";
		Triplet<Integer, String, String> result = align(v, w);
		System.out.println(result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}
	
	@Test
	public void alignExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("linear_space3.txt");
		//List<String> lines = FileUtil.loadFile("global_alignment.txt");
		Triplet<Integer, String, String> result = align(lines.get(1), lines.get(2));
		System.out.println(result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}

	@Test
	public void alignDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_250_14.txt");
		//List<String> lines = FileUtil.loadFile("global_alignment.txt");
		Triplet<Integer, String, String> result = align(lines.get(0), lines.get(1));
		System.out.println(result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}
}
