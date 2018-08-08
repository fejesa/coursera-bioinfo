package coursera.bio;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class ClumpsTest {

	@Test
	public void clumps() {
		String result = 
				Clumps.clumps(
						"CGGACTCGACAGATGTGAAGAACGACAATGTGAAGACTCGACACGACAGAGTGAAGAGAAGAGGAAACATTGTAA",
						5, 50, 4).stream().collect(Collectors.joining(" "));
		assertEquals("GAAGA CGACA", result);
	}

	@Test
	public void clumpsExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("clump_finding_data.txt");
		String text = lines.get(1);
		String[] array = lines.get(2).split(" ");
		int k = Integer.parseInt(array[0]);
		int L = Integer.parseInt(array[1]);
		int t = Integer.parseInt(array[2]);
		String result = 
				Clumps.clumps(
						text,
						k, L, t).stream().collect(Collectors.joining(" "));
		assertEquals("AAACCAGGTGG", result);
	}

	@Test
	public void clumpDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_4_5.txt");
		String text = lines.get(0);
		String[] array = lines.get(1).split(" ");
		int k = Integer.parseInt(array[0]);
		int L = Integer.parseInt(array[1]);
		int t = Integer.parseInt(array[2]);
		String result = 
				Clumps.clumps(
						text,
						k, L, t).stream().collect(Collectors.joining(" "));
		assertEquals("ACTACTGATCT GCGATGCTTAA AAAATAGTATG CGCGCGCTGCC", result);
	}
}