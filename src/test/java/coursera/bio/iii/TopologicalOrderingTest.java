package coursera.bio.iii;

import static coursera.bio.iii.TopologicalOrdering.order;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import coursera.bio.FileUtil;
import coursera.bio.graph.Vertex;

public class TopologicalOrderingTest {

	private Function<List<Vertex<String>>, String> printPath = path -> {
		return path.stream().map(v -> v.label).collect(Collectors.joining(", "));
	};
	
	@Test
	public void order() {
		List<String> input = Arrays.asList(
				"0 -> 1",
				"1 -> 2",
				"3 -> 1",
				"4 -> 2");
		assertEquals("0, 3, 4, 1, 2", order.andThen(printPath).apply(input));
	}

	@Test
	public void orderX() {
		List<String> input = Arrays.asList(
				"0 -> 1",
				"0 -> 3",
				"0 -> 4",
				"1 -> 2",
				"3 -> 2",
				"4 -> 1",
				"4 -> 5",
				"5 -> 6",
				"1 -> 5",
				"2 -> 6");
		System.out.println(order.andThen(printPath).apply(input));
		
		input = Arrays.asList(
				"5 -> 2",
				"5 -> 0",
				"4 -> 0",
				"4 -> 1",
				"2 -> 3",
				"3 -> 1");
		System.out.println(order.andThen(printPath).apply(input));
	}
	
	@Test
	public void orderExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("topological_ordering.txt");
		List<String> input = lines.subList(1, lines.size() - 2);
		assertEquals("0, 1, 2, 6, 3, 7, 4, 10, 18, 5, 13, 11, 16, 17, 8, 9, 19, 21, 12, 14, 15, 20, 24, 22, 23, 25", order.andThen(printPath).apply(input));
	}

	@Test
	public void orderDataset() throws Exception {
		List<String> input = FileUtil.loadFile("dataset_254_2.txt");
		assertEquals("0, 1, 10, 15, 16, 17, 23, 25, 3, 4, 5, 6, 7, 9, 2, 20, 29, 12, 34, 8, 13, 27, 39, 24, 11, 19, 31, 18, 42, 28, 30, 33, 38, 14, 40, 37, 21, 22, 36, 26, 32, 35", order.andThen(printPath).apply(input));
	}

	@Test
	public void orderQuiz() {
		List<String> input = Arrays.asList(
				"0 -> 1",
				"0 -> 2",
				"0 -> 3",
				"0 -> 4",
				"0 -> 5",
				"1 -> 2",
				"1 -> 5",
				"2 -> 3",
				"4 -> 3",
				"4 -> 5");
		System.out.println(order.andThen(printPath).apply(input));
	}
}