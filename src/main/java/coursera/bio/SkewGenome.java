package coursera.bio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SkewGenome {

	public static Function<String, int[]> skew = g -> internalSkew(g);

	public static Function<String, int[]> maximumSkew = (g) -> {
		int[] array = internalSkew(g);
		List<Integer> mins = new ArrayList<>();
		int marker = 0;
		for (int i = 1, min = Integer.MIN_VALUE; i < array.length; ++i) {
			if (array[i] == min) {
				mins.add(i);
			} else if (array[i] > min) {
				min = array[i];
				marker = mins.size();
				mins.add(i);
			}
		}
		return mins
				.subList(marker, mins.size())
				.stream()
				.mapToInt(Integer::intValue).toArray();
	};
	
	public static Function<String, int[]> minimumSkew = (g) -> {
		int[] array = internalSkew(g);
		List<Integer> mins = new ArrayList<>();
		int marker = 0;
		for (int i = 1, min = Integer.MAX_VALUE; i < array.length; ++i) {
			if (array[i] == min) {
				mins.add(i);
			} else if (array[i] < min) {
				min = array[i];
				marker = mins.size();
				mins.add(i);
			}
		}
		return mins
				.subList(marker, mins.size())
				.stream()
				.mapToInt(Integer::intValue).toArray();
	};

	private static int[] internalSkew(String genome) {
		int[] array = new int[genome.length() + 1];
		for (int index = 1; index < array.length; ++index) {

			int value = array[index - 1];
			char nuc = genome.charAt(index - 1);
			if (nuc == 'G') {
				++value;
			} else if (nuc == 'C') {
				--value;
			}
			array[index] = value;

		}
		return array;
	}
}
