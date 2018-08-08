package coursera.bio;

import static coursera.bio.StringComposition.compose;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StringCompositionTest {

	@Test
	public void compose() {
		List<String> expected = Arrays.asList("AATCC", "ATCCA", "CAATC", "CCAAC", "TCCAA");
		assertEquals(expected, compose.apply("CAATCCAAC", 5));
	}

	@Test
	public void composeExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("string_com.txt");
		List<String> expected = lines.subList(4, lines.size());
		assertEquals(expected, compose.apply(lines.get(2), Integer.valueOf(lines.get(1))));
	}

	@Test
	public void composeDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_197_3.txt");
		String result = compose
				.apply(lines.get(1), Integer.valueOf(lines.get(0))).stream()
				.collect(Collectors.joining("\n"));
		System.out.println(result);
	}
}
