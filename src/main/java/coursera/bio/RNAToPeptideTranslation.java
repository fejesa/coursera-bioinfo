package coursera.bio;

import java.util.Map;
import java.util.function.Function;

public class RNAToPeptideTranslation {

	public static Function<String, String> toPeptide = rna -> {

		Map<String, Character> codonTable = GeneticCode.dictionary();

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
	};
}
