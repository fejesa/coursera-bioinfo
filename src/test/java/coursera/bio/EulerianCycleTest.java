package coursera.bio;

import static coursera.bio.EulerianCycle.findCycle;
import static coursera.bio.Eulerian.printPath;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class EulerianCycleTest {

	@Test
	public void cycle() {
		List<String> input = Arrays.asList(
				"0 -> 3",
				"1 -> 0",
				"2 -> 1,6",
				"3 -> 2",
				"4 -> 2",
				"5 -> 4",
				"6 -> 5,8",
				"7 -> 9",
				"8 -> 7",
				"9 -> 6");
		// There are more eligible cycle
		System.out.println(findCycle.andThen(printPath).apply(input));
	}

	@Test
	public void cycleExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("eulerian_cycle.txt");
		List<String> input = lines.subList(1, lines.size() - 2);
		// There are more eligible cycle
		System.out.println(findCycle.andThen(printPath).apply(input));
	}

	@Test
	public void cycleDataset() throws Exception {
		List<String> input = FileUtil.loadFile("dataset_203_2.txt");
		// There are more eligible cycle
		System.out.println(findCycle.andThen(printPath).apply(input));
	}
}