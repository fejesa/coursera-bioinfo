package coursera.bio.iii;

import static coursera.bio.iii.ColoredEdges.toEdges;

import java.util.ArrayList;
import java.util.List;

public class TwoBreakDistance {

	private static List<List<Integer>> coloredEdgesCycles(
			List<Tuple<Integer, Integer>> blue,
			List<Tuple<Integer, Integer>> red) {

		List<List<Integer>> cycles = new ArrayList<>();
		int size = blue.size() + red.size();

		int[][] adj = new int[size][2];
		boolean[] visited = new boolean[size];

		for (int i = 0; i < blue.size(); ++i) {
			int a = blue.get(i).a;
			int b = blue.get(i).b;
			adj[a - 1][0] = b - 1;
			adj[b - 1][0] = a - 1;
		}

		for (int i = 0; i < red.size(); ++i) {
			int a = red.get(i).a;
			int b = red.get(i).b;
			adj[a - 1][1] = b - 1;
			adj[b - 1][1] = a - 1;
		}

		for (int node = 0; node < size; ++node) {
			if (!visited[node]) {
				visited[node] = true;
				int head = node;
				List<Integer> cycle = new ArrayList<>();
				cycle.add(head + 1);
				// arbitrary we start with a blue edge
				int color = 0;
				while (true) {
					node = adj[node][color];
					if (node == head) {
						// cycle found, we got back to the visited head node,
						// just break
						cycles.add(cycle);
						break;
					}
					cycle.add(node + 1);
					visited[node] = true;
					color = (color + 1) % 2;
				}
			}
		}
		return cycles;
	}

	public static int distance(String p, String q) {
		List<Tuple<Integer, Integer>> blue = toEdges.apply(p);
		List<Tuple<Integer, Integer>> red = toEdges.apply(q);

		List<List<Integer>> coloredEdgesCycles = coloredEdgesCycles(blue, red);

		int size = blue.size() + red.size();

		return size / 2 - coloredEdgesCycles.size();
	}
}