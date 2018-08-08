package coursera.bio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PairedComposition {

	public static TriFunction<Integer, Integer, String, List<String>> compose = (k, d, text) -> {
		List<String> kmers = new ArrayList<>();
		for (int i = 0; i < text.length() - 2 * k - d + 1; ++i) {
			String paired1 = text.substring(i, i + k);
			String paired2 = text.substring(i + k + d, i + 2 * k + d);
			kmers.add(paired1 + "|" + paired2);
		}
		kmers.sort(Comparator.naturalOrder());
		return kmers;
	};
}