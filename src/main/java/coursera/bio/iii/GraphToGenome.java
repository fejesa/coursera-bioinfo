package coursera.bio.iii;

import static coursera.bio.iii.CycleToChromosome.decode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GraphToGenome {

	public static Function<String, String> toGenome = graph -> {
		Function<String, Tuple<Integer, Integer>> toTuple = s -> {
			String[] nums = s.substring(1, s.length() - 1).split(",");
			return new Tuple<Integer, Integer>(
					Integer.parseInt(nums[0].trim()), Integer.parseInt(nums[1]
							.trim()));
		};

		String[] edges = graph.split("(?<=\\)),");
		List<Integer> cycle = null;
		int begin = 0, end = 0;
		StringBuilder genome = new StringBuilder();
		for (int i = 0; i < edges.length; ++i) {
			Tuple<Integer, Integer> t = toTuple.apply(edges[i].trim());

			if (begin == 0) {
				begin = t.a;
				end = begin % 2 == 0 ? begin - 1 : begin + 1;
				cycle = new ArrayList<>();
			}

			cycle.add(t.a);
			cycle.add(t.b);

			if (t.b == end) {
				begin = 0;
				cycle.add(0, end);
				cycle.remove(cycle.size() - 1);
				String c = cycle.stream().map(n -> Integer.toString(n)).collect(Collectors.joining(" ", "(", ")"));
				genome.append(decode.apply(c));
			}
		}

		return genome.toString();
	};
}