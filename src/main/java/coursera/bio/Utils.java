package coursera.bio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import coursera.bio.graph.Vertex;

public class Utils {

	private static Map<String, Character> proteinDictionary = null;

	public static Map<Character, Integer> massTable = null;

	static {
		massTable = new HashMap<>();
		massTable.put('G', 57);
		massTable.put('A', 71);
		massTable.put('S', 87);
		massTable.put('P', 97);
		massTable.put('V', 99);
		massTable.put('T', 101);
		massTable.put('C', 103);
		massTable.put('I', 113);
		massTable.put('L', 113);
		massTable.put('N', 114);
		massTable.put('D', 115);
		massTable.put('K', 128);
		massTable.put('Q', 128);
		massTable.put('E', 129);
		massTable.put('M', 131);
		massTable.put('H', 137);
		massTable.put('F', 147);
		massTable.put('R', 156);
		massTable.put('Y', 163);
		massTable.put('W', 186);
	}
	
	static {
		proteinDictionary = new HashMap<>();
		proteinDictionary.put("His", 'H');
		proteinDictionary.put("Gln", 'Q');
		proteinDictionary.put("Pro", 'P');
		proteinDictionary.put("Arg", 'R');
		proteinDictionary.put("Leu", 'L');
		proteinDictionary.put("Asp", 'D');
		proteinDictionary.put("Glu", 'E');
		proteinDictionary.put("Ala", 'A');
		proteinDictionary.put("Gly", 'G');
		proteinDictionary.put("Val", 'V');
		proteinDictionary.put("Tyr", 'Y');
		proteinDictionary.put("Ser", 'S');
		proteinDictionary.put("Cys", 'C');
		proteinDictionary.put("Trp", 'W');
		proteinDictionary.put("Phe", 'F');
		proteinDictionary.put("Asn", 'N');
		proteinDictionary.put("Lys", 'K');
		proteinDictionary.put("Thr", 'T');
		proteinDictionary.put("Ile", 'I');
		proteinDictionary.put("Met", 'M');
	}

	public static Map<String, Character> proteinDictionary() {
		return proteinDictionary;
	}

	public static String dnaToRna(String dna) {
		return dna.replace('T', 'U');
	}

	public static String rnaToDna(String rna) {
		return rna.replace('U', 'T');
	}

	public static Function<String, String> prefix = kmer -> {
		int length = kmer.length();
		return kmer.substring(0, length - 1);
	};

	public static Function<String, String> suffix = kmer -> {
		int length = kmer.length();
		return kmer.substring(1, length);
	};

	public static Function<int[], String> intArrayToString = (array) -> {
		return Arrays.
				stream(array)
				.mapToObj(i -> Integer.toString(i))
				.collect(Collectors.joining(" "));
	};

	public static <T> Set<T> getCommonElements(
			Collection<? extends Collection<T>> collections) {

		if (collections.isEmpty()) {
			return Collections.emptySet();
		}

		Set<T> common = new LinkedHashSet<T>();
		Iterator<? extends Collection<T>> iterator = collections.iterator();
		common.addAll(iterator.next());

		while (iterator.hasNext()) {
			common.retainAll(iterator.next());
		}

		return common;
	}

	public static Set<String> distinctKmers(String text, int k) {
		Set<String> set = new LinkedHashSet<>();
		for (int i = 0; i <= text.length() - k; ++i) {
			set.add(text.substring(i, i + k));
		}
		return set;
	}

	public static List<String> kmers(String text, int k) {
		List<String> kmers = new ArrayList<>();
		for (int i = 0; i < text.length() - k + 1; ++i) {
			kmers.add(text.substring(i, i + k));
		}
		return kmers;
	}

	private static Random random = new Random(); 

	public static BiFunction<String, Integer, String> randomKmer = (text, k) -> {
		int i = random.nextInt(text.length() - k + 1);
		return text.substring(i, i + k);
	};

	public static UnaryOperator<Integer> getNextIndex = n -> random.nextInt(n);

	public static LinkedList<String> repetitions(int length) {
		LinkedList<String> items = new LinkedList<>();

		char[] input = { 'A', 'C', 'G', 'T' };
		rep(items, input, new char[length], 0);

		return items;
	}

	public static Function<List<Vertex<String>>, String> printGraph = vertices -> {
		Comparator<Vertex<String>> comp = (v, w) -> v.label.compareTo(w.label);
		vertices.sort(comp);

		StringBuilder builder = new StringBuilder();
		vertices
			.stream()
			.filter(v -> !v.adjacents().isEmpty())
			.forEach(v -> {
				builder.append(v.label);
				builder.append(" -> ");
				v.adjacents().sort(comp);
				builder.append(v.adjacents().stream()
						.map(a -> (String) a.label)
						.collect(Collectors.joining(",")));
				builder.append("\n");
		});

		return builder.substring(0, builder.length() - 1);
	};

	private static void rep(LinkedList<String> reps, char[] input, char[] item, int count) {
		if (count < item.length) {
			for (int i = 0; i < input.length; i++) {
				item[count] = input[i];
				rep(reps, input, item, count + 1);
			}
		} else {
			reps.add(String.valueOf(item));
		}
	}

	public static Function<String, List<Integer>> stringToInts = s -> {
		return Arrays.stream(s.split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	};

	public static Function<List<String>, List<String>> expand = peptides -> {
		List<String> expanded = new ArrayList<>();
		for(String s : peptides) {
			for (Character c : massTable.keySet()) {
				expanded.add(s + c);
			}
		}
		return expanded;
	};

	public static Function<String, Integer> peptideMass = peptide -> {
		int sum = peptide
				.chars()
				.reduce(0, (m, c) -> m + massTable.get((char) c));
		return sum;
	};

	public static Function<String, List<Integer>> toNumbersInclusion = in -> {
		String s = in.substring(1, in.length() - 1);
		String[] nums = s.split(" ");
		List<Integer> result = new ArrayList<>();
		result.add(0);
		result.addAll(Arrays.stream(nums).map(Integer::parseInt)
				.collect(Collectors.toList()));
		result.add(nums.length + 1);
		return result;
	};

	public static Function<String, List<Integer>> toNumbers = in -> {
		String s = in.substring(1, in.length() - 1);
		String[] nums = s.split(" ");
		return Arrays.stream(nums).map(Integer::parseInt)
				.collect(Collectors.toList());
	};
}