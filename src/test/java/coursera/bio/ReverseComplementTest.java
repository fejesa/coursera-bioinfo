package coursera.bio;

import static coursera.bio.ReverseComplement.reverseComplement;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ReverseComplementTest {

	@Test
	public void reverseComplement() {
		String reverse = reverseComplement.apply("AAAACCCGGT");
		assertEquals("ACCGGGTTTT", reverse);
	}

	@Test
	public void reverseComplementExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("reverse_complement_data.txt");
		String reverse = reverseComplement.apply(lines.get(1));
		assertEquals(lines.get(3), reverse);
	}

	@Test
	public void reverseComplementDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_3_2.txt");
		String reverse = reverseComplement.apply(lines.get(0));
		String expected = "GTGTAAACGACTCTTCTTACGCGGAGTGAACCACGGGGCGCGACCAGCCTACTGAGAATAACTCTCTGAACCTGCCTAGTAGGGTCTTCAAAGCCGTAAGCAGCACAATGAGTCCGACGTATCCGGAAGGTGGCGATTTGTCCCCTCATGAATGGCGCAACTAGTGGGTTCAACTTGGCTTGGGCATGCCCCCAGAAGGCGGACCTAACCCACGCCATTGGACGCACGCTTACACAATAATATCCCATCTGATAACCGTAAGCGGCAGGTCGGTCAGAGTCCTTATTGTGTGAGCTTGCATCTTAATAACTTCGGCGAGCTAAAGAGCAACCCTATAAATTAAAATAGATAAGTGTGAGTCGTTCCCACAGACGCGGAGATTATAGTGAATAACTCGCGGCGGTTCGAAACCAGCATTTTAGTATAAAATCACCGGGTTGAGCCGGACCTTGCAGCTCTTACTGACCTAGTAATCCAATACCGCTCGGCAGTGGAATGTAGGAGATTAAAAAGTAACGGATGTATAGTACGACTTAGCGAAGAATAAGGACTCGCTACGCGCGTTCTGCAATGCTGGGGTTTACACTTAAAGGTAGCGATTTGCGAAACCTCCTACTTAAGGGAATGTGCCGAAATCGTCGTGATATATGCCTAGGGTGTCGGAAGTCACATTCGCGAACGAGATGGGTTTGAAAAAGCCTCGTTCACATGGGAACCATAGTGCACGAGTCGATATCCATCTCCCGTGACCAGGCGCCTGGTGACCTCGCGGAAAATATGTTTCTTCGAACGCACCGGTTTCCTCGAATTTGCGAAACCCTGCGCATCTGTTTGTAATGAGCTAGGTAGGAGAACTAGGTTACCAATTGGTGCCCAAACGGCTGTTCGCGGTGAAATTGAAGGACAAACTGACACAACTCGCGCACAGCGCCGTGCAAGCGCACGATTGCGGCTGTCGGGCCTACATTATGGCCTAAACAGACTATAAGCCACGTCTCAACGAGAGTATAATTAGCCGAGAGATAGGATGAGGTAGCTAGTTAAGGAACCGGGCAGATAGTGGGATCCTCGGCATAGCTGGGTTTCGGTTCACAGCTGACAATCAATGTTGCAGTTTGGCATCATCAGAGCCACAACCTATAGTAAGCCCATCATAAGAGCTGCTTTAAGGCTGGTCTAGTACTTTAATAGCCTGTATGATTAAGATACCGAAAGTTAGGATCTTCATGATCAAGCACCTTGTTTTCCGTTATCCGGATCATCGCGGCATGAACGTTTCTTCGGGGATGGCCCAGAACCGAAGCTCCACAGTTGCACAAAGTCGTATCTCTGGGCCATCTAGGAGGTCATTCCCTTATCGCAGACTACGTAATGTCGTTTCCCAGGGCCTATACTGATCGTTCCGAGCACCCTCTGAAGTGGGGTTTGCCTGTCCGTACGGTGACCACAAAGTCCGGTTTCTTGCACAGTCCCATACTATTGATTTTTGAGGGTTGTTCAGGGTACCTCGTGTTGAAGAGAAAAGCATAGGAGTTGACATTCGAAGTTCTCCCCAGATTTCAGATAAAGGCAGTTGGATGTGGAATCTCCCCTCGGTCAGGCAATTCCGCGTTATACGCAACGTGGAGACTTAACGGTCAAGAAAAGCGCTCCGAGGCTGTGTACTGGTCAGAAGATCACCGCAGCTCGGGTTTTGTTCGCGGGTCAAGGGGTCGCGTGTCTTAGCGTATGACTATTACGAAACGCCTGAGTCGCTGTCCACAGGGCTGTAAGTGCTCAACACCCTACGCACTAGTACTCTACCCTCCGCCCAACCTTAGGTATGGGTTAGCAACCAAATAGGGTCGTTGGGGAACCAGCAGCTGAAACCTCCTATCTTGAATATTTGGAAATCATGAGGACGGTTCACCCGGAGTGTATGGTGCGTCGGACTCACTGGAGTCCGGTTCATTCACAAGGGCAAATTTAATGAGCACCGAATTTCGATTAGAGTAACCCGGGGAGATGACGTACAAAACCGCGAGACCTTACCTTCGGTCCGTTATACAGGCTCCTGTTATTCTACTTCGTTCTTGGCAGCTGTCTGGGCAAAAAAAGACGAGGTGCGCTGTCCGGTTGTAATCAGCAAAAGGGCCGATGAGCGAGCCATGGAACACAAGAAATACGTGGCTTAACCAGCGCTCTCGGTGAAAGTGCTAACCTCATGTATGTGTATATAGAATCTCCAGCCCTCTATGAATCCCCAAGCTATACCAGCGTTGCGAATCACACATGTTCAGTCATCCTGCCAGAGGCCTATTGCTGGTATCATTTTTTGCCTCACGTCATGCCAAACGGCCGGAATACCTTCGTAATACCTCCCTGTGCGACCATAAGGGAGCGTTCGCCGTACCAAGAACTACTAAAATGATCGAACGTCTGCTTGTAAATCATGTCGTTATAATCCTACGAGCGTACTTGATGAGTAAGTTAATGTTTAGCTGCAGCCATGATATTTGAACATTCCGGTACCCGAACGGAGATTGGGTTCAACCTTCTGTAGGGTGAAACTGGGGAGCGAACCACCATGATAACGCCCGCACTAACAAGTGAGACTTCGGTCATACTTTGACCAGCGGCGACCAAAGACCTAGGCGCGAATGTTCCAGGTGGCCCGCGTATCTATGACGTAAATTCGCTGTTCCCTGGGGTTCGGTGCCTTGAACGCCTTGCAGGTTTTTTGTACTACAAGTCTCAAGGAGAACCGCAGTTGGCACTTACAAGCCTTACCTGGGTCCCCGTGGATGATAGTGGTGAGCTCGTACAAACTTGTATTAGCATTGGGCCCCAGCCCACCGCGAATGCAATTGTCAGCGACGCATCGGCGCGAGCTCAGAGATCTAACCGTGAACCTTGATAAGGTTGAGATCCCGTACCGGTCGAATCTCGTTCTTGTTGCCTCAACGGTCGATTATAGAAACTCATTGTCTATGACTCCGCGGGTCGCGCTATCAGTAACTCACGGACAGATTTTCGCACTCCAGCTCCTTCCGAATTTTTCCCACGGTGTCCCAGGACACTGTTTGACCAGGCGATCAATTTAATCCGTTGCACTACCTCCTTCTTATTACTCATCTCGGATACGTTCATTTCATTCTACGCGGCATCCGTTGGGCAACCATCTTCAACCCAGACGCTTACATCTGGTCTAGGCACCGTATGGGCCGTGGATCGCATTGCGTTGTAGCTCGTTGTCGAGGGGTAACATGCCGATGGGAAGACAGCTCCCCTCCGATCGCTTCGCGCCGGGACGGGAATAGGCTATCCATTGCTCAATAATAGGGTATATACTAGACCGGTGGGATTATTGTTGACTGCCGGCAATTGAGTAACTTGACCTCACACCCTTACCGGGTTAATTTTGTAGTCCCCTGGGGCAGATGTGACCAGGTAGACAATCGATTTGAAGTGCTAATCAAGCGCTATCAGGGTAACTCCGAGAAACCTGCACCGCTGAAGAGAGCGGGTGTCAATTTAAAAAGTTGATCTAATCGGTTACGTTGGAATTCTTTCGAAGAGCTTTGAGACTGTAGATCTGCTCCGTGAGGCCAAGCAATTGTACAGCTGGACGGTATATTATCTTGGTTAATTTTCGCTCGAGGGGCCTGGTTCAACTATGAGTGATTCACATATACCCCCAGCTAGCAATCGGATCTTTATCGGCACGCCCACTGAACAGGTATTGAGACGTTGAAACATTACTGAACAGTTGTGTCCTTGCTACGCATGGACCCAGCCTCGCTACGACGGAATCTTATCGTCTTGCCAGACTACCTGACGATCCAGCCAGTCAGGAGAAGCAGTTATTTTACGCGGAGAAGTAGCCGAGTACAGACACACTGTGTCGGCTGAATCCAGTTCTAGCCAAGCTTGGGAACAGAACCCGTCTACGGGATGATCTTCGAATAAAAGGGCTTGACACCACTGTAAACCAATATGAGGTTACACTACTCCCACGGTAGACGCCTCGTTACTAGGGCGTTCTGGCTGAAGCGCGGGGCGGGTTTTGGGTTATACCTTCGCCCTTACCGGCATCAATGAAGTCCCCTCTCGAACCCCTTAGAGATATGGCAATAAAGAGTCTTTCGTGTCAAGGAACCGCTTTAACTACCAGGACAGCATACACTCTCATAACTGTGCCCGAGCGTACATAGTCCCACAAACTTTAATATACCACTTGTGAACACTCGGACCCGGCTTATTTACAGCAACTGTTATAATCTATAGATCATCCGTTAAGCATGGTCTGAAGAAGGTGCCAATCATTCGGCTCTCAACACCTGCATAACGTTCGTGATTCTTAGAAGAAATTGTCTTACTTTGGACTTAGTCAGCTGGTGCGCGGTGGACTGTGGCCCAGTGATGGGATCTTATCGTTGTTCCCGTCGTGTTTTCATGGAAATCTTTATGATGGTGCGATTAGATGTGAGTATGCAACCGTTTACTTAGAGAATCTTCATAAAACGAGGGACACGACCGCGCAAGACACAAATTACTCAATCGCGCCAGCAGAGCAGGCGGGAAGGTGTAAACTACGGTTCTTCAGGGAACCCTGATAATTGCTGAGGCCAGCCATTGCAATGTAAATACTGGATTTTATCCTTACCGTTGCATGTAAAGAAAATTGTGATGAATACAACTAGGGAGGCGGGCCTTCCGAGGTTTCCGATCAGAATATGAGCTCTATCAGGTCGCGCCACCTCGAAAGGTTTGGTACGCTCCCGCGTTTGTAAAAAGACGCTTGACTTTCGTACGGTAATTGGGCTAGATATCAGTTTCTAAGTGTTGCATGTATCAGACGCAGGCGCCATATTACCATACGATAGCGTCAGGCAGATTTTCCCATACGACTGAATTTAGGCCACACTTTCGTACGCTTCAAACTAATGTGAATCACGCGAGAGACTCACCACTTCCAACACTTGTGTTCCGTACGCGGCATCGTGCGAGTTCCGCATAGGTGTGTTAATACCTCGCCACCCATCGGTAAGTTGGGAAGCTCGGAATCGAACTCGCGATTAACCGTGTTACATTGGACAATCACCATCATCCCAAGTGAGCAGAACCCAACACAGTTTGGTGCTACCCACCAAACCATAGATAAGGATGAGGTAATTGGGTAAGGAAGAAACCTCTCGTGATAGGGGCCGTTAGGAACGGGCGTACACAAGCACATGACACGAACTGAATCCGAATCGGGTTAGCGTGACACCTCTAGCGAATCTAACGATATCCACATACAACGGTCGTGTAATGCTCCGTTGGCAGGGCGCTCTTATGGAAACGCAAAAAACCCTCGGGCCTCTCGTGCTCTTTTCCACTGTCCGTGGCGACTTAGCTGACAGGGAGCCTGGGGTCGCGAGACCTGTTCCTGGTTTTCACCGGAAACTTTCACTCTCGATGAGAAAGTTGTCAACAAGCACATCGCAAAACCACACATCCGGAATGTACACAGACGCGGCGATGTTCATTACTGACTTCGGTTGCGTGACAATTAATTTCATTCCGTGTCGCATCGCTCTATAAGGGACCGAGCACACTTACTTGACCTCGCAATGTCCGGTGGGATGCGGCCTCCATGCTGACCCGCTTGTGACTGAAAACCCCAATGACTAGCACACTAGTTGTACTTCCAGTCTATCTCCCGACCTTACTGATACACTGCCCTGCCACCGACCAGCGAGAATGTTCTATTTGTCTGGCTCGGATGGTCTAAGGGGACAAATTCCTCTTGGCACCGCGTGGGAGCGCTTCTACCGGCGCACTGTAGCAGCCTGAGTCAATCGTTCGCGAATCCTAACAGTACGTATGGGGATTGACTCGACCCTTAATTATGGGAGCCTGAGTTGACCCTAAGTGGTAAGTCATGTGCTATTTCGAGCCCAATGGAGGTCTATACATTTCAGTATCCCTGCTTTTTGGCTAATTTGAAACGCAATAGCAAGGGCTTAGTGTGGCCGACCGATGCAAAGCCTAGAACCTACAGCACCTTTTGCAGACGACTAGCAAGTTGAGTGATCTGGATCTCCTACCCCTCGATCCGCTCGAACCTAATAGTTGACCGTAGTGACACCGAACGCAGACATGCCAGGCCGACTGGCATGTTACTTAGCGCAGATATGTGGCGACTCCCCCCCCTGAGTGACCGTTTGTTCTTAGAGGGGGAATATGTACTCCTATCAGCGAGTGGCTCGATCATCGTTTGCATTTCTTCCTTTCTAATTAGTCGAAAAAACGACAACCCTGATACAGCAGGCGCGTTGGAAGGGCGCTCCTTGTTACAGCCGACGTGTGTCTAGCCCGGTAATGCAATGCCGAACTCAGTCACATGGCCTCAAACGTAAGAACTCCCGTCCACAGGTACCCCTCATGGGTCCCCGCCACGTAAAGTACGGCCGCACTCCGCAATCAGTAGTAACTTAACCCTTAGCTAGAGATACCCCTTTCAATATCCTCAGCGTCCTACATAGAACCCAAAGCTCGAGTGAACAGATAAACGTTAGCGCAAAATTACTGATATAGTAAAAACCGGGCAACAGTTTAAAAGGCACGCGCTCATCCTAATTCGCTTCCGCCACTTAAAGTGCAGCCCATGGGTGAAAGTAGCTCCGAAACGAACGAGTGCAAGGGAACTTGAGACCGTGGTCCCTTGGAGTTGTAGTTGGGCTTGGAAGTGCACCGGGTATTAGCCTGAGTCTATTACTGCATCAGAAGTTAAGATTAAGACCATGGGATCATTGCTTCCTCTACTGAATGCCTTCTAGGCAGAACGTGTGTTGATGCTTGGAACGACCTAGTTTGAGGTCGTTCAGAAGCCCGCAGGGAGAGCGCCTCATAACCAAGTGTCGGAACGTGATTGGATTATGCCAGTCGTTCTTTACCCTATTCCGTAACGCGCGATAATCCACGACGAGCAACACGACCCGTCTGAGCGGGAGCCCGCTATGTAATGTAGACTCCATTCTTGGTATTTAAGAGGATGCAAATTTGAGGATCACCTGACGACAGCCCGCATCTTTTAAAGACTAAGAACAAAGAGTGGGTTCCGACATCTAGGGCGGCTCTCAAGCTCTGAGTTGTTTTCCGTAAACGGACTCGACCGATGGTGCACGCTCAATCAGTGGGCGATGCCCAGTCCTTCCTGTAGAACTCTTTAGTCATCAACACAGTCTCCCTTAACGCAGTTCTGGACCATGTGAAATAATTTACAGGCGTTTTTGAAGACTTTGGCCGGGCACATCTTGTTATGGGCTACTGTTGACAGCTTCGACAGGTGTCTAGTAATAACGCTAGCGGAGGGCTTGCGCAACATCGGTCGAAAAGCGCTTAACGGGATACGTTAACGTAACTACGTGGCGTACTTTTCATAAACCGTGAACGTTCCCGAGCGAGAAACAGGACAGATCGGGTGGAAGGTTACCCGCAGATGGGCCATGATTCTTTCAGATGGAAACATTAGTTGACCGCGCAAAAGGAATTTCCACTGCGCTTGGCCATGATAAGTTATCCCGCATGGTCGGTCGTGGCGATAAGCGGAGCTTTTCGAACACTCTGACGAATAGGGGTCCGTATGAAATGAAGCTATTATGATAAAAATCTAAGATTCCTTCTGCTGGCGAATGAACCCCTCATCATTACGTTTATTCCAAATCATTCTTGAAGGTACCTCAGATCAACCTCCTAGTAGCTATGGTCGAAGATAGTGATTCCTAGGCGATTTTCTAACGTAGATCTTTTAGCTCACTAATGCTATGAAGACTAATAGAAAGGTGATCCGTGGCGTGAGAGGTTTGGTGGGGAGGTGTATCTGATTCATGGCGACCTCGAACTGGATGGCCCATACAACATCCTTTCATGGTACCGGGCTGGCGGTCCAAATGTGATAGTTTTTGCAAAATATGGTGCGGGGAAACGTCATGCTGAATCATATGGCCGCTGATAGAAGGGCTGCCATACGTCGAGGCACACACGACGCACGCGGGGTCGTATTTCGCGGTCGCGACGTGTGATTTTAGGGACATGTGTACCTCTGGAATATCGTACTAATATGCGAGATTTCAGAGTACTGTCTCTGCCAAGCCCTTGAACGAACAGGACGGGTGTATGATGCGTTCATTGTTGGTTAGGTTCAGATATTGAGTATATACTCCTATTGTGCGTTTTATCCGCTGCTTGTAGTCTGTACCTCTACTTTGTTACCCGCATAGATCTGAACAGACCTAAGGCCAGGGGGCTATCCTGTGGGAGGGATCCCTCGGCATGTAGGCTGGGAACCTCGAGGATATTTGCGGGGGCTCCGGTAACGGCACCCTCAGCAAACCCGAGGCTAGAGCCTGAAATAATTCAACTGTTGGGGCTTTTGAACTGCCTTGATGGGCGATACCAGTGCCGGCGAGGTAAGCGGGGTCTATTCCGTCATTTAACAGGCCGGTACGTCAGGCCCGAGGGGCTGTGAGCGGACCGGTCCTAGATGCCCTAGCGTATACATGCGACCCCGCATGTTGCCCTCCGGAACGTATTTCTAAAGGCGTACTGTCTGTTACATTTGCGATAACGTTCACCAGTGATTCAATCCGCACGAACCGTGTATCTTGAAGTTGGAGAATGTACGATATTCTGATGTATGACCATAAAACCTATTCCCTCCAGTAAGAACGCCTTTAATAATTTACAAGAGTGACTTTTGGCCCAGGTACCTGTTGTTAATGCGGGTGTTAAAACCAGCTAGCCAAGAAATCGTATTATACGACCGAGCATCAAGTATTGGTCGCTCTTTGTGCGGGATCCAGGAACTGTGAACTAGCAATGAGTATAGGAGGCCTGCTTGAGATTCTCTCTTGCTCGAGGGAGCGCGATAGCTGCCTATAACTCCCGGAGGCCCAGGCAGCTACTCATTCGTTTGTGTAGTAAATCAATGGCCCGGCCCCCCACACCCTCAAGTTACTGACTGGGTAGGGAATGGGCTTCTGCTTGATAAAACAATTTTTCGATCCGTCCCATCTCCTGCGGCTTTAAGCATGCGGCGGGTATCCCAGTTTGTATCTGGTTCGAAGTAATATTAGCGCTAAGGCATCTTAAGATTCCAATATGACCGGGGGGCAACGACATATTATTGCCGGTGACGCCTATGGCTAACGGATAAACCAATTCGGGAGTACAACATGAGAGGATTCGATCAGAGATGAGATTCACCGATAAAATATGTCC";
		assertEquals(expected, reverse);
	}

	@Test
	public void reverseComplementQuiz() {
		String reverse = reverseComplement.apply("CCAGATC");
		assertEquals("GATCTGG", reverse);
	}
}