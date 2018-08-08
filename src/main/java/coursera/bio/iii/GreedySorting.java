package coursera.bio.iii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GreedySorting {

	private static Function<String, List<Integer>> toNumbers = in -> {
		String s = in.substring(1, in.length() - 1);
		String[] nums = s.split(" ");
		return Arrays.stream(nums).map(Integer::parseInt)
				.collect(Collectors.toList());
	};

	private static Function<List<Integer>, String> toLine = nums -> {
		return nums.stream().map(n -> String.format("%+d", n))
				.collect(Collectors.joining(" ", "(", ")"));
	};

	private static BiFunction<Integer, List<Integer>, List<Integer>> nextBlock = (
			next, list) -> {
		List<Integer> sublist = new ArrayList<>();
		for (int i = 0; i < list.size(); ++i) {
			Integer value = list.get(i);
			sublist.add(-value);
			if (next.equals(Math.abs(value))) {
				break;
			}
		}
		return IntStream.range(0, sublist.size())
				.map(i -> sublist.get(sublist.size() - i - 1)).boxed()
				.collect(Collectors.toList());
	};

	@SafeVarargs
	private static List<Integer> merge(List<Integer>... lists) {
		return Arrays.stream(lists).flatMap(l -> l.stream())
				.collect(Collectors.toList());
	}

	public static Function<String, List<String>> sort = in -> {
		List<Integer> list = toNumbers.apply(in);

		List<String> result = new ArrayList<>();
		List<Integer> part = new ArrayList<>();

		for (int k = 0; k < list.size(); ++k) {

			int current = list.get(k);

			if (current == k + 1) {
				part.add(current);
			} else if (-current == k + 1) {
				list.set(k, -current);
				part.add(k, -current);
				result.add(toLine.apply(list));
			} else {

				List<Integer> block = nextBlock.apply(k + 1,
						list.subList(k, list.size()));
				list = merge(part, block,
						list.subList(k + block.size(), list.size()));
				Integer next = block.get(0);
				part.add(next);
				result.add(toLine.apply(list));

				if (next < 0) {
					list.set(k, -next);
					part.set(k, -next);
					result.add(toLine.apply(list));
				}
			}
		}
		return result;
	};
}