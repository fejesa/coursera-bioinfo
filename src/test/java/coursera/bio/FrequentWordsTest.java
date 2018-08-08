package coursera.bio;

import static coursera.bio.FrequentWords.frequentWords;
import static coursera.bio.FrequentWords.frequentWordsWithMismatches;
import static coursera.bio.FrequentWords.frequentWordsWithMismatchesAndReverseComplements;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class FrequentWordsTest {

	@Test
	public void frequentWords() {
		String words = frequentWords.apply("ACGTTGCATGTCGCATGATGCATGAGAGCT", 4)
				.stream()
				.collect(Collectors.joining(" "));
		String expected = Arrays
				.asList("GCAT", "CATG")
				.stream()
				.collect(Collectors.joining(" "));
		assertEquals(expected, words);
	}

	@Test
	public void frequentWordsExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("frequent_words_data.txt");

		Set<String> words = frequentWords.apply(lines.get(1), Integer.parseInt(lines.get(2)));
		Set<String> expected = Arrays.stream(lines.get(4).split(" ")).collect(Collectors.toSet());
		assertEquals(expected, words);
	}

	@Test
	public void frequentWordsDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_2_9.txt");
		Set<String> words = frequentWords.apply(lines.get(0), Integer.parseInt(lines.get(1)));
		assertEquals("CCCCAACCCCCA CCCAACCCCCAA CCAACCCCCAAC",
				words
				.stream()
				.collect(Collectors.joining(" ")));

	}

	@Test
	public void frequentWordsQuiz() {
		String words = frequentWords.apply("CGCCTAAATAGCCTCGCGGAGCCTTATGTCATACTCGTCCT", 3)
				.stream()
				.collect(Collectors.joining(" "));
		assertEquals("CCT", words);
	}

	@Test
	public void frequentWordsWithMismatches() {
		assertEquals("AAAAA",
				frequentWordsWithMismatches
					.apply("AACAAGCTGATAAACATTTAAAGAG", 5, 1)
					.stream()
					.collect(Collectors.joining(" ")));

		assertEquals("ATGC GATG ATGT",
				frequentWordsWithMismatches
					.apply("ACGTTGCATGTCGCATGATGCATGAGAGCT", 4, 1)
					.stream()
					.collect(Collectors.joining(" ")));
	}

	@Test
	public void frequentWordsWithMismatchesExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("frequent_words_mismatch_data_1.txt");
		String[] s = lines.get(2).split(" ");
		assertEquals(lines.get(4),
				frequentWordsWithMismatches
					.apply(lines.get(1), Integer.valueOf(s[0]), Integer.valueOf(s[1]))
					.stream()
					.collect(Collectors.joining(" ")));
	}

	@Test
	public void frequentWordsWithMismatchesDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_9_7.txt");
		String[] s = lines.get(1).split(" ");
		assertEquals("CTTGCT",
				frequentWordsWithMismatches
					.apply(lines.get(0), Integer.valueOf(s[0]), Integer.valueOf(s[1]))
					.stream()
					.collect(Collectors.joining(" ")));
	}

	@Test
	public void frequentWordsWithMismatchesAndReverseComplements() {
		assertEquals("ACAT ATGT",
				frequentWordsWithMismatchesAndReverseComplements
					.apply("ACGTTGCATGTCGCATGATGCATGAGAGCT", 4, 1)
					.stream()
					.collect(Collectors.joining(" ")));
	}

	@Test
	public void frequentWordsWithMismatchesAndReverseComplementsExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("frequent_words_mismatch_complement.txt");
		String[] s = lines.get(2).split(" ");
		assertEquals(lines.get(4),
				frequentWordsWithMismatchesAndReverseComplements
					.apply(lines.get(1), Integer.valueOf(s[0]), Integer.valueOf(s[1]))
					.stream()
					.collect(Collectors.joining(" ")));
	}

	@Test
	public void frequentWordsWithMismatchesAndReverseComplementsDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_9_8.txt");
		String[] s = lines.get(1).split(" ");
		assertEquals("GTAGCT AGCTAC",
				frequentWordsWithMismatchesAndReverseComplements
					.apply(lines.get(0), Integer.valueOf(s[0]), Integer.valueOf(s[1]))
					.stream()
					.collect(Collectors.joining(" ")));
	}
}