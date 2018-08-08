package coursera.bio.iii;

import static coursera.bio.iii.GreedySorting.sort;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class GreedySortingTest {

	@Test
	public void sort() {
		List<String> result = sort.apply("(-3 +4 +1 +5 -2)");
		List<String> expected = Arrays.asList(
				"(-1 -4 +3 +5 -2)",
				"(+1 -4 +3 +5 -2)",
				"(+1 +2 -5 -3 +4)",
				"(+1 +2 +3 +5 +4)",
				"(+1 +2 +3 -4 -5)",
				"(+1 +2 +3 +4 -5)",
				"(+1 +2 +3 +4 +5)");
		assertEquals(expected, result);
	}

	@Test
	public void sortExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("greedy_sorting.txt");
		List<String> result = sort.apply(lines.get(1));
		List<String> expected = lines.subList(3, lines.size());
		for (int i = 0; i < expected.size(); ++i) {
			if (!result.get(i).equals(expected.get(i))) {
				System.err.println(i);
				System.out.println("r -> " + result.get(i));
				System.out.println("  -> " + expected.get(i));
			}
		}
		assertEquals(expected, result);
	}

	@Test
	public void sortDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_286_3.txt");
		List<String> result = sort.apply(lines.get(0));
		result.forEach(System.out::println);
	}

	@Test
	public void sortQuiz() {
		List<String> result = sort.apply("(+6 -12 -9 +17 +18 -4 +5 -3 +11 +19 +14 +10 +8 +15 -13 +20 +2 +7 -16 -1)");
		System.out.println(result.size());
	}
}