package coursera.bio;

import static coursera.bio.Utils.prefix;
import static coursera.bio.Utils.suffix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import coursera.bio.graph.Vertex;

public class OverlapGraph {

	public static Function<List<String>, List<Vertex<String>>> overlap = kmers -> {
		kmers.sort(Comparator.naturalOrder());

		List<Vertex<String>> vertices = new ArrayList<>();
		for (int i = 0; i < kmers.size(); ++i) {
			vertices.add(new Vertex<String>(i, kmers.get(i)));
		}

		vertices.forEach(v -> {
			vertices.forEach(w -> {
				if (!w.equals(v) && suffix.apply(v.label).equals(prefix.apply(w.label))) {
					v.addAdjacent(w);
				}
			});
		});

		return vertices;
	};

	public static Function<List<Vertex<String>>, String> print = vertices -> {
		StringBuilder builder = new StringBuilder();
		vertices
			.stream()
			.filter(v -> !v.adjacents().isEmpty())
			.forEach(v -> {
				builder.append(v.label);
				builder.append(" -> ");
				builder.append(v.adjacents().get(0).label);
				builder.append("\n");
		});
		
		return builder.substring(0, builder.length() - 1);
	};
}