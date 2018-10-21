import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import text.CompositeTextPart;
import text.Word;

import static org.testng.Assert.assertEquals;

public class TestParser {

	private Parser parser = Parser.getInstance();

	@DataProvider
	public static Object[][] parseWordData() {
		return new Object[][]{{"Ж", new Word("Ж")}, {"Слово", new Word("Слово")},
				{"СЛОВО", new Word("СЛОВО")}, {"G", new Word("G")}, {"Word", new Word("Word")},
				{"WORD", new Word("WORD")}};
	}

	@DataProvider
	public static Object[][] parseWordDataException() {
		String[] words = {
				" word", "word ", "wo rd", ",word", "word;", "wo!rd", "_word",
				"word_", "-word", "word-", "", " ", "?", "\n", "\t",
				" Слово", "Слово ", "Сл во", ",Слово", "Слово;", "Сл!во", "_Слово",
				"Слово_", "-Слово", "Слово-", "Сword", "wordС", "wСлово", "Словоw"};
		Object[][] data = new Object[words.length][1];
		for (int i = 0; i < words.length; i++) {
			data[i][0] = words[i];
		}
		return data;
	}

	@Test(dataProvider = "parseWordData")
	public void parseWordTest(String word, Word expected) {
		CompositeTextPart tested = parser.parseWord(word);
		assertEquals(tested, expected);
	}

	@Test(dataProvider = "parseWordDataException",
			expectedExceptions = IllegalArgumentException.class)
	public void parseWordTestException(String word) {
		parser.parseWord(word);
	}

}
