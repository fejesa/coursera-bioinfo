package coursera.bio;

import static coursera.bio.FrequentWords.frequentWordsBySorting;
import static coursera.bio.PatternUtil.patternCount;

import java.util.HashSet;
import java.util.Set;

public class Clumps {

	public static Set<String> clumps(String text, int k, int L, int t) {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < text.length() - L; ++i) {
			String s = text.substring(i, i + L);
			Set<String> patterns = frequentWordsBySorting.apply(s, k);
			for (String pattern : patterns) {
				if (!set.contains(pattern)
						&& patternCount.apply(s, pattern) >= t) {
					set.add(pattern);
				}
			}
		}
		return set;
	}
}
