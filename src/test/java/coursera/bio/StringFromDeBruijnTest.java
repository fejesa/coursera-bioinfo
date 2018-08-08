package coursera.bio;

import static coursera.bio.StringFromDeBruijn.fromKmers;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StringFromDeBruijnTest {

	@Test
	public void reconstruct() {
		List<String> kmers = Arrays.asList("CTTA", "ACCA", "TACC", "GGCT",
				"GCTT", "TTAC");
		assertEquals("GGCTTACCA", fromKmers.apply(kmers, 4));
	}

	@Test
	public void reconstructExtraData() throws Exception {
		List<String> lines = FileUtil
				.loadFile("StringReconstructionProblem.txt");
		List<String> kmers = lines.subList(2, lines.size() - 2);
		Integer k = Integer.valueOf(lines.get(1));
		assertEquals(lines.get(lines.size() - 1), fromKmers.apply(kmers, k));
	}

	@Test
	public void reconstructDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_203_6.txt");
		List<String> kmers = lines.subList(1, lines.size());
		Integer k = Integer.valueOf(lines.get(0));
		System.out.println(fromKmers.apply(kmers, k));
	}

	@Test
	public void quiz() {
		List<String> kmers = Arrays.asList("AAAT", "AATG", "ACCC", "ACGC",
				"ATAC", "ATCA", "ATGC", "CAAA", "CACC", "CATA", "CATC", "CCAG",
				"CCCA", "CGCT", "CTCA", "GCAT", "GCTC", "TACG", "TCAC", "TCAT",
				"TGCA");
		assertEquals("CAAATGCATACGCTCATCACCCAG", fromKmers.apply(kmers, 4));
	}
}