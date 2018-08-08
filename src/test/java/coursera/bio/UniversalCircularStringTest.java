package coursera.bio;

import static coursera.bio.UniversalCircularString.findCycle;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UniversalCircularStringTest {

	@Test
	public void findCycle() {
		assertEquals("0011", findCycle.apply(2));
		assertEquals("00010111", findCycle.apply(3));
		assertEquals("0000100110101111", findCycle.apply(4));
	}

	@Test
	public void findCycleDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_203_10.txt");
		Integer k = Integer.valueOf(lines.get(0));
		String expected = "0111111000000001000000110000010100000111000010010000101100001101000011110001000100110001010100010111000110010001101100011101000111110010010100100111001010110010110100101111001100110101001101110011101100111101001111111101010101110101101101011111011011110111";
		assertEquals(expected, findCycle.apply(k));
	}
}
