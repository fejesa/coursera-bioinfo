package coursera.bio;

import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public class PatternUtil {

	public static BiFunction<String, String, Integer> patternCount = (text,
			pattern) -> {
		int count = 0;
		for (int i = 0; i <= text.length() - pattern.length(); ++i) {
			if (text.substring(i, pattern.length() + i).equals(pattern)) {
				++count;
			}
		}
		return count;
	};

	public static ToIntFunction<String> patternToNumber = (p) -> internalPatternToNumber(p);

	static int internalPatternToNumber(String pattern) {
		if (pattern.isEmpty()) {
			return 0;
		}
		char symbol = pattern.charAt(pattern.length() - 1);
		return 4
				* internalPatternToNumber(pattern.substring(0,
						pattern.length() - 1)) + symbolToNumber(symbol);
	}

	public static int symbolToNumber(char symbol) {
		switch (symbol) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'G':
			return 2;
		case 'T':
			return 3;
		default:
			throw new IllegalArgumentException();
		}
	}

	public static BiFunction<Integer, Integer, String> numberToPattern = (n, k) -> internalNumberToPattern(
			n, k);

	static String internalNumberToPattern(int number, int k) {
		if (k == 1) {
			return String.valueOf(numberToSymbol.apply(number));
		}
		int prefixIndex = number / 4;
		int reminder = (int) (number % 4);
		String prefixPattern = internalNumberToPattern(prefixIndex, k - 1);
		char symbol = numberToSymbol.apply(reminder);
		return prefixPattern + symbol;
	}

	public static BiFunction<String, Integer, int[]> computeFrequencies = (t, k) -> internalComputeFrequencies(
			t, k);

	private static int[] internalComputeFrequencies(String text, int k) {
		int length = (int) Math.pow(4, k);
		int[] frequencies = new int[length];
		for (int i = 0; i <= text.length() - k; ++i) {
			String pattern = text.substring(i, i + k);
			int j = patternToNumber.applyAsInt(pattern);
			frequencies[j] = frequencies[j] + 1;
		}
		return frequencies;
	}

	private static IntFunction<Character> numberToSymbol = n -> {
		switch (n) {
		case 0:
			return 'A';
		case 1:
			return 'C';
		case 2:
			return 'G';
		case 3:
			return 'T';
		default:
			throw new IllegalArgumentException();
		}
	};	
}