package coursera.bio.iii;

import static coursera.bio.iii.LongestPathInDAG.lcs;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import coursera.bio.FileUtil;

public class LongestPathInDAGTest {

	@Test
	public void lcsSimple() {
		List<String> input = Arrays.asList(
				"0->1:7",
				"0->2:4",
				"2->3:2",
				"1->4:1",
				"3->4:3");

		Tuple<Integer, List<String>> result = lcs(0, 4, input);

		assertEquals(Integer.valueOf(9), result.a);
		assertEquals("0->2->3->4", toPath(result.b));
	}

	@Test
	public void lcsPlus() {
		List<String> input = Arrays.asList(
				"0->1:5",
				"0->2:3",
				"1->3:6",
				"1->2:2",
				"2->4:4",
				"2->5:2",
				"2->3:7",
				"3->5:1",
				"3->4:-1",
				"4->5:-2");
		Tuple<Integer, List<String>> result = lcs(1, 5, input);
		assertEquals(Integer.valueOf(10), result.a);
		System.out.println(toPath(result.b));
	}

	@Test
	public void lcsExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("07_longest_path_in_DAG.txt");
		Integer source = Integer.parseInt(lines.get(1));
		Integer shink = Integer.parseInt(lines.get(2));
		Tuple<Integer, List<String>> result = lcs(source, shink, lines.subList(3, lines.size() - 4));
		assertEquals(Integer.valueOf(62), result.a);
		System.out.println(toPath(result.b));
	}

	@Test
	public void lcsDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_245_7.txt");
		Integer source = Integer.parseInt(lines.get(0));
		Integer shink = Integer.parseInt(lines.get(1));
		Tuple<Integer, List<String>> result = lcs(source, shink, lines.subList(2, lines.size()));
		assertEquals(Integer.valueOf(0), result.a);
		assertEquals("13->18->24", toPath(result.b));
	}

	@Test
	public void lcsQuiz() {
		List<String> input = Arrays.asList(
				"0->1:3",
				"0->2:6",
				"0->3:5",
				"1->2:2",
				"1->5:4",
				"2->4:4",
				"2->5:3",
				"2->6:7",
				"3->4:4",
				"3->5:5",
				"4->6:2",
				"5->6:1");
		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 7; ++j) {
				if (i == j) continue;
				Tuple<Integer, List<String>> result = lcs(i, j, input);
				System.out.printf("[ %d, %d ]: %d, %s\n", i, j, result.a, result.b.toString());
			}
		}
	}
	
	private String toPath(List<String> list) {
		return list.stream().collect(Collectors.joining("->"));
	}
}
