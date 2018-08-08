package coursera.bio.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Vertex<T> {
	
	public final T label;

	public final int id;
	
	private final List<Vertex<T>> adjacent;

	public Vertex(int id, T node) {
		this.id = id;
		this.label = node;
		this.adjacent = new ArrayList<Vertex<T>>();
	}

	public void addAdjacent(Vertex<T> adj) {
		adjacent.add(adj);
	}

	public List<Vertex<T>> adjacents() {
		return this.adjacent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Vertex other = (Vertex) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(label).append("(").append(id).append(")");
		if (!adjacent.isEmpty()) {
			builder.append("->");
			builder.append(adjacent
					.stream()
					.map(a -> (String) a.label)
					.collect(Collectors.joining(",")));
		}
		return builder.toString();
	}
}