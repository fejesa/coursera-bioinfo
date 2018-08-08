package coursera.bio;

import static coursera.bio.Eulerian.printPath;
import static coursera.bio.EulerianPath.findPath;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class EulerianPathTest {

	@Test
	public void path() {
		List<String> input = Arrays.asList(
				"0 -> 2",
				"1 -> 3",
				"2 -> 1",
				"3 -> 0,4",
				"6 -> 3,7",
				"7 -> 8",
				"8 -> 9",
				"9 -> 6");
		assertEquals("6->7->8->9->6->3->0->2->1->3->4", findPath.andThen(printPath).apply(input));
	}

	@Test
	public void pathExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("eulerian_path.txt");
		List<String> input = lines.subList(1, lines.size() - 2);
		System.out.println(findPath.andThen(printPath).apply(input));
	}

	@Test
	public void pathDataset() throws Exception {
		List<String> input = FileUtil.loadFile("dataset_203_5.txt");
		System.out.println(findPath.andThen(printPath).apply(input));
	}
}