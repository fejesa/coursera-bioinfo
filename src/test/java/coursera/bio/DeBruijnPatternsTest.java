package coursera.bio;

import static coursera.bio.DeBruijnPatterns.graph;
import static coursera.bio.Utils.printGraph;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class DeBruijnPatternsTest {

	@Test
	public void graph() {
		List<String> input = Arrays.asList(
				"GAGG", "CAGG", "GGGG", "GGGA",
				"CAGG", "AGGG", "GGAG");

		StringBuilder builder = new StringBuilder();
		builder.append("AGG -> GGG").append("\n");
		builder.append("CAG -> AGG,AGG").append("\n");
		builder.append("GAG -> AGG").append("\n");
		builder.append("GGA -> GAG").append("\n");
		builder.append("GGG -> GGA,GGG");
		
		assertEquals(builder.toString(), graph.andThen(printGraph).apply(input));
	}

	@Test
	public void graphExtraDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("De_Bruijn_Graph_from_kmer.txt");
		List<String> input = lines
					.stream()
					.skip(1)
					.filter(l -> !l.contains("->") && !l.contains("Input") && !l.contains("Output"))
					.collect(Collectors.toList());

		String expected = lines
				.stream()
				.filter(l -> l.contains("->"))
				.collect(Collectors.joining("\n"));

		assertEquals(expected, graph.andThen(printGraph).apply(input));
	}

	@Test
	public void graphDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_200_7.txt");
		System.out.println(graph.andThen(printGraph).apply(lines));
	}

	@Test
	public void quiz() {
		List<String> input = Arrays.asList("AAAT", "AATG", "ACCC", "ACGC",
				"ATAC", "ATCA", "ATGC", "CAAA", "CACC", "CATA", "CATC", "CCAG",
				"CCCA", "CGCT", "CTCA", "GCAT", "GCTC", "TACG", "TCAC", "TCAT",
				"TGCA");
		System.out.println(graph.andThen(printGraph).apply(input));
	}
}
