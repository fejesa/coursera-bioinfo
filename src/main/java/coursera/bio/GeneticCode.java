package coursera.bio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneticCode {

	private final static String RNA_CODON = "RNA_codon_table_1.txt";

	public static Map<String, Character> dictionary() {
		try {
			URL url = GeneticCode.class.getResource("/" + RNA_CODON);
			Map<String, Character> map = new HashMap<>();
			try (BufferedReader br = new BufferedReader(new FileReader(
					new File(url.getFile())));) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] s = line.trim().split(" ");
					Character c = null;
					if (s.length == 1) {
						c = Character.valueOf('*');
					} else {
						c = s[1].charAt(0);
					}
					map.put(s[0], c);
				}
			}
			return map;
		} catch (IOException ioe) {
			throw new UncheckedIOException(ioe);
		}
	}

	public static Map<Character, List<String>> acidDictionary()
			throws IOException {
		Map<Character, List<String>> reverse = new HashMap<>();
		for (Map.Entry<String, Character> entry : dictionary().entrySet()) {
			List<String> list = reverse.get(entry.getValue());
			if (list == null) {
				list = new ArrayList<>();
				reverse.put(entry.getValue(), list);
			}
			list.add(entry.getKey());
		}
		return reverse;
	}
}