package coursera.bio.iii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TwoBreakOnGenomeGraph {

	public static List<Tuple<Integer, Integer>> twoBreakOnGraph(String graph, int i,
			int j, int k, int l) {
		Function<String, Tuple<Integer, Integer>> toTuple = s -> {
			String[] nums = s.substring(1, s.length() - 1).split(",");
			return new Tuple<Integer, Integer>(
					Integer.parseInt(nums[0].trim()), Integer.parseInt(nums[1]
							.trim()));
		};

		Map<Tuple<Integer, Integer>, Tuple<Integer, Integer>> map = new HashMap<>();
		map.put(new Tuple<Integer, Integer>(i, j),  new Tuple<Integer, Integer>(l, j));
		map.put(new Tuple<Integer, Integer>(j, i),  new Tuple<Integer, Integer>(j, l));
		map.put(new Tuple<Integer, Integer>(k, l),  new Tuple<Integer, Integer>(k, i));
		map.put(new Tuple<Integer, Integer>(l, k),  new Tuple<Integer, Integer>(i, k));

		List<Tuple<Integer, Integer>> list = new ArrayList<>();

		String[] edges = graph.split("(?<=\\)),");
		for (int a = 0; a < edges.length; ++a) {
			Tuple<Integer, Integer> tuple = toTuple.apply(edges[a].trim());
			Tuple<Integer, Integer> t = map.getOrDefault(tuple, tuple);
			list.add(t);	
		}
		
		return list;
	}
}