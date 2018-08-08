package coursera.bio;

import java.util.function.Function;

public class LinearPeptide {

	public static Function<Integer, Long> count = n -> {
		long sum = 1;
		for (int i = 0; i <= n; ++i) {
			sum += i;
		}
		return sum;
	};
}
