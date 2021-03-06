package by.sinkevich;

import by.sinkevich.text.Number;
import by.sinkevich.text.*;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class TestTextManipulatorParser {

	private TextManipulatorParser manipulator = new TextManipulatorParser();

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
		File file = new File("src/test/resources/parseSentenceTest.txt");
		String[] sentences = readFile(file).split("\n");
		return new Object[][]{{sentences[0]}, {sentences[1]}, {sentences[2]}};
	}

	private static String readFile(File file) {
		String result = "";
		try {
			result = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			System.err.println("Ошибка чтения файла " + file.getPath());
			e.printStackTrace();
		}
		return result;
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
	public void parseParagraphTest() {
		File file = new File("src/test/resources/parseParagraphTest.txt");
		String expected = readFile(file);
		Paragraph tested = manipulator.parseParagraph(expected);
		assertEquals(tested.toString(), expected);
	}

	@Test(dependsOnGroups = {"SimpleTextPartTest", "ComplexTextPartTest"})
	public void parseTextTest() {
		String expectedText = "";
		File file = new File("src/test/resources/parseTestInput.txt");
		try {
			expectedText = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.err.println("Ошибка чтения файла " + file.getPath());
			e.printStackTrace();
		}

		Text testedText = manipulator.parseText(expectedText);
		assertEquals(testedText.toString(), expectedText);
	}

	@Test(dependsOnMethods = "parseTextTest", dependsOnGroups = {"SimpleTextPartTest", "ComplexTextPartTest"})
	public void sortSentencesByWordsNumberTest() {
		File file = new File("src/test/resources/sortSentencesByWordsNumberTestExpected.txt");
		File testedFile = new File("src/test/resources/sortSentencesByWordsNumberTestInput.txt");
		String expectedText = readFile(file);
		String testedText = readFile(testedFile);

		List<String> expectedSentences = Arrays.asList(expectedText.split("\n"));

		Text parsedText = manipulator.parseText(testedText);
		List<TextPart> testedSentences = manipulator.sortSentencesByWordsNumber(parsedText);

		assertEquals(testedSentences.stream()
				.map(TextPart::toString)
				.collect(Collectors.toList()), expectedSentences);
	}

	@Test(dependsOnGroups = {"SimpleTextPartTest", "ComplexTextPartTest"})
	public void parseTest() {
		File file = new File("src/test/resources/parseTestInput.txt");
		String expectedText = readFile(file);
		Text tested = new Text();

		try (FileInputStream fis = new FileInputStream(file)) {
			tested = manipulator.parse(fis);
		} catch (FileNotFoundException e) {
			System.err.println("Файл не найден " + file.getPath());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Ошибка чтения файла " + file.getPath());
			e.printStackTrace();
		}

		assertEquals(expectedText, tested.toString());
	}
}
