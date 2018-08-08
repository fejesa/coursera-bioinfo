package coursera.bio.iii;

import static coursera.bio.iii.FittingAlignment.alignment;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class FittingAlignmentTest {

	@Test
	public void fittingAlignment() {
		String v = "GTAGGCTTAAGGTTA";
		String w = "TAGATA";
		Triplet<Integer, String, String> result = alignment(v, w);
		assertEquals(Integer.valueOf(2), result.x);
		assertEquals("TAGGCTTA", result.y);
		assertEquals("TA-G-ATA", result.z);
	}

	@Test
	public void fittingAlignmentExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("fitting_alignment.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(1), lines.get(2));
		assertEquals(Integer.valueOf(lines.get(4)), result.x);
		System.out.println(result.y);
		System.out.println(result.z);
	}

	@Test
	public void fittingAlignmentDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_248_5.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(0), lines.get(1));
		assertEquals(Integer.valueOf(19), result.x);
		assertEquals("GCTCAGCTGCTATTTATCCTTAA-GTTATGCTATACACGTCT-ATGATC-TCTGGGTGCGACCAGTAG--TTATGGGAACCCAA-AATC-AAGAATAAGAGGCTCCGCG-T", result.y);
		assertEquals("G-TC--CTGCCATCT-T-CTTAACG-CAAGC-AT---GGT-TGAT-A-CATCACGCTGAAATCA-TAGACTT-TCTGAA-CCAACCCCCAAAGAGGGAG-GGCT-CGGGAT", result.z);
	}

	@Test
	public void fittingAlignmentQuiz() {
		String v = "GTTGGATTACGAATCGATATCTGTTTG";
		String w = "ACGTCG";
		Triplet<Integer, String, String> result = alignment(v, w);
		System.out.println(result.x);
	}
}