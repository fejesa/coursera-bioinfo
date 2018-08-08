package coursera.bio.iii;

import static coursera.bio.Utils.toNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CycleToChromosome {

	public static Function<String, String> decode = cycle -> {
		List<Integer> numbers = toNumbers.apply(cycle);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < numbers.size() / 2; ++i) {
			int j2 = numbers.get(i * 2);
			int j2_1 = numbers.get(i * 2 + 1);
			if (j2_1 < j2) {
				list.add(-j2 / 2);
			} else {
				list.add((j2_1) / 2);
			}
		}
		return list.stream().map(n -> String.format("%+d", n)).collect(Collectors.joining(" ", "(", ")"));
	};

}
