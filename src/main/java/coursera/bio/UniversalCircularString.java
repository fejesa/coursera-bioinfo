package coursera.bio;

import static coursera.bio.DeBruijnPatterns.graph;
import static coursera.bio.Eulerian.cycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import coursera.bio.graph.Vertex;

public class UniversalCircularString {

	public static Function<Integer, String> findCycle = k -> {
		Supplier<List<String>> patterns = () -> {
			char[] fill = new char[k];
			Arrays.fill(fill, '0');
			String base = new String(fill);

			return IntStream.range(0, Double.valueOf(Math.pow(2, k)).intValue())
					.mapToObj(i -> {
						String s = Integer.toBinaryString(i);
						return (base + s).substring(s.length());
					})
					.collect(Collectors.toList());
		};

		List<Vertex<String>> vertices = graph.apply(patterns.get());
		Map<String, Vertex<String>> vertexMap = vertices
				.stream()
				.collect(Collectors.toMap(v -> v.label, v -> v));

		Stack<Vertex<String>> eulerian = cycle.apply(vertexMap, vertices.get(0).label);
		List<Vertex<String>> list = new ArrayList<>();
		while(!eulerian.isEmpty()) {
			list.add(eulerian.pop());
		}
		String result = list
				.stream()
				.map(v -> v.label.substring(0, 1))
				.collect(Collectors.joining(""));

		return result.substring(0, result.length() - 1);
	};
}