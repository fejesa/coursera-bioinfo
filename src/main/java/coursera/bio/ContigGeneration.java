package coursera.bio;

import static coursera.bio.DeBruijnPatterns.graph;
import static coursera.bio.Eulerian.degreeMap;
import static coursera.bio.StringFromGenomPath.reconstruct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import coursera.bio.graph.Vertex;

public class ContigGeneration {

	public static Function<List<Vertex<String>>, String> printPathFromVertices = path -> {
		return reconstruct.apply(
				path
				.stream()
				.map(v -> v.label)
				.collect(Collectors.toList()));
	};

	public static Function<List<Vertex<String>>, String> printPath = path -> {
		return path.stream().map(v -> v.label)
				.collect(Collectors.joining(" -> "));
	};

	public static Function<List<Vertex<String>>, List<List<Vertex<String>>>> maximalNonBranchingPaths = vertices -> {

		Map<String, Long> degrees = degreeMap.apply(vertices);
		Predicate<Vertex<String>> oneInOneOut = v -> v.adjacents().size() == 1
				&& degrees.get(v.label) == 0;

		Set<Vertex<String>> track = new HashSet<>();

		List<Vertex<String>> list = vertices.stream()
				.filter(v -> !oneInOneOut.test(v) && !v.adjacents().isEmpty())
				.collect(Collectors.toList());

		List<List<Vertex<String>>> paths = new ArrayList<>();
		list.forEach(v -> {
			v.adjacents().forEach(w -> {
				List<Vertex<String>> nonBranchingPath = new ArrayList<>();
				nonBranchingPath.add(v);
				track.add(v);
				nonBranchingPath.add(w);
				track.add(w);
				while (oneInOneOut.test(w)) {
					Vertex<String> u = w.adjacents().iterator().next();
					nonBranchingPath.add(u);
					track.add(u);
					w = u;
				}
				paths.add(nonBranchingPath);
			});
		});

		vertices.removeAll(track);
		track.clear();

		vertices.forEach(v -> {
			if (!track.contains(v)) {
				List<Vertex<String>> nonBranchingPath = new ArrayList<>();
				nonBranchingPath.add(v);
				track.add(v);
				v.adjacents().forEach(w -> {
					nonBranchingPath.add(w);
					while (oneInOneOut.test(w) && !track.contains(w)) {
						Vertex<String> u = w.adjacents().iterator().next();
						nonBranchingPath.add(u);
						track.add(w);
						w = u;
					}
				});
				paths.add(nonBranchingPath);
			}
		});

		return paths;
	};

	public static Function<List<String>, List<List<Vertex<String>>>> contigs = kmers -> {
		return graph.andThen(maximalNonBranchingPaths).apply(kmers);
	};
}