package coursera.bio;

import static coursera.bio.Utils.massTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class PrefixMass {

	public static Function<String, List<Integer>> mass = peptide -> {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		for (int i = 0; i < peptide.length(); ++i) {
			Character c = peptide.charAt(i);
			list.add(massTable.get(c) + list.get(list.size() - 1));
		}
		list.sort(Comparator.naturalOrder());
		return list;
	};
}
