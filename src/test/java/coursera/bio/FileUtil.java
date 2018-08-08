package coursera.bio;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

	public static List<String> loadFile(String fileName) throws Exception {
		URL url = FileUtil.class.getResource("/" + fileName);
		List<String> lines = Files.readAllLines(Paths.get(url.toURI()));
		return lines;
	}
}