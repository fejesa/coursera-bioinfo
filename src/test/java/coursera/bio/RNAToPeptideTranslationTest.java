package coursera.bio;

import static coursera.bio.RNAToPeptideTranslation.toPeptide;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class RNAToPeptideTranslationTest {

	@Test
	public void toPeptide() {
		assertEquals(
				"MAMAPRTEINSTRING",
				toPeptide
						.apply("AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA"));
	}

	@Test
	public void toPeptideExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("protein_translate_data.txt");
		assertEquals(lines.get(3), toPeptide.apply(lines.get(1)));
	}

	@Test
	public void toPeptideDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_96_5.txt");
		assertEquals(
				"MAFTGIHDELTHNAAERTSHRPVSRHCPAFFLPVNPGMRIGSFRVALPGARNLVWSLCQADKCSRGCGNIRVQVRASPLTQGYATGSYRTGHVKRTVHIIIAYVGGNLLSHNDKSVSPESSKDLRNGVTPFVCGRLTVVTHCCAQVIPFVRVVDREQVQHRYTRHDMSLEMQRDAGPVIQWCSGAEGQAPRPLKRVMAWTLSYRYYGLSVRPRSCWTSLQCVTGLSTNELACIWPWLRGSVINHAHLGDTLSMVAFLIPHLHNPPSRVDLYLNRTFSSLDVRHYGAKNMGVLKSQFSLIRYIRVKCGWRIFDFNSERPEIQWSYHRLAEIRVSPIRDPGSLEPYRIVAVQIRYSNLKLRLYGALKSLLTALSKRHLQKRLDPGYRKDVAIKAKVPDYWATVPLPHSSSRGRWYHYRHPFLPLSLPFHAVLERSQWTARTYGRAREGNNTSAPCRYVFQVYARAGIRGTSARGDASVRVALRWSCIVWSLLYRLICDGDKVLQLVDFCPETCKVKTPGDAVCFDCEGVWKRCMCILARPFPPNGSNSTADVAPSLLSSNTASQTLGMRPLASAGSLVDPSRVRFGATPLGKFKPFALSQISLLANMLRLSLEPPAYLSCVRPRCESRPSRIARTKSGPQSKACSRIQSRRGHVLLHAETSLIIRALRVTRSGQLNINNIPKIGKSRSLAMDCNGLRFRSRSRQVDIVQLCYICQIIGYPRFAFGRSETKMLRCTVVSMAWIRPYCHFFQHNPFLAGYEPLCSKYGGACRICTGIVSSARVPMSHFRMCGLIAEAMEDRAIYRKRMGCAPLLRTTLCGSNASGPLSIQPTSLSMQGLGYRFETPSRFLPYFRCRPSVSCPVSSRLLHLDYGPFGVCQRARLIPAAIAMSLRVNSRKISFPTSVLAAFIYIGGRGISHKMGLPVFLGFLLKWVDEGTFSDRKYRERHLDRPATGNRTDIDFPGGSRSAEWFSSTILYSVCPSPLTNSVYPFALTVLRIGERHNPIIPGPLGLDWSPEVYLASSALGALRQSSSLRVGEGVSSPALGRSPWCSPQSSWQPEAYFSRGATSGSGRLSSLTLYAHFYRPYFSHIKASSSMATDQTPHPVPPALPRVTACHRLSIGATNHWRAALASRRGINLWQVCVPMRRHCLSLLQMIRAGHRSMGKLVKAGNITSLQSSLVAAASCAGLPTQLANNGIDRWEAHWMELHGTDQLVELLYFAGTTASAPTLETPQVLTLGAPHWRNRMLVYQSQFKNRRNAVLAGWSLLEAYYGPGMRTQILTPPAAPTSWSERPQRGSAARRRCHYLHIICLAPELPGLLLFTIEVVPECAFHLCGTCLTPWRLSSDHQLAANCAYKSTSLKGEWLHKKGAGLLSPTTGYRRLRTRIISINKSRLPSEFRMSYALVRVKMVADALRKLYLRIPSPRAQGPEGDVVVPQFCGNQRYVECFRSDPATMVPSIRLQFLHTFGGNSGFEPRPRSANGGSWGVRGVSSSAHSANDCIRLLRTPRYKSLLAKQLNLVLSMALSETTAARFSGSRAIGNISTCRLTVCWHSCLWQYLNTSCVSGEDCPPFRILGTPLRASKFRHDSRRRGGGACYLGLWDIPTSPIRPYRNNKDIGLNFMEVYTSFQYSWIPSLPLIRVISKFPQSRKYGPRVKLYSVGEDRGVSMRHRWSLIDIVGHENILPSRRSNEGYSALGATSRRDVEPKDLKVQGHRSLPPRTSARLWRPLRRRVIGSLSSERLDQSNRFQRRRQNSVLARRGRDHFRMAFSTNVERRNNSTAKLRCYRGNTHTSGHKFDGSSLTLGRNIAFLLVSTVRSPRYVFISTILTCRISGWEVHGRQSRAWGRNEALRHPIQRHFVLHNYLEHPPSRKWGRCPPVNVSPEWTWFSYSSSYAYIAVRGPRERNTFPMAINVCAQKFHERVLRQRVDILWVSGSRAVMRSVSSGERGVILRSQAVSSTFTVIRPFMLAKTSATEVHAASSSIASVTSSGPQSLISRRNKKVVKVGASISRTGWLGTHDIDEHGVSLTASLRSPFFVLSRCYQVLPRSDLMQLTSSFVLAIVSELLELTTSEENARGETPSYRPYAPHPRRTIRATTLWVQDEEWRSSPRLPTYELGDLEAMLFYKRGRKGGLAYWHWYVILRHGATVMTLGQISDDMSNEQSLHISVPYLTLSSRPQLTASKLEMRRARSSPSLVLTFIKGSGPTRSLISVKRINSKRADPEPCLGAKNPVGSLLACDDPLCGPRGWPNLWCNCTLQGRVSVSTGRTGRSINGDLAVSPTSGDLLATPASGTRIEAYVLSMSSIRRTRAGQSGLPHTRVGTKDSLMRLRFDPMRTNHFPCILPESFNRSDISTSIMAGKSTLPAASLMGFCLNLRNSAPGGNDNVDSIRKRPWKGAFKEGYTCYSLCVTCAATSLHRREAPLGLQPVSDHILVYSYRKQQLVSYGTVPSPVAKSGLGRTTSMVCRGRDYTRSSSNRESCLLSRSQLHRHSQVSWTWTLPHLRGTFLGASEPVGYIVDRIRRANKPLLLKSYPKPIMAPAWPTRVAIAKLHMPICRRCSLSHCLLVNIAVKSSVSLGRMIGRICFVLILIVELAVPISRYKAYNSHLSVSWDNRITGLLGDLFLDRWDKTGERPTTFLARASNAVTAGIFSFRSTNDVIAVSDLKYHAMERHGTQTRPRKNSWGPDAPHRWLYSSRGVTGGSTIRLRAGSNIECHSSSMSAGSWSCLDGAFSCLPANSNRFSQVDPSCTATPPVAPKVASALLHELLIVMRRRGSMNRPYNICTLVTRGYRLALKLSGPSGSPSAAKEGTSQSGYVLHGEWCETHKIRMELRLHPIRLRDMSCLLYRISPARDMLVVSRLTASPAYQIVGANSTLLLRCTTPLRLLGLYLRYPQEYVKWESPWYYVPTEDCMRIAACGHKAITTVYPQCRIRCHIALGEGRPGGLHTPVWEFPTRSSKKEIEGQLAHSSRLVASIGRCSAGLSYAAALLAIFVCTNEQVGVYELPSLGVSPMILYLWSHDNIGGVLRYLSRLSSRTCYWFQPRDQVTASCHQVVYKALTVFLMREVLTASCAFIPARESRSYRVEFVPSDRNRLFHKCTSRDVTEYGITISTLSGNLQLQIEEISRYGAVPGELSPRAFTLIVETTGRQRLFSSEPRDTWCGRLQTLAMAVHLRNQRSGLVSTTRCDEFENSLEKRCAKNSLTINGYLKRRDGGRHRLHSCCREFAGHRQRQPCNSGAPAAQPINLYRNVRILTYTWQDRLSSYKPELVIHSTVYAYHLAALLHAARLEPQRRFRWSAKCPCER",
				toPeptide.apply(lines.get(0)));
	}

	@Test
	public void toPeptideQuiz() {
		System.out.println(toPeptide.apply("CCACGUACUGAAAUUAAC"));
		System.out.println(toPeptide.apply("CCCAGUACCGAGAUGAAU"));
		System.out.println(toPeptide.apply("CCCCGUACGGAGAUGAAA"));
		System.out.println(toPeptide.apply("CCCAGGACUGAGAUCAAU"));
	}
}