package coursera.bio;

import static coursera.bio.CyclopeptideSequencing.countSubpepdites;
import static coursera.bio.CyclopeptideSequencing.isConsistent;
import static coursera.bio.CyclopeptideSequencing.sequences;
import static coursera.bio.CyclopeptideSequencing.theoreticalSpectrum;
import static coursera.bio.LinearSpectrum.linearSpectrum;
import static coursera.bio.Utils.stringToInts;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class CyclopeptideSequencingTest {

	@Test
	public void countSubpeptides() {
		assertEquals(980597910, countSubpepdites(31315));
	}

	@Test
	public void countSubpeptidesDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_98_3.txt");
		assertEquals(1735597260,
				countSubpepdites(Integer.parseInt(lines.get(0))));
	}

	@Test
	public void spectrum() {
		List<Integer> expected = Arrays.asList(0, 113, 114, 128, 129, 227, 242,
				242, 257, 355, 356, 370, 371, 484);
		assertEquals(expected, theoreticalSpectrum("LEQN"));
	}

	@Test
	public void spectrumExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("theoretical_spectrum_data.txt");
		String s = lines.get(lines.size() - 1);
		List<Integer> expected = stringToInts.apply(s);
		assertEquals(expected, theoreticalSpectrum(lines.get(1)));
	}

	@Test
	public void spectrumDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_98_4.txt");
		String result = theoreticalSpectrum(lines.get(0)).stream()
				.map(i -> Integer.toString(i)).collect(Collectors.joining(" "));
		assertEquals(
				"0 71 87 97 99 101 113 114 115 128 128 128 131 147 156 168 186 186 211 214 215 218 232 241 243 256 257 269 275 282 284 301 312 317 319 333 342 346 354 371 383 388 397 397 404 412 418 429 432 433 443 461 468 470 501 514 525 525 527 530 532 532 533 544 557 560 569 574 601 615 626 629 629 640 645 647 653 661 672 688 700 700 713 716 730 739 742 743 744 768 775 787 789 800 801 815 826 844 844 847 856 858 867 872 886 898 903 915 929 934 943 945 954 957 957 975 986 1000 1001 1012 1014 1026 1033 1057 1058 1059 1062 1071 1085 1088 1101 1101 1113 1129 1140 1148 1154 1156 1161 1172 1172 1175 1186 1200 1227 1232 1241 1244 1257 1268 1269 1269 1271 1274 1276 1276 1287 1300 1331 1333 1340 1358 1368 1369 1372 1383 1389 1397 1404 1404 1413 1418 1430 1447 1455 1459 1468 1482 1484 1489 1500 1517 1519 1526 1532 1544 1545 1558 1560 1569 1583 1586 1587 1590 1615 1615 1633 1645 1654 1670 1673 1673 1673 1686 1687 1688 1700 1702 1704 1714 1730 1801",
				result);
	}

	@Test
	public void sequences() {
		List<Integer> spectrum = Arrays.asList(0, 113, 128, 186, 241, 299, 314, 427);
		String result = sequences.apply(spectrum)
				.stream()
				.collect(Collectors.joining(" "));
		assertEquals("186-128-113 186-113-128 128-186-113 128-113-186 113-186-128 113-128-186", result);
	}

	@Test
	public void sequencesExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("cycloseq_data.txt");
		List<Integer> spectrum = stringToInts.apply(lines.get(1));

		List<String> list = Arrays.asList(lines.get(3).split(" "));
		Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
		list.sort(comparator.reversed());

		String expected = list
				.stream()
				.collect(Collectors.joining(" "));

		String result = sequences.apply(spectrum)
				.stream()
				.collect(Collectors.joining(" "));

		assertEquals(expected, result);
	}

	@Test
	public void sequencesDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_100_5.txt");
		List<Integer> spectrum = stringToInts.apply(lines.get(0));

		String result = sequences.apply(spectrum)
				.stream()
				.collect(Collectors.joining(" "));

		System.out.println(result);
	}

	@Test
	public void consistentQuiz() {
		List<Integer> spec = Arrays.asList(0, 71, 99, 101, 103, 128, 129, 199, 200, 204, 227, 230, 231, 298, 303, 328, 330, 332, 333);
		System.out.println(isConsistent.apply(linearSpectrum.apply("AVQ"), spec));
		System.out.println(isConsistent.apply(linearSpectrum.apply("TCQ"), spec));
		System.out.println(isConsistent.apply(linearSpectrum.apply("CTV"), spec));
		System.out.println(isConsistent.apply(linearSpectrum.apply("TVQ"), spec));
		System.out.println(isConsistent.apply(linearSpectrum.apply("QCV"), spec));
		System.out.println(isConsistent.apply(linearSpectrum.apply("VAQ"), spec));
	}
}