package coursera.bio.iii;

import static coursera.bio.iii.AffineGapPenaltiesAlignment.alignment;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class AffineGapPenaltiesAlignmentTest {

	@Test
	public void align() {
		Triplet<Integer, String, String> result = alignment("PRTEINS", "PRTWPSEIN");
		assertEquals(Integer.valueOf(8), result.x);
		assertEquals("PRT---EINS", result.y);
		assertEquals("PRTWPSEIN-", result.z);
	}

	@Test
	public void alignExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("affine_gap.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(1), lines.get(2));
		assertEquals(Integer.valueOf(lines.get(4)), result.x);
		System.out.println(lines.get(5));
		System.out.println(result.y);
		System.out.println(lines.get(6));
		System.out.println(result.z);
	}

	@Test
	public void alignDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_249_8.txt");
		Triplet<Integer, String, String> result = alignment(lines.get(0), lines.get(1));
		assertEquals(Integer.valueOf(221), result.x);
		assertEquals("CNISANGNNSRFAPHCAKVCDNLMFVPFSSNWRIVWISMYCPRIWIIGVGGNQTTAWPGGWNRNGAIYVNKKCRK", result.y);
		assertEquals("CNISANGNNSRFAHHCKKVCDNL--------WMHV---MYCPS------GGNQTTAWPGGW-RNGANYSNCNCRK", result.z);
	}
}