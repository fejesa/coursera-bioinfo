package coursera.bio.iii;

import static coursera.bio.ReverseComplement.reverseComplement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import coursera.bio.Utils;

public class SharedKmers {

	public static List<Tuple<Integer, Integer>> shared(int k, String p, String q) {

		Function<List<String>, List<Tuple<String, Integer>>> kmerTuple = kmers -> {
			List<Tuple<String, Integer>> kmersTuples = new ArrayList<>();
			for (int i = 0; i < kmers.size(); ++i) {
				kmersTuples.add(new Tuple<String, Integer>(kmers.get(i), i));
			}
			return kmersTuples;
		};

		List<String> pkmers = Utils.kmers(p, k);
		List<String> qkmers = Utils.kmers(q, k);

		List<Tuple<String, Integer>> pkmersTuples = kmerTuple.apply(pkmers);
		List<Tuple<String, Integer>> qkmersTuples = kmerTuple.apply(qkmers);
		List<Tuple<String, Integer>> qReversekmersTuples = kmerTuple
				.apply(qkmers.stream().map(reverseComplement)
						.collect(Collectors.toList()));

		Map<String, Set<Integer>> dictionary = new HashMap<>();

		Consumer<Tuple<String, Integer>> putin = t -> {
			Set<Integer> coords = dictionary.get(t.a);
			if (coords == null) {
				coords = new HashSet<>();
				dictionary.put(t.a, coords);
			}
			coords.add(t.b);
		};
		
		qkmersTuples.forEach(putin);
		qReversekmersTuples.forEach(putin);

		Function<Tuple<String, Integer>, List<Tuple<Integer, Integer>>> coordinates = pt -> {
			Set<Integer> list = dictionary.getOrDefault(pt.a,
					Collections.emptySet());
			return list.stream().map(c -> new Tuple<Integer, Integer>(pt.b, c))
					.collect(Collectors.toList());
		};

		List<Tuple<Integer, Integer>> list = new ArrayList<>();
		pkmersTuples.forEach(pt -> {
			list.addAll(coordinates.apply(pt));
		});
		return list;
	}
}
