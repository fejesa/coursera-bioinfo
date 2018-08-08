package coursera.bio.iii;

import static coursera.bio.iii.Backtracking.lcs;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class BacktrackingTest {

	@Test
	public void lcs() {
		String s = "AACCTTGG";
		String t = "ACACTGTGA";
		assertEquals("AACTTG", lcs.apply(s, t));
	}

	@Test
	public void lcsExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("longest_common_subsequence.txt");
		String s = lines.get(1);
		String t = lines.get(2);
		System.out.println(lcs.apply(s, t));
		System.out.println(lines.get(4));
		//assertEquals(lines.get(4), lcs.apply(s, t));
	}

	@Test
	public void lcsDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_245_5.txt");
		String s = lines.get(0);
		String t = lines.get(1);
		assertEquals("TAGACACTACCTCAAGCAGTCTTTAACTTATGTTACATGAAGATGTCGAGAAGCACGCTACGGGGCCCAGGGGTGATGACGGGTCGTCACAATGAAACCGATGGGTAGGGTTATACAAACAGTCTTCTCTGGACCACGCTGGAAAGTGGCCAGGTAACTTAAAACTAAAATTCACACCCTCATGTCTGATACGGGTTACAAGGCCGTGCCCCTCACCAATTTAAGCGCTTATATTTGTAATCAACAGATCCGTTGAAGCCGCTCTGACCCTGTCGGACCCCCTCCAGGACGCAGAAGGGACCGCGAATAATTATCCCTAGAACAGAGTTTTATGCCTTCGTGGCGATGCCCAAAACTAAGCCTACTGGGGGATCTCAAATTCTGGGGCTAAGCACATGACCGTCGATCTGTCCACCATAAGAGAACACGTCATGACTATGCTATCTTTTGTTATTAAGCGTTACTTCGCCACCTTCCTAATATAAATCTCGGTCCTGGTATGACACACCATCGATTTACGTCAAAACCTGCCATTCGTTCTAATCCCGATTAACAAGACTACACTCCACCCAACTGCTTCTACAGCGGCTCG", lcs.apply(s, t));
	}

	@Test
	public void lcsSimple() {
		String s = "ATCTGAT";
		String t = "TGCATA";
		assertEquals("TCTA", lcs.apply(s, t));
		
	}

	@Test
	public void lcsQuiz() {
		String s = "GCGATC";
		String t = "CTGACG";
		System.out.println(lcs.apply(s, t));
	}
}
