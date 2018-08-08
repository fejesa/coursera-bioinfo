package coursera.bio;

import java.util.List;
import java.util.function.Function;

public class StringFromGenomPath {

	public static Function<List<String>, String> reconstruct = kmers -> {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < kmers.size(); ++i) {
			builder.append(kmers.get(i).charAt(0));
		}
		String last = kmers.get(kmers.size() - 1);
		builder.append(last.substring(1));
		return builder.toString();
	};
}