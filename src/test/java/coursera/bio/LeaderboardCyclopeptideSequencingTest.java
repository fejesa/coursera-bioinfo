package coursera.bio;

import static coursera.bio.LeaderboardCyclopeptideSequencing.leaderboardSequence;
import static coursera.bio.Utils.stringToInts;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LeaderboardCyclopeptideSequencingTest {

	@Test
	public void sequence() {
		List<Integer> spec = Arrays.asList(0, 71, 113, 129, 147, 200, 218, 260, 313, 331, 347, 389, 460);
		assertEquals("113-147-71-129", leaderboardSequence.apply(spec, 10));
	}

	@Test
	public void sequenceExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("leaderboard.txt");
		List<Integer> spec = stringToInts.apply(lines.get(2));
		Integer n = Integer.valueOf(lines.get(1));
		System.out.println(leaderboardSequence.apply(spec, n));
	}

	@Test
	public void sequenceDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_102_7.txt");
		List<Integer> spec = stringToInts.apply(lines.get(1));
		Integer n = Integer.valueOf(lines.get(0));
		assertEquals("186-115-137-115-103-71-71-156-103-101-137-101-97-128-129-137-129-97-128-137-128-97-101-103", leaderboardSequence.apply(spec, n));
	}

	@Test
	public void spectrumTyrocidineB1() throws Exception {
		List<String> lines = FileUtil.loadFile("Tyrocidine_B1_Spectrum_10.txt");
		List<Integer> spec = stringToInts.apply(lines.get(0));
		assertEquals("99-163-128-114-147-186-97-147-113-128", leaderboardSequence.apply(spec, 1000));
	}

	@Test
	public void spectrumTyrocidineB1_25() throws Exception {
		List<String> lines = FileUtil.loadFile("spectrum_25_tyrocidine_b1.txt");
		List<Integer> spec = stringToInts.apply(lines.get(0));
		System.out.println(leaderboardSequence.apply(spec, 1000));
	}
}