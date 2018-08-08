package coursera.bio;

import static coursera.bio.DeBruijn.pathGraph;
import static coursera.bio.Utils.printGraph;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class DeBruijnTest {

	@Test
	public void pathGraph() {
		StringBuilder builder = new StringBuilder();
		builder.append("AAG -> AGA,AGA").append("\n");
		builder.append("AGA -> GAT").append("\n");
		builder.append("ATT -> TTC").append("\n");
		builder.append("CTA -> TAA").append("\n");
		builder.append("CTC -> TCT").append("\n");
		builder.append("GAT -> ATT").append("\n");
		builder.append("TAA -> AAG").append("\n");
		builder.append("TCT -> CTA,CTC").append("\n");
		builder.append("TTC -> TCT");
		
		assertEquals(builder.toString(), pathGraph
				.andThen(printGraph)
				.apply("AAGATTCTCTAAGA", 4));
	}

	@Test
	public void pathGraphExtraDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("De_Bruijn_Graph_from_a_String.txt");
		Integer k = Integer.valueOf(lines.get(1));
		String text = lines.get(2);
		String expected = lines.stream().filter(l -> l.contains("->")).collect(Collectors.joining("\n"));

		assertEquals(expected, pathGraph
				.andThen(printGraph)
				.apply(text, k));
	}

	@Test
	public void pathGraphDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_199_6.txt");
		Integer k = Integer.valueOf(lines.get(0));
		String text = lines.get(1);
		
		System.out.println(pathGraph
				.andThen(printGraph)
				.apply(text, k));
	}

	@Test
	public void stopAndThink() {
		String text = "TAATGCCATGGGATGTT";
		System.out.println(pathGraph
				.andThen(printGraph)
				.apply(text, 2));
		System.out.println();
		System.out.println(pathGraph
				.andThen(printGraph)
				.apply(text, 3));
		System.out.println();
		System.out.println(pathGraph
				.andThen(printGraph)
				.apply(text, 4));
		
		System.out.println();
		System.out.println(pathGraph
				.andThen(printGraph)
				.apply("TAATGGGATGCCATGTT", 3));
	}
}