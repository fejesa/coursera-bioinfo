package coursera.bio.iii;

import static coursera.bio.iii.ColoredEdges.format;
import static coursera.bio.iii.ColoredEdges.toEdges;
import static coursera.bio.iii.GraphToGenome.toGenome;
import static coursera.bio.iii.TwoBreakOnGenomeGraph.twoBreakOnGraph;

public class TwoBreakOnGenome {

	public static String twoBreak(String genome, int i, int j, int k, int l) {
		//System.err.println(genome);
		String g = toEdges.andThen(format).apply(genome);
		//System.err.println(g);
		g = format.apply(twoBreakOnGraph(g, i, j, k, l));
		return toGenome.apply(g);
	}
}