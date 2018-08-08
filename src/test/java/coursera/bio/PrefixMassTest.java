package coursera.bio;

import static coursera.bio.PrefixMass.mass;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PrefixMassTest {

	@Test
	public void mass() {
		List<Integer> expected = Arrays.asList(0, 114, 242, 371, 484);
		assertEquals(expected, mass.apply("NQEL"));
	}
}
