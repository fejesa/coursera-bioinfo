package coursera.bio.iii;

import static coursera.bio.iii.TopologicalOrdering.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import coursera.bio.graph.Vertex;

public class LongestPathInDAG {

	private static Function<List<String>, Map<String, Integer>> transform = edges -> edges.stream()
			.map(e -> e.replace("->", " -> "))
			.collect(Collectors.toMap(
					e -> e.substring(0, e.indexOf(':')),
					e -> Integer.parseInt(e.substring(e.indexOf(':') + 1))));
	
	public static Tuple<Integer, List<String>> lcs(int source, int sink, List<String> weightedEdges) {
		Map<String, Integer> map = transform.apply(weightedEdges);

		List<Vertex<String>> vertexes = order.apply(new ArrayList<>(map.keySet()));

		// Initialize distances to all vertices as infinite and distance
	    // to source as 0
		int dist[] = new int[vertexes.size() + 1];
		Arrays.fill(dist, Integer.MIN_VALUE);
		
		Vertex<String> sv = vertexes.stream().filter(v -> v.label.equals(String.valueOf(source))).findAny().get();
		Vertex<String> sw = vertexes.stream().filter(w -> w.label.equals(String.valueOf(sink))).findAny().get();
		dist[sv.id] = 0;

		@SuppressWarnings("unchecked")
		Vertex<String>[] edgeTo = new Vertex[vertexes.size()];
		edgeTo[sv.id] = sv;

		for (Vertex<String> v : vertexes) {
			for (Vertex<String> w : v.adjacents()) {
				if (dist[w.id] < dist[v.id] + getWeight(v, w, map)) {
					dist[w.id] = dist[v.id] + getWeight(v, w, map);
					edgeTo[w.id] = w;
				}
			}
		}
		
		List<String> path = new ArrayList<>();

		for (int i = sv.id; i < edgeTo.length; ++i) {
			if (edgeTo[i] != null) {
				path.add(edgeTo[i].label);
			}
		}
		path.add(sw.label);

		return new Tuple<Integer, List<String>>(dist[sw.id], path);
	}

	private static int getWeight(Vertex<String> v, Vertex<String> w, Map<String, Integer> map) {
		String edge = v.label + " -> " + w.label;
		return map.get(edge);
	}
}