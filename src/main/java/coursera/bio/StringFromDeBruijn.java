package coursera.bio;

import static coursera.bio.Eulerian.cycle;
import static coursera.bio.Eulerian.degreeMap;
import static coursera.bio.Eulerian.isEulerian;
import static coursera.bio.StringFromGenomPath.reconstruct;
import static coursera.bio.Utils.prefix;
import static coursera.bio.Utils.suffix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import coursera.bio.graph.Vertex;

public class StringFromDeBruijn {
	
	public static BiFunction<List<String>, Integer, String> fromKmers = (kmers, k) -> {

		Map<String, Vertex<String>> vertexMap = new HashMap<>();

		Consumer<String> putIfAbsent = s -> {
			Vertex<String> v = vertexMap.get(s);
			if (v == null) {
				v = new Vertex<String>(vertexMap.size(), s);
				vertexMap.put(s, v);
			}
		};

		kmers.forEach(kmer -> {
			putIfAbsent.accept(prefix.apply(kmer));
			putIfAbsent.accept(suffix.apply(kmer));
		});

		kmers.forEach(kmer -> {
			String p = prefix.apply(kmer);
			String s = suffix.apply(kmer);
			Vertex<String> v = vertexMap.get(p);
			Vertex<String> w = vertexMap.get(s);
			v.addAdjacent(w);
		});

		List<Vertex<String>> vertices = new ArrayList<>(vertexMap.values());
		Map<String, Long> map = degreeMap.apply(vertices);

		if (!isEulerian(map.values())) {
			return null;
		}

		String start = map.entrySet().stream().filter(e -> e.getValue() == -1).findAny().get().getKey();
		String end = map.entrySet().stream().filter(e -> e.getValue() == 1).findAny().get().getKey();

		Stack<Vertex<String>> eulerian = cycle.apply(vertexMap, start);

		List<Vertex<String>> list = new ArrayList<>();
		while(!eulerian.isEmpty()) {
			list.add(eulerian.pop());
		}
		list.remove(list.size() - 1);
		list.add(vertexMap.get(end));

		return reconstruct.apply(list.stream().map(v -> v.label).collect(Collectors.toList()));
	};
}
