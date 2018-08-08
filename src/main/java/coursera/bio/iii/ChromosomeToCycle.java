package coursera.bio.iii;

import static coursera.bio.Utils.toNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChromosomeToCycle {

	public static Function<String, String> encode = chromosome -> {
		List<Integer> numbers = toNumbers.apply(chromosome);
		List<Integer> list = new ArrayList<>();
		numbers.forEach(i -> {
			if (i > 0) {
				list.add(2 * i - 1);
				list.add(2 * i);
			} else {
				list.add(-2 * i);
				list.add(-2 * i - 1);
			}
		});
		return list.stream().map(i -> i.toString()).collect(Collectors.joining(" ", "(", ")"));
	};
}
