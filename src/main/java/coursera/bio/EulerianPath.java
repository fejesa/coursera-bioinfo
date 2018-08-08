package coursera.bio;

import static coursera.bio.Eulerian.path;
import static coursera.bio.Eulerian.toVertices;

import java.util.List;
import java.util.function.Function;

import coursera.bio.graph.Vertex;

public class EulerianPath {

	public static Function<List<String>, List<Vertex<String>>> findPath = edges -> {
		return toVertices.andThen(path).apply(edges);
	};
}