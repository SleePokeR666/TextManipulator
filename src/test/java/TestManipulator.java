import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import text.Number;
import text.*;
import util.TestManipulatorData;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TestManipulator implements TestManipulatorData {

	private Manipulator manipulator = new Manipulator();

	@DataProvider
	public static Object[][] parseSymbolTestData() {
		return new Object[][]{{'A'}, {'z'}, {'А'}, {'я'}, {'_'}, {'-'}};
	}

	@DataProvider
	public static Object[][] parseWhitespaceTestData() {
		return new Object[][]{{' '}, {'\t'}, {'\n'}, {'\u0085'}};
	}

	@DataProvider
	public static Object[][] parsePunctuationTestData() {
		return new Object[][]{{','}, {'.'}, {'!'}, {'?'}, {':'}};
	}

	@DataProvider
	public static Object[][] parseWordTestData() {
		return new Object[][]{{"я"}, {"Слово"}, {"I"}, {"word"}, {"WORD"}};
	}

	@DataProvider
	public static Object[][] parseNumberTestData() {
		return new Object[][]{{"12345.67890"}, {"1234567890"}, {".0123456789"}};
	}

	@DataProvider
	public static Object[][] parseSentenceTestData() {
		return new Object[][]{{sentence1}, {sentence2}, {sentence3}};
	}

	@Test(dataProvider = "parseSymbolTestData", groups = "SimpleTextPartTest")
	public void parseSymbolTest(char expectedSymbol) {
		Symbol tested = manipulator.parseSymbol(expectedSymbol);
		assertEquals(tested.toString(), String.valueOf(expectedSymbol));
	}

	@Test(groups = "SimpleTextPartTest")
	public void parseDigitTest() {
		char expectedDigit = '6';
		Digit tested = manipulator.parseDigit(expectedDigit);
		assertEquals(tested.toString(), String.valueOf(expectedDigit));
	}

	@Test(dataProvider = "parseWhitespaceTestData", groups = "SimpleTextPartTest")
	public void parseWhitespaceTest(char expectedWhitespace) {
		WhiteSpace tested = manipulator.parseWhitespace(expectedWhitespace);
		assertEquals(tested.toString(), String.valueOf(expectedWhitespace));
	}

	@Test(dataProvider = "parsePunctuationTestData", groups = "SimpleTextPartTest")
	public void parsePunctuationTest(char expectedPunctuation) {
		Punctuation tested = manipulator.parsePunctuation(expectedPunctuation);
		assertEquals(tested.toString(), String.valueOf(expectedPunctuation));
	}

	@Test(dataProvider = "parseWordTestData", dependsOnGroups = "SimpleTextPartTest",
			groups = "ComplexTextPartTest")
	public void parseWordTest(String expectedWord) {
		Word tested = manipulator.parseWord(expectedWord);
		assertEquals(tested.toString(), expectedWord);
	}

	@Test(dataProvider = "parseNumberTestData", dependsOnGroups = "SimpleTextPartTest",
			groups = "ComplexTextPartTest")
	public void parseNumberTest(String expectedNumber) {
		Number tested = manipulator.parseNumber(expectedNumber);
		assertEquals(tested.toString(), expectedNumber);
	}

	@Test(dataProvider = "parseSentenceTestData", dependsOnGroups = "SimpleTextPartTest",
			dependsOnMethods = {"parseWordTest", "parseNumberTest"},
			groups = "ComplexTextPartTest")
	public void parseSentenceTest(String expectedSentence) {
		Sentence tested = manipulator.parseSentence(expectedSentence);
		assertEquals(tested.toString(), expectedSentence);
	}

	@Test(dependsOnGroups = "SimpleTextPartTest", dependsOnMethods = {"parseSentenceTest",
			"parseNumberTest", "parseWordTest"},
			groups = "ComplexTextPartTest")
	public void parseParagraph() {
		TextPart[] textParts = {
				manipulator.parseSentence(sentence1), manipulator.parseWhitespace(' '),
				manipulator.parseSentence(sentence3)};

		Paragraph expectedParagraph = new Paragraph(Arrays.asList(textParts));

		Paragraph tested = manipulator.parseParagraph(paragraph);
		assertEquals(tested.toString(), expectedParagraph.toString());
	}

	@Test(dependsOnGroups = {"SimpleTextPartTest", "ComplexTextPartTest"})
	public void parseTest() {
		String expectedText = text;
		Text testedText = manipulator.parse(expectedText);
		assertEquals(testedText.toString(), expectedText);
	}

	@Test(dependsOnMethods = "parseTest")
	public void sortSentencesByWordsNumberTest() {
		Text parsedText = manipulator.parse(text);
		manipulator.sortSentencesByWordsNumber(parsedText);
	}
}
