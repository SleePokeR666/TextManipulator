import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import text.CompositeTextPart;

import static org.testng.Assert.assertEquals;

public class TestParser {

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
				{"word-" }
		};
	}

	@DataProvider
	public static Object[][] parseSentenceData() {
		String sentence = "Сложение (+), вычитание и унарный минус (-), умножение (*), деление (/) и присвоение (=) работают одинаково фактически во всех языках программирования.";
		return new Object[][] {{sentence}};
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

	@Test(dataProvider = "parseSentenceData")
	public void parseSentenceTest(String expectedSentence) {
		CompositeTextPart testedSentence = parser.parseSentence(expectedSentence);
		assertEquals(testedSentence.toString(), expectedSentence);
	}
}
