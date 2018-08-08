package coursera.bio;

import static coursera.bio.OverlapGraph.overlap;
import static coursera.bio.OverlapGraph.print;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class OverlapGraphTest {

	@Test
	public void overlap() {
		List<String> list = Arrays.asList("ATGCG", "GCATG", "CATGC", "AGGCA", "GGCAT");
		StringBuilder builder = new StringBuilder();
		builder.append("AGGCA -> GGCAT").append("\n");
		builder.append("CATGC -> ATGCG").append("\n");
		builder.append("GCATG -> CATGC").append("\n");
		builder.append("GGCAT -> GCATG");

		assertEquals(builder.toString(), overlap.andThen(print).apply(list));
	}

	@Test
	public void overlapExtraDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("overlap_graph_1.txt");
		List<String> list = lines.stream().filter(l -> !l.contains("->")).collect(Collectors.toList());
		String expected = lines.stream().filter(l -> l.contains("->")).collect(Collectors.joining("\n"));
		assertEquals(expected, overlap.andThen(print).apply(list));
	}

	@Test
	public void overlapDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_198_9.txt");
		System.out.println(overlap.andThen(print).apply(lines));
	}	
}