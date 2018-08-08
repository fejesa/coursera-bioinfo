package coursera.bio.iii;

import static coursera.bio.iii.LocalAlignment.alignment;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class LocalAlignmentTest {

	@Test
	public void align() {
		String a = "MEANLY";
		String b = "PENALTY";
		Triplet<Integer, String, String> result = alignment(a, b);
		assertEquals(Integer.valueOf(15), result.x);
		assertEquals("EANL-Y", result.y);
		assertEquals("ENALTY", result.z);
	}

	@Test
	public void alignExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("local_alignment.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(1), lines.get(2));
		assertEquals(Integer.valueOf(lines.get(4)), result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}

	@Test
	public void alignDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_247_9.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(0), lines.get(1));
		assertEquals(Integer.valueOf(1209), result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}

	@Test
	public void quiz() {
		String v = "TACTATTTACAGTAGACACGT";
		String w = "AACAGACATACAGATACCT";
		// String w = "AACAGAC-ATAC-AGATACCT";
		
		Triplet<Integer, String, String> result = alignment(v, w);
		System.out.println(result.x);
		System.out.println(result.y);
		System.out.println(result.z);
		
	}
}
