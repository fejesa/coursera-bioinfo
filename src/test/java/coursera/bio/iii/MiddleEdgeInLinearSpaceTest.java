package coursera.bio.iii;

import static coursera.bio.iii.MiddleEdgeInLinearSpace.middleEdge;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import coursera.bio.FileUtil;

public class MiddleEdgeInLinearSpaceTest {

	@Test
	public void middleEdge() {
		String v = "PLEASANTLY";
		String w = "MEASNLY";
		Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> result = middleEdge.apply(v, w);
		assertEquals(new Tuple<Integer, Integer>(4, 3), result.a);
		assertEquals(new Tuple<Integer, Integer>(5, 4), result.b);
		System.out.println(print(result.a) + " " + print(result.b));
	}

	@Test
	public void middleEdgeExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("linear_space.txt");
		String v = lines.get(1);
		String w = lines.get(2);
		Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> result = middleEdge.apply(v, w);
		assertEquals(lines.get(4), print(result.a) + " " + print(result.b));
	}

	@Test
	public void middleEdgeDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_250_12.txt");
		String v = lines.get(0);
		String w = lines.get(1);
		Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> result = middleEdge.apply(v, w);
		assertEquals(new Tuple<Integer, Integer>(522, 517), result.a);
		assertEquals(new Tuple<Integer, Integer>(523, 518), result.b);
		System.out.println(print(result.a) + " " + print(result.b));
	}

	private static String print(Tuple<Integer, Integer> t) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(t.a);
		sb.append(", ");
		sb.append(t.b);
		sb.append(")");
		return sb.toString();
	}
}
