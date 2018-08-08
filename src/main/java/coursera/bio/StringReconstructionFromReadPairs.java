package coursera.bio;

import static coursera.bio.DeBruijn.glue;
import static coursera.bio.Utils.prefix;
import static coursera.bio.Utils.suffix;
import static coursera.bio.Eulerian.path;
import static coursera.bio.StringFromGenomPath.reconstruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import coursera.bio.graph.Vertex;

public class StringReconstructionFromReadPairs {

	private static BiFunction<String, Function<String, String>, String> label = (
			p, func) -> {
		String[] pairs = p.split("\\|");
		StringBuilder builder = new StringBuilder();
		builder
			.append(func.apply(pairs[0]))
			.append("|")
			.append(func.apply(pairs[1]));
		return builder.toString();
	};

	private static Function<List<String>, List<Vertex<String>>> toVertices = pairs -> {

		Map<String, Vertex<String>> vertexMap = new HashMap<>();

		Consumer<String> putIfAbsent = s -> {
			Vertex<String> v = vertexMap.get(s);
			if (v == null) {
				v = new Vertex<String>(vertexMap.size(), s);
				vertexMap.put(s, v);
			}
		};

		pairs.forEach(kmer -> {
			putIfAbsent.accept(label.apply(kmer, prefix));
			putIfAbsent.accept(label.apply(kmer, suffix));
		});

		pairs.forEach(kmer -> {
			String p = label.apply(kmer, prefix);
			String s = label.apply(kmer, suffix);
			Vertex<String> v = vertexMap.get(p);
			Vertex<String> w = vertexMap.get(s);
			v.addAdjacent(w);
		});

		return new ArrayList<Vertex<String>>(vertexMap.values());
	};

	public static TriFunction<Integer, Integer, List<String>, String> assemble = (
			k, d, pairs) -> {
		// Build de Bruijn graph from pairs
		List<Vertex<String>> vertices = toVertices.apply(pairs);
		List<Vertex<String>> graph = new ArrayList<Vertex<String>>();
		vertices.forEach(v -> {
			Optional<Vertex<String>> w = glue.apply(v, vertices, graph);
			w.map(graph::add);
		});

		Map<String, Vertex<String>> vertexMap = graph.stream().collect(
				Collectors.toMap(v -> v.label, v -> v));
		// Find Eulerian path
		List<Vertex<String>> list = path.apply(vertexMap);

		// Reconstruct text from prefix and suffix string
		String prefixString = reconstruct.apply(list.stream()
				.map(v -> v.label.substring(0, k - 1))
				.collect(Collectors.toList()));
		String suffixString = reconstruct.apply(list.stream()
				.map(v -> v.label.substring(k + 1))
				.collect(Collectors.toList()));

		return prefixString
				+ suffixString.substring(suffixString.length() - (k + d));
	};
}