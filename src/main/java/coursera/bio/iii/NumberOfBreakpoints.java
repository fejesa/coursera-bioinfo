package coursera.bio.iii;

import java.util.List;
import java.util.function.Function;

import coursera.bio.Utils;

public class NumberOfBreakpoints {

	public static Function<String, Integer> breakpoints = in -> {
		List<Integer> list = Utils.toNumbersInclusion.apply(in);
		int counter = 0;
		for (int i = 0; i < list.size() - 1; ++i) {
			int px = list.get(i);
			int py = list.get(i + 1);
			if (py - 1 != px) {
				++counter;
			}
		}
		return counter;
	};
}