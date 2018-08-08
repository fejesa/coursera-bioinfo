package coursera.bio;

import static coursera.bio.Utils.prefix;
import static coursera.bio.Utils.suffix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import coursera.bio.graph.Vertex;

public class DeBruijnPatterns {

	public static Function<List<String>, List<Vertex<String>>> graph = kmers -> {

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

		return new ArrayList<Vertex<String>>(vertexMap.values());
	};
}