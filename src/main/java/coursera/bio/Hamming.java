package coursera.bio;

import java.util.function.BiFunction;

public class Hamming {

	public static BiFunction<String, String, Integer> distance = (s1, s2) -> {
		if (s1.length() != s2.length()) {
			throw new IllegalArgumentException();
		}
		int dist = 0;
		for (int i = 0; i < s1.length(); ++i) {
			if (s1.charAt(i) != s2.charAt(i)) {
				++dist;
			}
		}
		return dist;
	};
}
