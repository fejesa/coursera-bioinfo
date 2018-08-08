package coursera.bio;

import static coursera.bio.PeptideEncoding.peptideEncoding;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class PeptideEncodingTest {

	@Test
	public void encode() {
		List<String> expected = Arrays.asList("ATGGCC", "GGCCAT", "ATGGCC");
		assertEquals(
				expected,
				peptideEncoding(
						"ATGGCCATGGCCCCCAGAACTGAGATCAATAGTACCCGTATTAACGGGTGA",
						"MA"));
	}

	@Test
	public void encodeExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("peptide_encoding_data.txt");
		List<String> expected = lines.subList(4, lines.size());
		expected.sort(Comparator.naturalOrder());

		List<String> result = peptideEncoding(lines.get(1), lines.get(2));
		result.sort(Comparator.naturalOrder());

		assertEquals(expected, result);
	}

	@Test
	public void encodeDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_96_8.txt");
		List<String> expected = Arrays.asList("TGTCAAACGGCTTTAATGGGAACAACTCAT",
				"TGCCAAACTGCGCTAATGGGCACTACCCAC",
				"TGCCAGACGGCTTTGATGGGCACGACACAC",
				"TGCCAAACGGCTCTTATGGGCACTACACAT",
				"TGCCAAACTGCTCTCATGGGGACCACGCAC",
				"TGCCAGACAGCCCTGATGGGAACCACACAC",
				"TGCCAAACCGCGCTAATGGGCACTACACAT",
				"TGCCAGACTGCATTAATGGGAACGACGCAC",
				"TGCCAAACTGCCTTGATGGGGACCACACAT",
				"TGCCAAACCGCACTTATGGGCACGACCCAC",
				"TGCCAGACAGCCCTGATGGGGACGACGCAC",
				"GTGGGTTGTGCCCATTAACGCGGTTTGACA",
				"GTGAGTAGTACCCATTAATGCGGTCTGACA",
				"ATGCGTAGTACCCATTAAAGCGGTCTGACA",
				"ATGTGTAGTACCCATTAATGCTGTCTGGCA",
				"ATGTGTGGTCCCCATTAATGCCGTTTGACA",
				"ATGCGTGGTACCCATGAGAGCAGTTTGACA",
				"ATGGGTAGTCCCCATAAGGGCAGTCTGGCA",
				"ATGGGTAGTCCCCATCAGAGCTGTTTGACA");
		expected.sort(Comparator.naturalOrder());

		List<String> result = peptideEncoding(lines.get(0), lines.get(1));
		result.sort(Comparator.naturalOrder());
		assertEquals(expected, result);
	}

	@Test
	public void bacillusBrevis() throws Exception {
		String dna = FileUtil.loadFile("B_brevis.txt").stream().collect(Collectors.joining(""));
		List<String> acids = Arrays.asList("Val", "Lys", "Leu", "Phe", "Pro",
                "Trp", "Phe", "Asn", "Gln", "Tyr");
		String peptide = acids
				.stream()
				.map(a -> Utils.proteinDictionary().get(a).toString())
				.collect(Collectors.joining(""));
		List<String> result = peptideEncoding(dna, peptide);
		System.out.println(result);
	}

	@Test
	public void countQuiz() throws Exception {
		Map<Character, List<String>> acidDict = GeneticCode.acidDictionary();
		String protein = "LEADER";
		long product = protein
				.chars()
				.reduce(1, (m, c) -> m * acidDict.get((char) c).size());
		System.out.println(product);
	}
}