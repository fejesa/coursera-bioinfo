package coursera.bio;

import java.util.function.UnaryOperator;

public class ReverseComplement {

	private static UnaryOperator<Character> complement = n -> {
		switch (n) {
		case 'A':
			return 'T';
		case 'C':
			return 'G';
		case 'G':
			return 'C';
		case 'T':
			return 'A';
		default:
			throw new IllegalArgumentException();
		}
	};

	public static UnaryOperator<String> reverseComplement = (pattern) -> {
		StringBuilder builder = new StringBuilder();
		for (int i = pattern.length() - 1; i >= 0; --i) {
			builder.append(complement.apply(pattern.charAt(i)));
		}
		return builder.toString();
	};
}