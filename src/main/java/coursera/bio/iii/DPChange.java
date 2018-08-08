package coursera.bio.iii;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class DPChange {

	public static BiFunction<Integer, List<Integer>, Integer> change = (money,
			coins) -> {
		final Map<Integer, Integer> minNumberCoins = new HashMap<>();
		minNumberCoins.put(0, 0);
		for (int m = 1; m <= money; ++m) {
			minNumberCoins.put(m, Integer.MAX_VALUE);
			for (int i : coins) {
				if (m >= i
						&& minNumberCoins.get(m - i) + 1 < minNumberCoins.get(m)) {
					minNumberCoins.put(m, minNumberCoins.get(m - i) + 1);
				}
			}
		}

		return minNumberCoins.get(money);
	};
}
