package coursera.bio;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

public class StringComposition {

	public static BiFunction<String, Integer, List<String>> compose = (text, k) -> {
		List<String> kmers = Utils.kmers(text, k);
		kmers.sort(Comparator.naturalOrder());
		return kmers;
	};
}