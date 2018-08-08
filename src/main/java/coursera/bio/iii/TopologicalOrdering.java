package coursera.bio.iii;

import static coursera.bio.Eulerian.toVertices;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import coursera.bio.graph.Vertex;

public class TopologicalOrdering {
	
	public static Function<List<String>, List<Vertex<String>>> order = edges -> {
		Map<String, Vertex<String>> vertexMap = toVertices.apply(edges);

		Predicate<Vertex<String>> p = vertex -> vertexMap
				.values()
				.stream()
				.map(v -> v.adjacents())
				.flatMap(List::stream)
				.filter(a -> a.label.equals(vertex.label))
				.findAny().isPresent();

		List<Vertex<String>> candidates = new ArrayList<>();
		for (Map.Entry<String, Vertex<String>> entry : vertexMap.entrySet()) {
			if (!p.test(entry.getValue())) {
				candidates.add(entry.getValue());
			}
		}

		Comparator<Vertex<String>> comp = Comparator.comparing(v -> v.label);
		candidates.sort(comp);

		List<Vertex<String>> result = new ArrayList<>();

		while(!candidates.isEmpty()) {
			Vertex<String> v = candidates.remove(0);
			result.add(v);
			for (Iterator<Vertex<String>> i = v.adjacents().iterator(); i.hasNext();) {
				Vertex<String> b = i.next();
				i.remove();
				if (!p.test(b)) {
					candidates.add(b);
				}
			}
		}

		Map<String, Vertex<String>> map = toVertices.apply(edges);
		result.forEach(v -> {
			v.adjacents().addAll(map.get(v.label).adjacents());
		});

		return result;
	};
}