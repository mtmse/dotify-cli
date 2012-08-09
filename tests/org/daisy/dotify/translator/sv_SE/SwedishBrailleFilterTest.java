package org.daisy.dotify.translator.sv_SE;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.daisy.dotify.text.FilterLocale;
import org.junit.Test;

public class SwedishBrailleFilterTest {
	private final SwedishBrailleFilter filter;
	
	public SwedishBrailleFilterTest() {
		filter = new SwedishBrailleFilter();
		filter.setLocale(FilterLocale.parse("sv-se"));
	}
	// 1.2 - Numbers	
	@Test
	public void testSwedishFilter_Numbers() {
		assertEquals("⠼⠃⠚⠚⠊", filter.filter("2009"));
	}
	// 2.1 - Punctuation
	@Test
	public void testSwedishFilter_Punctuation_ex1() {
		assertEquals("⠠⠓⠕⠝ ⠅⠪⠏⠞⠑ ⠎⠍⠪⠗⠂ ⠞⠑ ⠕⠉⠓ ⠕⠎⠞⠄", filter.filter("Hon köpte smör, te och ost."));
	}
	public void testSwedishFilter_Punctuation_ex2() {
		assertEquals("⠠⠅⠕⠍⠍⠑⠗ ⠙⠥⠢", filter.filter("Kommer du?"));
	}
	public void testSwedishFilter_Punctuation_ex3() {
		assertEquals("⠠⠓⠪⠗ ⠥⠏⠏⠖", filter.filter("Hör upp!"));
	}
	public void testSwedishFilter_Punctuation_ex4() {
		assertEquals("⠠⠓⠕⠝ ⠎⠁⠒ ⠠⠠⠙⠝⠒⠎ ⠗⠑⠙⠁⠅⠞⠊⠕⠝ ⠜⠗ ⠎⠞⠕⠗⠄", filter.filter("Hon sa: DN:s redaktion är stor."));
	}
	public void testSwedishFilter_Punctuation_ex5() {
		assertEquals("⠠⠎⠅⠊⠇⠇⠝⠁⠙⠑⠝ ⠍⠑⠇⠇⠁⠝ ⠁⠗⠃⠑⠞⠎- ⠕⠉⠓ ⠧⠊⠇⠕⠙⠁⠛⠁⠗ ⠃⠇⠑⠧ ⠍⠊⠝⠙⠗⠑ ⠎⠅⠁⠗⠏⠆ ⠓⠕⠝ ⠅⠥⠝⠙⠑ ⠞⠊⠇⠇⠡⠞⠁ ⠎⠊⠛ ⠧⠊⠇⠕⠙⠁⠛⠁⠗ ⠍⠊⠞⠞ ⠊ ⠧⠑⠉⠅⠁⠝⠄", filter.filter("Skillnaden mellan arbets- och vilodagar blev mindre skarp; hon kunde tillåta sig vilodagar mitt i veckan."));
	}
	public void testSwedishFilter_Punctuation_ex6() {
		assertEquals("⠠⠍⠌⠠⠎ ⠠⠅⠗⠕⠝⠁⠝", filter.filter("M/S Kronan"));
	}
	public void testSwedishFilter_Punctuation_ex7() {
		assertEquals("⠼⠚⠂⠑⠑ ⠇⠊⠞⠑⠗⠌⠍⠊⠇", filter.filter("0,55 liter/mil"));
	}
	public void testSwedishFilter_Punctuation_ex8() {
		assertEquals("⠍⠡⠝⠁⠙⠎⠎⠅⠊⠋⠞⠑⠞ ⠁⠏⠗⠊⠇⠌⠍⠁⠚", filter.filter("månadsskiftet april/maj"));
	}
	public void testSwedishFilter_Punctuation_ex9() {
		assertEquals("⠰⠠⠧⠊⠇⠇ ⠙⠥ ⠇⠑⠅⠁⠢⠰", filter.filter("\"Vill du leka?\""));
	}
	public void testSwedishFilter_Punctuation_ex10() {
		assertEquals("⠠⠙⠑⠞ ⠧⠁⠗ ⠠⠊⠗⠊⠎⠐ ⠃⠇⠕⠍⠍⠕⠗⠄", filter.filter("Det var Iris' blommor."));
	}
	public void testSwedishFilter_Punctuation_ex11() {
		assertEquals("⠰⠠⠧⠁⠙ ⠃⠑⠞⠽⠙⠑⠗ ⠐⠁⠃⠎⠞⠗⠥⠎⠐⠢⠰ ⠋⠗⠡⠛⠁⠙⠑ ⠓⠁⠝⠄", filter.filter("\"Vad betyder 'abstrus'?\" frågade han."));
	}
	// 2.2 - Dashes
	@Test
	public void testSwedishFilter_Dashes_ex1() {
		assertEquals("⠠⠁⠝⠝⠑-⠠⠍⠁⠗⠊⠑ ⠓⠁⠗ ⠛⠥⠇- ⠕⠉⠓ ⠧⠊⠞⠗⠁⠝⠙⠊⠛ ⠅⠚⠕⠇⠄", filter.filter("Anne-Marie har gul- och vitrandig kjol."));
	}
	public void testSwedishFilter_Dashes_ex2() {
		assertEquals("⠠⠑⠞⠞ ⠋⠑⠃⠗⠊⠇⠞ ⠎⠽⠎⠎⠇⠁⠝⠙⠑ ⠍⠑⠙ ⠤⠤ ⠊⠝⠛⠑⠝⠞⠊⠝⠛ ⠁⠇⠇⠎⠄", filter.filter("Ett febrilt sysslande med \u2013 ingenting alls."));
	}
	public void testSwedishFilter_Dashes_ex3() {
		assertEquals("⠤⠤ ⠠⠧⠁⠙ ⠓⠑⠞⠑⠗ ⠓⠥⠝⠙⠑⠝⠢", filter.filter("\u2013 Vad heter hunden?"));
	}
	public void testSwedishFilter_Dashes_ex4() {
		assertEquals("⠠⠓⠁⠝ ⠞⠕⠛ ⠞⠡⠛⠑⠞ ⠠⠎⠞⠕⠉⠅⠓⠕⠇⠍⠤⠤⠠⠛⠪⠞⠑⠃⠕⠗⠛⠄", filter.filter("Han tog tåget Stockholm\u2013Göteborg."));
	}
	// 2.3.1 - Parentheses	
	@Test
	public void testSwedishFilter_Parentheses_ex1() {
		assertEquals("⠠⠎⠽⠝⠎⠅⠁⠙⠁⠙⠑⠎ ⠠⠗⠊⠅⠎⠋⠪⠗⠃⠥⠝⠙ ⠦⠠⠠⠎⠗⠋⠴", filter.filter("Synskadades Riksförbund (SRF)"));
	}
	public void testSwedishFilter_Parentheses_ex2() {
		assertEquals("⠠⠗⠁⠏⠏⠕⠗⠞⠑⠗ ⠁⠴ ⠋⠗⠡⠝ ⠋⠪⠗⠃⠥⠝⠙⠎⠍⠪⠞⠑⠞ ⠃⠴ ⠅⠁⠎⠎⠁⠜⠗⠑⠝⠙⠑⠝", filter.filter("Rapporter a) från förbundsmötet b) kassaärenden"));
	}
	// 2.3.2 - Brackets	
	@Test
	public void testSwedishFilter_Brackets_ex1() {
		assertEquals("⠠⠅⠗⠁⠧⠑⠞ ⠓⠁⠗ ⠎⠞⠜⠇⠇⠞⠎ ⠋⠗⠡⠝ ⠕⠇⠊⠅⠁ ⠛⠗⠥⠏⠏⠑⠗ ⠦⠃⠇⠄⠁⠄ ⠷⠓⠪⠛⠎⠅⠕⠇⠑⠾⠎⠞⠥⠙⠑⠗⠁⠝⠙⠑ ⠕⠉⠓ ⠙⠑⠇⠞⠊⠙⠎⠁⠗⠃⠑⠞⠁⠝⠙⠑⠴ ⠍⠑⠝ ⠙⠑⠞ ⠓⠁⠗ ⠁⠇⠇⠞⠊⠙ ⠁⠧⠧⠊⠎⠁⠞⠎⠄", filter.filter("Kravet har ställts från olika grupper (bl.a. [högskole]studerande och deltidsarbetande) men det har alltid avvisats."));
	}
	public void testSwedishFilter_Brackets_ex2() {
		assertEquals("⠠⠗⠑⠙ ⠠⠏⠕⠗⠞ ⠷⠗⠜⠙ ⠏⠡⠗⠞⠾", filter.filter("Red Port [räd pårt]"));
	}
	// COULDDO 2.3.4
	// 2.3.5 - Braces	
	@Test
	public void testSwedishFilter_Braces() {
		assertEquals("⠠⠷⠼⠁⠂ ⠼⠉⠂ ⠼⠑⠠⠾ ⠥⠞⠇⠜⠎⠑⠎ ⠍⠜⠝⠛⠙⠑⠝ ⠁⠧ ⠞⠁⠇⠑⠝ ⠑⠞⠞⠂ ⠞⠗⠑ ⠕⠉⠓ ⠋⠑⠍⠄", filter.filter("{1, 3, 5} utläses mängden av talen ett, tre och fem."));
	}
	// 2.4.1 (ex 2) COULDDO ex 1, 3	
	@Test
	public void testSwedishFilter_2_4_1() {
		assertEquals("⠎⠑ ⠬ ⠼⠛⠤⠤⠼⠊", filter.filter("se § 7\u20139"));
	}
	// 2.4.2
	@Test
	public void testSwedishFilter_2_4_2() {
		assertEquals("⠠⠁⠇⠍⠟⠧⠊⠎⠞ ⠯ ⠠⠺⠊⠅⠎⠑⠇⠇", filter.filter("Almqvist & Wiksell"));
	}
	// 2.4.3 COULDDO ex 2, 3
	@Test
	public void testSwedishFilter_2_4_3() {
		assertEquals("⠠⠇⠁⠗⠎ ⠠⠛⠥⠎⠞⠁⠋⠎⠎⠕⠝ ⠔⠼⠁⠊⠉⠋", filter.filter("Lars Gustafsson *1936"));
	}
	// 2.4.4
	@Test
	public void testSwedishFilter_2_4_4() {
		assertEquals("⠞⠗⠽⠉⠅ ⠘⠼⠼⠃⠁⠘⠼", filter.filter("tryck #21#"));
	}
	// 2.4.5, 2.4.7
	@Test
	public void testSwedishFilter_2_4_5() {
		assertEquals("⠑⠍⠊⠇⠘⠤⠑⠍⠊⠇⠎⠎⠕⠝⠘⠷⠓⠕⠞⠍⠁⠊⠇⠄⠉⠕⠍", filter.filter("emil_emilsson@hotmail.com"));
	}
	// 2.4.6
	@Test
	public void testSwedishFilter_2_4_6() {
		assertEquals("⠠⠉⠒⠘⠌⠠⠠⠺⠊⠝⠙⠕⠺⠎⠘⠌⠎⠽⠎⠞⠑⠍⠘⠌⠇⠕⠛⠊⠝⠺⠼⠉⠁⠄⠙⠇⠇", filter.filter("C:\\WINDOWS\\system\\loginw31.dll"));
	}
	// 2.4.8
	@Test
	public void testSwedishFilter_2_4_8() {
		assertEquals("⠁⠇⠅⠸⠁", filter.filter("alk|a"));
	}
	// 2.4.9
	@Test
	public void testSwedishFilter_2_4_9() {
		assertEquals("⠎⠥⠃⠎⠞⠄ ⠘⠒⠝ ⠘⠒⠁⠗", filter.filter("subst. ~n ~ar"));
	}
	// 2.5
	@Test
	public void testSwedishFilter_2_5() {
		assertEquals("⠠⠏⠗⠊⠎⠑⠞ ⠧⠁⠗ ⠼⠑⠚⠚ ⠘⠑⠄", filter.filter("Priset var 500 €."));
	}
	// COULDDO 2.6, 2.7
	// 3.2 - Uppercase
	// 3.2.1
	@Test
	public void testSwedishFilter_3_2_1() {
		assertEquals("⠠⠓⠁⠝ ⠓⠑⠞⠑⠗ ⠠⠓⠁⠝⠎ ⠕⠉⠓ ⠃⠗⠕⠗ ⠓⠁⠝⠎ ⠓⠑⠞⠑⠗ ⠠⠃⠗⠕⠗⠄", filter.filter("Han heter Hans och bror hans heter Bror."));
	}
	// 3.2.2
	@Test
	public void testSwedishFilter_3_2_2_ex1() {
		assertEquals("⠠⠠⠎⠁⠧", filter.filter("SAV"));
	}
	@Test
	public void testSwedishFilter_3_2_2_ex2() {
		assertEquals("⠠⠠⠊⠅⠑⠁⠱⠎ ⠅⠁⠞⠁⠇⠕⠛", filter.filter("IKEAs katalog"));
	}
	@Test
	public void testSwedishFilter_3_2_2_ex3() {
		assertEquals("⠠⠎⠧⠑⠝⠎⠅⠁ ⠠⠠⠊⠎⠃⠝⠱-⠉⠑⠝⠞⠗⠁⠇⠑⠝", filter.filter("Svenska ISBN-centralen"));
	}
	// 3.2.3
	@Test
	public void testSwedishFilter_3_2_3_ex1() {
		assertEquals("⠠⠠⠠⠇⠕⠌⠞⠉⠕⠌⠎⠁⠉⠕⠱⠒⠎ ⠠⠃⠗⠽⠎⠎⠑⠇⠅⠕⠝⠞⠕⠗", filter.filter("LO/TCO/SACO:s Brysselkontor"));
	}
	@Test
	public void testSwedishFilter_3_2_3_ex2() {
		assertEquals("⠠⠠⠠⠎⠽⠝⠎⠅⠁⠙⠁⠙⠑⠎ ⠗⠊⠅⠎⠋⠪⠗⠃⠥⠝⠙⠱", filter.filter("SYNSKADADES RIKSFÖRBUND"));
	}
	@Test
	public void testSwedishFilter_3_2_3_ex3() {
		assertEquals("⠅⠠⠺⠓⠂ ⠠⠚⠜⠍⠠⠕", filter.filter("kWh, JämO"));
	}
	@Test
	public void testSwedishFilter_3_2_3_ex4() {
		assertEquals("⠠⠇⠪⠎⠑⠝⠕⠗⠙⠒ ⠕⠠⠧⠃⠠⠑⠠⠛⠚", filter.filter("Lösenord: oVbEGj"));
	}
	// 3.3.1	
	@Test
	public void testSwedishFilter_3_3() {
		assertEquals("⠠⠇⠪⠎⠑⠝⠕⠗⠙⠒ ⠇⠧⠃⠼⠑⠛⠱⠚", filter.filter("Lösenord: lvb57j"));
	}
	@Test
	public void testSwedishFilter_additional_ex1() throws FileNotFoundException {
		assertEquals("⠘⠦⠎⠞⠚⠜⠗⠝⠁⠘⠴ ⠘⠦⠃⠇⠊⠭⠞⠘⠴ ⠘⠦⠒⠦⠘⠴ ⠘⠦⠒⠴⠘⠴ ⠬⠕", filter.filter("\u066d \u2607 \u2639 \u263a \u00ba"));
	}
	public void testSwedishFilter_additional_ex2() throws FileNotFoundException {
		assertEquals("⠠⠝⠑⠛⠁⠞⠊⠧⠁ ⠞⠁⠇⠒ -⠼⠙⠑⠋⠙⠑", filter.filter("Negativa tal: -45645"));
	}
	public void testSwedishFilter_additional_ex3() throws FileNotFoundException {
		assertEquals("⠘⠦⠓⠚⠜⠗⠞⠑⠗⠘⠴", filter.filter("\u2665")); // hjärter
	}
}