package coursera.bio;

import static coursera.bio.CyclicSpectrum.cyclicSpectrum;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CyclicSpectrumTest {

	@Test
	public void spectrum() {
		List<Integer> expected = Arrays.asList(0, 113, 114, 128, 129, 227, 242, 242, 257, 355, 356, 370, 371, 484);
		assertEquals(expected, cyclicSpectrum.apply("NQEL"));
	}

	@Test
	public void spectrumQuiz() {
		List<Integer> expected = Arrays.asList(0, 71, 101, 113, 131, 184, 202, 214, 232, 285, 303, 315, 345, 416);
		System.out.println(cyclicSpectrum.apply("MAIT").equals(expected));
		System.out.println(cyclicSpectrum.apply("TALM").equals(expected));
		System.out.println(cyclicSpectrum.apply("TAIM").equals(expected));
		System.out.println(cyclicSpectrum.apply("TMIA").equals(expected));
		System.out.println(cyclicSpectrum.apply("IAMT").equals(expected));
		System.out.println(cyclicSpectrum.apply("MTAL").equals(expected));
	}
}
