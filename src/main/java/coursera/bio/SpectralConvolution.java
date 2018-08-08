package coursera.bio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class SpectralConvolution {

	public static Function<List<Integer>, List<Integer>> convolution = spec -> {
		spec.sort(Comparator.naturalOrder());
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < spec.size(); ++i) {
			for (int j = i + 1; j < spec.size(); ++j) {
				int sub = spec.get(j) - spec.get(i);
				if (sub > 0) {
					list.add(sub);
				}
			}
		}
		list.sort(Comparator.naturalOrder());
		return list;
	};
}