package coursera.bio;

import static coursera.bio.ConvolutionCyclopeptideSequencing.convolutionSequencing;
import static coursera.bio.Utils.stringToInts;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class ConvolutionCyclopeptideSequencingTest {

	@Test
	public void convolutionSequencing() {
		List<Integer> spec = Arrays
				.asList(57, 57, 71, 99, 129, 137, 170, 186, 194, 208, 228, 265, 285, 299, 307, 323, 356, 364, 394, 422, 493);
		String expected = "99-71-137-57-72-57";
		assertTrue(match(expected, convolutionSequencing.apply(spec, 20, 60)));
	}

	@Test
	public void convolutionSequencingExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("convolution.txt");
		Integer m = Integer.valueOf(lines.get(1));
		Integer n = Integer.valueOf(lines.get(2));
		List<Integer> spec = stringToInts.apply(lines.get(3));
		assertTrue(match(lines.get(5), convolutionSequencing.apply(spec, m, n)));
	}

	@Test
	public void convolutionSequencingDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_104_7.txt");
		Integer m = Integer.valueOf(lines.get(0));
		Integer n = Integer.valueOf(lines.get(1));
		List<Integer> spec = stringToInts.apply(lines.get(2));
		assertEquals("97-128-113-186-129-114-113-186-87-87-114-131-115-60-87", convolutionSequencing.apply(spec, m, n));
	}

	private boolean match(String expected, String actual) {
		String[] array = actual.split("-");
		String[] as = new String[array.length];
		for (int i = 0; i < array.length; ++i) {
			as[i] = array[array.length - i - 1];
		}

		for (int i = 0; i < as.length; ++i) {
			String last = as[array.length-1];
			System.arraycopy(as, 0, as, 1, as.length-1 );
			as[0] = last;

			String r = Arrays.stream(as).collect(Collectors.joining("-"));
			if (expected.equals(r)) {
				return true;
			}
		}

		return false;
	}
}
