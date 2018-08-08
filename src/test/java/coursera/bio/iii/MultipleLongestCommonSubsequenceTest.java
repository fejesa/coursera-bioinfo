package coursera.bio.iii;

import static coursera.bio.iii.MultipleLongestCommonSubsequence.align;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;
import coursera.bio.iii.MultipleLongestCommonSubsequence.ThreeWayAlignmentResult;

public class MultipleLongestCommonSubsequenceTest {

	@Test
	public void alignSimple() {
		String v = "ATATCCG";
		String w = "TCCGA";
		String u = "ATGTACTG";
		ThreeWayAlignmentResult result = align(v, w, u);
		assertEquals(3, result.longest);
		System.out.println(result.alignA);
		System.out.println(result.alignB);
		System.out.println(result.alignC);
	}

	@Test
	public void alignExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("multiple_longest_common_subsequence.txt");
		ThreeWayAlignmentResult result = align(lines.get(1), lines.get(2), lines.get(3));
		assertEquals(11, result.longest);
		System.out.println(result.alignA);
		System.out.println(result.alignB);
		System.out.println(result.alignC);
	}

	@Test
	public void alignDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_251_5.txt");
		ThreeWayAlignmentResult result = align(lines.get(0), lines.get(1), lines.get(2));
		assertEquals(4, result.longest);
		assertEquals("------CTGGAGGGCA", result.alignA);
		assertEquals("---TAACT--TG-GTC", result.alignB);
		assertEquals("GGG---CT-TTGAG--", result.alignC);
	}

	@Test
	public void quiz() {
		String v = "TCTAGCGAAC";
		String w = "ATTACCGATC";
		String u = "TTCACTGACG";
		ThreeWayAlignmentResult result = align(v, w, u);
		System.out.println(result.longest);
		System.out.println(result.alignA);
		System.out.println(result.alignB);
		System.out.println(result.alignC);
	}
}
