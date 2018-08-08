package coursera.bio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import coursera.bio.graph.Vertex;

public class Eulerian {
	
	protected static BiFunction<Map<String, Vertex<String>>, String, Stack<Vertex<String>>> cycle = (vertexMap, start) -> {
		Map<Vertex<String>, Iterator<Vertex<String>>> adjacencyMap = vertexMap
				.entrySet()
				.stream().collect(Collectors
						.toMap(e -> e.getValue(), e -> e.getValue().adjacents().iterator()));

		Stack<Vertex<String>> cycle = new Stack<>();
		Stack<Vertex<String>> stack = new Stack<>();
		stack.push(vertexMap.get(start));
		while(!stack.empty()) {
			Vertex<String> v = stack.pop();
			cycle.push(v);
			Vertex<String> w = v;
			while (adjacencyMap.get(w).hasNext()) {
				stack.push(w);
				w = adjacencyMap.get(w).next();
			}
		}

		return cycle;
	};

	public static Function<Collection<Vertex<String>>, Map<String, Long>> degreeMap = vertices -> {
		Function<Vertex<String>, Long> inDegree = v -> {
			long l = vertices
					.stream()
					.flatMap(w -> w.adjacents().stream())
					.filter(e -> e.equals(v))
					.count();
			return l;
		};
		Map<String, Long> map = vertices
				.stream()
				.collect(Collectors.toMap(v -> v.label, v -> inDegree.apply(v) - v.adjacents().size()));
		return map;
	};

	protected static Function<Map<String, Vertex<String>>, List<Vertex<String>>> path = vertexMap -> {
		List<Vertex<String>> vertices = new ArrayList<>(vertexMap.values());
		Map<String, Long> map = degreeMap.apply(vertices);

		if (!isEulerian(map.values())) {
			//return Collections.emptyList();
		}

		System.out.println("start pos>" + map.entrySet().stream().filter(e -> e.getValue() == -1).count());
		System.out.println("end pos>" + map.entrySet().stream().filter(e -> e.getValue() == 1).count());
//		List<String> starts = map.entrySet().stream()
//				.filter(e -> e.getValue() == -1).map(e -> e.getKey()).collect(Collectors.toList());
//		starts.forEach(s -> {
//			Stack<Vertex<String>> eulerian = cycle.apply(vertexMap, s);
//
//			List<Vertex<String>> list = new ArrayList<>();
//			while (!eulerian.isEmpty()) {
//				list.add(eulerian.pop());
//			}
//			System.out.println(list.size());
//		});
//		
//		return Collections.emptyList();
		
		String start = map.entrySet().stream().filter(e -> e.getValue() == -1).findAny().get().getKey();
		String end = map.entrySet().stream().filter(e -> e.getValue() == 1).findAny().get().getKey();

		Stack<Vertex<String>> eulerian = cycle.apply(vertexMap, start);

		List<Vertex<String>> list = new ArrayList<>();
		while(!eulerian.isEmpty()) {
			list.add(eulerian.pop());
		}
		list.remove(list.size() - 1);
		list.add(vertexMap.get(end));
		return list;
	};
	
	protected static boolean isEulerian(Collection<Long> degrees) {
		long mid = degrees
				.stream()
				.filter(e -> e == 0)
				.count();
		if (mid == degrees.size()) {
			//  has a closed Euler tour
			return true;
		}
		long end = degrees
				.stream()
				.filter(e -> e == 1)
				.count();
		long start = degrees
				.stream()
				.filter(e -> e == -1)
				.count();

		//  has an open Euler tour?
		return start == 1 && end == 1;
	}

	public static Function<List<String>, Map<String, Vertex<String>>> toVertices = edges -> {
		Map<String, Vertex<String>> vertexMap = new HashMap<>();
		edges.forEach(e -> {
			String[] array = e.split("\\s*[->,]+\\s*");
			String source = array[0];
			Vertex<String> v = vertexMap.get(source);
			if (v == null) {
				v = new Vertex<String>(vertexMap.size(), source);
				vertexMap.put(source, v);
			}

			for (int i = 1; i < array.length; ++i) {
				String target = array[i];
				Vertex<String> w = vertexMap.get(target);
				if (w == null) {
					w = new Vertex<String>(vertexMap.size(), target);
					vertexMap.put(target, w);
				}
				v.addAdjacent(w);
			}
		});
		return vertexMap;
	};

	public static Function<List<Vertex<String>>, String> printPath = path -> {
		return path.stream().map(v -> v.label).collect(Collectors.joining("->"));
	};
}
