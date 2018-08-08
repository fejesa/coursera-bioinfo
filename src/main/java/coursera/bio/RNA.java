package coursera.bio;

import static coursera.bio.ReverseComplement.reverseComplement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RNA {

	private static Map<String, Character> codonTable = GeneticCode.dictionary();

	public static String proteinTranslation(String rna) {
		if (rna == null || rna.trim().length() < 3) {
			throw new IllegalArgumentException();
		}
		StringBuilder builder = new StringBuilder();
		loop: for (int i = 0; i < rna.length(); i = i + 3) {
			String codon = rna.substring(i, Math.min(i + 3, rna.length()));
			Character c = codonTable.get(codon);
			if (c == null) {
				throw new IllegalArgumentException("invalid RNA sequence");
			}
			if (c == '*') {
				break loop;
			}
			builder.append(c);
		}
		return builder.toString();
	}

	public static String dnaToRna(String dna) {
		return dna.replace('T', 'U');
	}

	public static String rnaToDna(String rna) {
		return rna.replace('U', 'T');
	}

	private static boolean detectProtein(String rna, String protein, int count) {
		if (rna.length() < 3) {
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

	public static Collection<String> peptideEncoding(String dna, String protein) {
		List<String> result = new ArrayList<>();

		List<String> list1 = lookForProtein(dna, protein);
		result.addAll(list1);

		List<String> list2 = lookForProtein(reverseComplement.apply(dna), protein);
		for (String s : list2) {
			result.add(reverseComplement.apply(s));
		}

		// Order by positions
		Map<Integer, String> m = new TreeMap<>();
		for (String s : result) {
			for (int i = 0; i < dna.length() - s.length(); ++i) {
				if (dna.substring(i, i + s.length()).equals(s)) {
					m.put(i, s);
				}
			}
		}

		return new ArrayList<>(m.values());
	}
}