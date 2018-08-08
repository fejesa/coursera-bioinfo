package coursera.bio;

import static coursera.bio.MotifEnumeration.findMotifs;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class MotifEnumerationTest {

	@Test
	public void findMotifs() {
		List<String> text = Arrays.asList("ATTTGGC", "TGCCTTA", "CGGTATC", "GAAAATT");
		assertEquals("ATA ATT GTT TTT",
				findMotifs
				.apply(text, 3, 1)
				.stream()
				.collect(Collectors.joining(" ")));
	}

	@Test
	public void findMotifExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("motif_enumeration_data.txt");
		String[] nums = lines.get(1).split(" ");
		List<String> text = Arrays.asList(
				lines.get(2),
				lines.get(3),
				lines.get(4),
				lines.get(5),
				lines.get(6),
				lines.get(7));
		assertEquals(lines.get(9),
				findMotifs
				.apply(text, Integer.parseInt(nums[0]), Integer.parseInt(nums[1]))
				.stream()
				.collect(Collectors.joining(" ")));
	}

	@Test
	public void findMotifDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_156_7.txt");
		String[] nums = lines.get(0).split(" ");
		List<String> text = Arrays.asList(
				lines.get(1),
				lines.get(2),
				lines.get(3),
				lines.get(4),
				lines.get(5),
				lines.get(6));
		assertEquals("TTGAC TTGCC TTGGC TTGTC",
				findMotifs
				.apply(text, Integer.parseInt(nums[0]), Integer.parseInt(nums[1]))
				.stream()
				.collect(Collectors.joining(" ")));
	}

	@Test
	public void findMotifSubtle() throws Exception {
		List<String> lines = FileUtil.loadFile("15_4_implanted_motif_2.txt");
		List<String> text = lines.stream().map(l -> l.replace("*", "")).collect(Collectors.toList());
		System.out.println(findMotifs.apply(text, 15, 4));
		// OutOfMemory
	}
}