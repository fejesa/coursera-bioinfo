package coursera.bio;

import static coursera.bio.Eulerian.cycle;
import static coursera.bio.Eulerian.degreeMap;
import static coursera.bio.Eulerian.isEulerian;
import static coursera.bio.Eulerian.toVertices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

import coursera.bio.graph.Vertex;

public class EulerianCycle {

	public static Function<List<String>, List<Vertex<String>>> findCycle = edges -> {
		Map<String, Vertex<String>> vertexMap = toVertices.apply(edges);

		List<Vertex<String>> vertices = new ArrayList<>(vertexMap.values());

		if (!isEulerian(degreeMap.apply(vertices).values())) {
			return Collections.emptyList();
		}

		Stack<Vertex<String>> eulerian = cycle.apply(vertexMap, vertices.get(0).label);

		List<Vertex<String>> list = new ArrayList<>();
		while(!eulerian.isEmpty()) {
			list.add(eulerian.pop());
		}
		return list;
	};
}
