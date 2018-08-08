package coursera.bio;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CountingPeptides {

	private static int[] AMINO_ACID_MASS = { 57, 71, 87, 97, 99, 101, 103, 113,
			114, 115, 128, 129, 131, 137, 147, 156, 163, 186 };

	public static Function<Integer, Long> count = mass -> {
		Map<Integer, Long> lcp = new HashMap<>();
		lcp.put(0, 1L);
		for (int i = 57; i <= mass; i++) {
			peptides(i, lcp);
		}
		return lcp.containsKey(mass) ? lcp.get(mass) : 0;
	};

	private static void peptides(int length, Map<Integer, Long> lcp) {
		for (int i = 0; i < AMINO_ACID_MASS.length; i++) {
			if (lcp.containsKey(length - AMINO_ACID_MASS[i]))
				lcp.put(length,
						lcp.get(length - AMINO_ACID_MASS[i])
								+ (lcp.containsKey(length) ? lcp.get(length)
										: 0));
		}
	}
}
