package coursera.bio.iii;

import static coursera.bio.Utils.toNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColoredEdges {

	public static Function<String, List<Tuple<Integer, Integer>>> toEdges = genome -> {
		String[] chromosomes = genome.split("(?<=\\))");
		List<String> list = Arrays.stream(chromosomes).collect(
				Collectors.toList());
		List<Tuple<Integer, Integer>> edges = new ArrayList<>();
		list.forEach(c -> {
			String nodes = ChromosomeToCycle.encode.apply(c);
			List<Integer> nums = toNumbers.apply(nodes);
			for (int j = 1; j < nums.size() / 2; ++j) {
				int node2j = nums.get(2 * j);
				int node2j_1 = nums.get(2 * j - 1);
				edges.add(new Tuple<Integer, Integer>(node2j_1, node2j));
			}
			// Glue end to start
			int node2j_1 = nums.get(nums.size() - 1);
			int node2j = nums.get(0);
			edges.add(new Tuple<Integer, Integer>(node2j_1, node2j));
		});

		return edges;
	};

	public static Function<List<Tuple<Integer, Integer>>, String> format = edges -> edges
			.stream().map(e -> String.format("(%d, %d)", e.a, e.b))
			.collect(Collectors.joining(", ", "", ""));
}
