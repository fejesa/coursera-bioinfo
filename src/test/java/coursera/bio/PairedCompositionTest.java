package coursera.bio;

import static coursera.bio.PairedComposition.compose;
import static org.junit.Assert.*;
import java.util.stream.Collectors;

import org.junit.Test;

public class PairedCompositionTest {

	@Test
	public void compose() {
		String expected = "(AAT|CCA) (ATG|CAT) (ATG|GAT) (CAT|GGA) (CCA|GGG) (GCC|TGG) (GGA|GTT) (GGG|TGT) (TAA|GCC) (TGC|ATG) (TGG|ATG)";
		assertEquals(expected, compose.apply(3, 1, "TAATGCCATGGGATGTT")
				.stream()
				.collect(Collectors.joining(") (", "(", ")")));
		
		System.out.println(compose.apply(3, 2, "TAATGCCATGGGATGTT")
				.stream()
				.collect(Collectors.joining(") (", "(", ")")));
	}
}