package coursera.bio;

import static coursera.bio.ReverseComplement.reverseComplement;
import static coursera.bio.Utils.dnaToRna;
import static coursera.bio.Utils.rnaToDna;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PeptideEncoding {

	private static Map<String, Character> codonTable = GeneticCode.dictionary();

	private static boolean detectProtein(String rna, String protein, int count) {
		if (rna.length() <= 3) {
			return false;
		}
		String p = rna.substring(3 * count, 3 * (count + 1));

		if (count + 1 == protein.length()
				&& codonTable.get(p) == protein.charAt(count)) {
			return true;
		}
		if (count + 1 < protein.length()
				&& codonTable.get(p) == protein.charAt(count)) {
			return detectProtein(rna, protein, count + 1);
		}
		return false;
	}

	private static List<String> lookForProtein(String dna, String protein) {
		String rna = dnaToRna(dna);
		List<String> result = new ArrayList<>();
		while (rna.length() > 0) {
			if (detectProtein(rna, protein, 0)) {
				result.add(rnaToDna(rna.substring(0, protein.length() * 3)));
			}
			rna = rna.substring(1);
		}
		return result;
	}

	public static List<String> peptideEncoding(String dna, String protein) {

		List<String> result = lookForProtein(dna, protein);

		List<String> list2 = 
				lookForProtein(reverseComplement.apply(dna), protein)
				.stream()
				.map(s -> reverseComplement.apply(s))
				.collect(Collectors.toList());

		result.addAll(list2);

		return result;
	}
}
