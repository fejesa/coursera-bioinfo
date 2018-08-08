package coursera.bio.iii;

import static coursera.bio.iii.DPChange.change;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import coursera.bio.FileUtil;

public class DPChangeTest {

	@Test
	public void change() {
		assertEquals(Integer.valueOf(2),
				change.apply(40, Arrays.asList(50, 25, 20, 10, 5, 1)));
	}
	
	@Test
	public void changeExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("change_problem.txt");
		List<Integer> coins = Arrays
				.stream(lines.get(2).split(","))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		assertEquals(Integer.valueOf(lines.get(4)), change.apply(Integer.valueOf(lines.get(1)), coins));
	}

	@Test
	public void changeDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_243_9.txt");
		List<Integer> coins = Arrays
				.stream(lines.get(1).split(","))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		assertEquals(Integer.valueOf(801), change.apply(Integer.valueOf(lines.get(0)), coins));
	}
}
