package coursera.bio;

import static coursera.bio.SpectralConvolution.convolution;
import static coursera.bio.Utils.stringToInts;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Test;

public class SpectralConvolutionTest {

	@Test
	public void convolution() {
		List<Integer> list = Arrays.asList(0, 137, 186, 323);
		List<Integer> expected = Arrays.asList(137, 137, 186, 186, 323, 49);
		assertEquals(expected, convolution.apply(list));
	}

	@Test
	public void convoultionExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("spectral_convolution_data.txt");
		List<Integer> list = stringToInts.apply(lines.get(1));
		List<Integer> expected = stringToInts.apply(lines.get(3));
		expected.sort(Comparator.naturalOrder());
		assertEquals(expected, convolution.apply(list));
	}

	@Test
	public void convolutionDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_104_4.txt");
		List<Integer> list = stringToInts.apply(lines.get(0));
		String result = convolution.apply(list).stream().map(i -> Integer.toString(i)).collect(Collectors.joining(" ")); 
		System.out.println(result);
	}

	@Test
	public void quiz() {
		List<Integer> list = Arrays.asList(0, 57, 118, 179, 236, 240, 301);
		Map<Integer, Long> counted = convolution.apply(list)
				.stream()
				.collect(Collectors.groupingBy(o -> o, Collectors.counting()));

		Comparator<Entry<Integer, Long>> byValue = (entry1, entry2) -> {
			return entry1.getValue().compareTo(entry2.getValue());
		};

		List<Entry<Integer, Long>> ordered = counted
				.entrySet()
				.stream()
				.filter(e -> e.getKey() >= 57 && e.getKey() <= 200)
				.sorted(byValue.reversed())
				.collect(Collectors.toList());
		System.out.println(ordered);
	}
}