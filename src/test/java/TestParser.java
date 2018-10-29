import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import text.CompositeTextPart;
import util.TestParserData;

import static org.testng.Assert.assertEquals;

public class TestParser implements TestParserData {

	private Parser parser = Parser.getInstance();

	@DataProvider
	public static Object[][] parseWordData() {
		return new Object[][]{
				{"А"    }, {"Я"    }, {"П"    }, {"а"    }, {"я"    }, {"п"    },
				{"A"    }, {"Z"    }, {"Q"    }, {"a"    }, {"z"    }, {"q"    },
				{"СЛОВО"}, {"слово"}, {"WORD" }, {"word" }};
	}

	@DataProvider
	public static Object[][] parseWordDataException() {
		return new Object[][]{
				{""      }, {" "     }, {"?"     }, {"\n"    }, {"\t"    }, {"0"     },
				{"_"     }, {"-"     },
				{" word" }, {"word " }, {"wo rd" }, {"!word" }, {"word!" }, {"wo!rd" },
				{"\nword"}, {"word\n"}, {"wo\nrd"}, {"\tword"}, {"word\t"}, {"wo\trd"},
				{"0word" }, {"word9" }, {"wo5rd" }, {"_word" }, {"word_" }, {"-word" },
				{"word-" }};
	}

	@DataProvider
	public static Object[][] parseNumberData() {
		return new Object[][]{{"12345.67890"}, {"1234567890"}, {".0123456789"}};
	}

	@DataProvider
	public static Object[][] parseNumberDataException() {
		return new Object[][]{
				{".12345.67890"}, {"12345.67890."}, {".0123456789."}, {"012.3456.789"},
				{" 1234567890" }, {"1234567890 " }, {"012345 6789" }, {"a0123456789" },
				{"1234567890b" }, {"12345c67890" }, {";0123456789" }, {"a0123456789!"}};
	}

	@DataProvider
	public static Object[][] parseSentenceData() {
		return new Object[][] {
				{positiveSentence1}, {positiveSentence2}, {positiveSentence3},
				{positiveSentence4}, {positiveSentence5}, {positiveSentence6}};
	}

	@DataProvider
	public static Object[][] parseParagraphDataException() {
		return new Object[][]{{"\r\nParagraph"}, {"Paragraph\n\rParagraph"}, {"Paragraph\n\r"}};
	}

	@Test(dataProvider = "parseWordData")
	public void parseWordTest(String expectedWord) {
		CompositeTextPart testedWord = parser.parseWord(expectedWord);
		assertEquals(testedWord.toString(), expectedWord);
	}

	@Test(dataProvider = "parseWordDataException",
			expectedExceptions = IllegalArgumentException.class)
	public void parseWordTestException(String word) {
		parser.parseWord(word);
	}

	@Test(dataProvider = "parseNumberData")
	public void parseNumberTest(String expectedNumber) {
		CompositeTextPart testedNumber = parser.parseNumber(expectedNumber);
		assertEquals(testedNumber.toString(), expectedNumber);
	}

	@Test(dataProvider = "parseNumberDataException",
			expectedExceptions = IllegalArgumentException.class)
	public void parseNumberTestException(String number) {
		parser.parseNumber(number);
	}

	@Test(dataProvider = "parseSentenceData")
	public void parseSentenceTest(String expectedSentence) {
		CompositeTextPart testedSentence = parser.parseSentence(expectedSentence);
		assertEquals(testedSentence.toString(), expectedSentence);
	}

	@Test
	public void parseParagraphTest() {
		String expectedParagraph = TestParserData.positiveParagraph;
		CompositeTextPart testedParagraph = parser.parseParagraph(expectedParagraph);
		assertEquals(testedParagraph.toString(), expectedParagraph);
	}

	@Test(dataProvider = "parseParagraphDataException",
			expectedExceptions = IllegalArgumentException.class)
	public void parseParagraphTestException(String paragraph) {
		parser.parseParagraph(paragraph);
	}

	@Test
	public void parseTextTest() {
		String expectedText = TestParserData.positiveText;
		CompositeTextPart testedText = parser.parseText(expectedText);
		assertEquals(testedText.toString(), expectedText);
	}
}
