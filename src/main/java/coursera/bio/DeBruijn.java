package coursera.bio;

import static coursera.bio.Utils.prefix;
import static coursera.bio.Utils.suffix;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import coursera.bio.graph.Vertex;

public class DeBruijn {

	public static TriFunction<Vertex<String>, List<Vertex<String>>, List<Vertex<String>>, Optional<Vertex<String>>> glue =
			(vertex, oldGraph, graph) -> {
		String label = vertex.label;
		boolean contains = graph
				.stream()
				.anyMatch(v -> v.label.equals(label));
		if (contains) { // Already added
			return Optional.empty();
		}

		List<Vertex<String>> nodes = oldGraph
				.stream()
				.filter(v -> v.label.equals(label))
				.collect(Collectors.toList());

		if (nodes.size() == 1) { // No other vertex
			return Optional.of(vertex);
		}

		Vertex<String> w = new Vertex<String>(graph.size(), label);
		nodes.forEach(v -> w.adjacents().addAll(v.adjacents()));

		List<Vertex<String>> sources = new ArrayList<>();
		oldGraph.forEach(v -> {
			nodes.forEach(n -> {
				if (v.adjacents().contains(n.id)) {
					sources.add(v);
				}
			});
		});
		w.adjacents().addAll(sources);

		return Optional.of(w);
	};

	public static BiFunction<String, Integer, List<Vertex<String>>> pathGraph = (text, k) -> {
		List<String> kmers = Utils.kmers(text, k);

		List<Vertex<String>> vertices = new ArrayList<Vertex<String>>();
		Vertex<String> start = new Vertex<String>(vertices.size(), prefix.apply(kmers.get(0)));
		vertices.add(start);

		Vertex<String> prev = start;
		for (int i = 0; i < kmers.size(); ++i) {
			String kmer = kmers.get(i);
			Vertex<String> vertex = new Vertex<String>(vertices.size(), suffix.apply(kmer));
			prev.addAdjacent(vertex);
			vertices.add(vertex);
			
			prev = vertex;
		}

		List<Vertex<String>> graph = new ArrayList<Vertex<String>>();
		vertices.forEach(v -> {
			Optional<Vertex<String>> w = glue.apply(v, vertices, graph);
			w.map(graph::add);
		});

		return graph;
	};
}