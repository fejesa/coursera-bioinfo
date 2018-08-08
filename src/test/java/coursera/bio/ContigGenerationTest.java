package coursera.bio;

import static coursera.bio.ContigGeneration.contigs;
import static coursera.bio.ContigGeneration.maximalNonBranchingPaths;
import static coursera.bio.ContigGeneration.printPathFromVertices;
import static coursera.bio.Eulerian.toVertices;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import coursera.bio.graph.Vertex;

public class ContigGenerationTest {

	@Test
	public void contigs() {
		List<String> kmers = Arrays.asList(
				"ATG", "ATG", "TGT", "TGG", "CAT", "GGA", "GAT", "AGA");
		List<String> expected = Arrays.asList(
				"AGA", "ATG", "ATG", "CAT", "GAT", "TGGA", "TGT");
		List<List<Vertex<String>>> list = contigs.apply(kmers);
		List<String> paths = list
				.stream()
				.map(path -> printPathFromVertices.apply(path))
				.collect(Collectors.toList());
		paths.sort(Comparator.naturalOrder());

		assertEquals(expected, paths);
	}

	@Test
	public void contigsExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("contig_generation_3.txt");
		List<String> kmers = new ArrayList<>();
		int i = 1;
		for (; ; ++i) {
			String line = lines.get(i);
			if (line.equalsIgnoreCase("Output:")) {
				break;
			}
			kmers.add(line);
		}

		List<String> expectedList = lines.subList(i + 1, lines.size());
		expectedList.sort(Comparator.naturalOrder());

		List<List<Vertex<String>>> list = contigs.apply(kmers);
		List<String> paths = list.stream().map(path -> printPathFromVertices.apply(path)).collect(Collectors.toList());
		paths.sort(Comparator.naturalOrder());

		assertEquals(expectedList, paths);
	}

	@Test
	public void contigsDataset() throws Exception {
		List<String> kmers = FileUtil.loadFile("dataset_205_5.txt");

		List<List<Vertex<String>>> list = contigs.apply(kmers);
		List<String> paths = list.stream().map(path -> printPathFromVertices.apply(path)).collect(Collectors.toList());
		paths.sort(Comparator.naturalOrder());

		String result = paths
				.stream()
				.collect(Collectors.joining("\n"));
		System.out.println(result);
	}

	@Test
	public void maximalNonBranchingPaths() {
		List<String> edges = Arrays.asList(
				"1 -> 2",
				"2 -> 3",
				"3 -> 4,5",
				"6 -> 7",
				"7 -> 6");
		String expected = Arrays.asList(
				"1 -> 2 -> 3",
				"3 -> 4",
				"3 -> 5",
				"6 -> 7 -> 6").stream()
				.collect(Collectors.joining("\n"));

		List<Vertex<String>> vertices = new ArrayList<>(toVertices.apply(edges).values());
		List<List<Vertex<String>>> paths = maximalNonBranchingPaths.apply(vertices);

		List<String> list = paths
				.stream()
				.map(p -> ContigGeneration.printPath.apply(p))
				.collect(Collectors.toList());
		list.sort(Comparator.naturalOrder());
		
		String result = list
				.stream()
				.collect(Collectors.joining("\n"));
		
		assertEquals(expected, result);
		
	}

	@Test
	public void maximalNonBranchingPathsExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("MaximalNonBranchingPaths.txt");
		List<String> edges = new ArrayList<>();
		int i = 1;
		for (; ; ++i) {
			String line = lines.get(i);
			if (line.equalsIgnoreCase("output")) {
				break;
			}
			edges.add(line);
		}
		
		List<Vertex<String>> vertices = new ArrayList<>(toVertices.apply(edges).values());
		List<List<Vertex<String>>> paths = maximalNonBranchingPaths.apply(vertices);

		List<String> list = paths
				.stream()
				.map(p -> ContigGeneration.printPath.apply(p))
				.collect(Collectors.toList());
		list.sort(Comparator.naturalOrder());

		List<String> expectedList = lines.subList(i + 1, lines.size());
		expectedList.sort(Comparator.naturalOrder());
		list.forEach(r -> {
			if (!expectedList.contains(r)) System.out.println(r);
		});

		String expected = expectedList.stream().collect(Collectors.joining("\n"));
		System.out.println(expected);

	}

	@Test
	public void maximalNonBranchingPathsDataset() throws Exception {
		List<String> edges = FileUtil.loadFile("dataset_6207_2.txt");
		List<Vertex<String>> vertices = new ArrayList<>(toVertices.apply(edges).values());
		List<List<Vertex<String>>> paths = maximalNonBranchingPaths.apply(vertices);

		List<String> list = paths
				.stream()
				.map(p -> ContigGeneration.printPath.apply(p))
				.collect(Collectors.toList());
		list.sort(Comparator.naturalOrder());
		
		String result = list
				.stream()
				.collect(Collectors.joining("\n"));

		System.out.println(result);
	}
}