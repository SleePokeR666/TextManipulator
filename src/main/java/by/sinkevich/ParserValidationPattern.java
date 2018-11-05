package by.sinkevich;

import by.sinkevich.text.Number;
import by.sinkevich.text.*;
import by.sinkevich.util.ValidationPattern;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ParserValidationPattern {

	private final static Logger LOG = LogManager.getLogger();
	private static ParserValidationPattern instance;

	private ParserValidationPattern() {

	}

	static ParserValidationPattern getInstance() {
		if (instance == null) {
			instance = new ParserValidationPattern();
		}
		return instance;
	}

	Symbol parseSymbol(char symbol) {
		checkTextPart(String.valueOf(symbol), ValidationPattern.SYMBOL);
		return new Symbol(symbol);
	}

	Digit parseDigit(char digit) {
		checkTextPart(String.valueOf(digit), ValidationPattern.DIGIT);
		return new Digit(digit);
	}

	WhiteSpace parseWhitespace(char whitespace) {
		checkTextPart(String.valueOf(whitespace), ValidationPattern.WHITESPACE);
		return new WhiteSpace(whitespace);
	}

	Punctuation parsePunctuation(char punctuation) {
		checkTextPart(String.valueOf(punctuation), ValidationPattern.PUNCTUATION);
		return new Punctuation(punctuation);
	}

	Word parseWord(String word) {
		checkTextPart(word, ValidationPattern.WORD);
		Word result = new Word();

		for (int i = 0; i < word.length(); i++) {
			char current = word.charAt(i);
			result.add(parseSymbol(current));
		}

		return result;
	}

	Number parseNumber(String number) {
		checkTextPart(number, ValidationPattern.NUMBER);
		Number result = new Number();

		for (int i = 0; i < number.length(); i++) {
			char current = number.charAt(i);
			if (current == '.') {
				result.add(parsePunctuation(current));
			} else {
				result.add(parseDigit(current));
			}
		}

		return result;
	}

	Sentence parseSentence(String sentence) {
		checkTextPart(sentence, ValidationPattern.SENTENCE);
		Sentence result = new Sentence();
		String regex = constructSentenceRegex();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sentence);

		while (matcher.find()) {
			if (matcher.group(1) != null && !matcher.group(1).isEmpty()) {
				result.add(parseWord(matcher.group(1)));
			} else if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
				result.add(parseNumber(matcher.group(2)));
			}
			if (matcher.group(3) != null && !matcher.group(3).isEmpty()) {
				fillDelimitersGroup(matcher.group(3), result);
			}
		}

		return result;
	}

	private String constructSentenceRegex() {
		String regexWord = ValidationPattern.WORD.getPattern();
		String regexNumber = ValidationPattern.NUMBER.getPattern();
		String regexWhitespace = ValidationPattern.WHITESPACE.getPattern();
		String regexPunctuation = ValidationPattern.PUNCTUATION.getPattern();

		String delimitersGroup = String.format("[%s%s]*", regexWhitespace, regexPunctuation);
		return String.format("(?:(%s)|(%s))(%s)", regexWord, regexNumber, delimitersGroup);
	}

	private void fillDelimitersGroup(String delimiters, Sentence sentence) {
		for (int i = 0; i < delimiters.length(); i++) {
			char current = delimiters.charAt(i);
			if (current == ' ') {
				sentence.add(parseWhitespace(current));
			} else {
				sentence.add(parsePunctuation(current));
			}
		}
	}

	Paragraph parseParagraph(String paragraph) {
		checkTextPart(paragraph, ValidationPattern.PARAGRAPH);
		Paragraph result = new Paragraph();
		String regexSentence = ValidationPattern.SENTENCE.getPattern();
		String regex = String.format("(\\h*)(%s)", regexSentence);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(paragraph);

		while (matcher.find()) {
			if (!matcher.group(1).isEmpty()) {
				String whitespace = matcher.group(1);
				for (int i = 0; i < whitespace.length(); i++) {
					char current = whitespace.charAt(i);
					result.add(parseWhitespace(current));
				}
			}
			result.add(parseSentence(matcher.group(2)));
		}

		return result;
	}

	Text parseText(String text) {
		Text result = new Text();
		String regexParagraph = ValidationPattern.PARAGRAPH.getPattern();
		String regex = String.format("(%s)(\\R*)", regexParagraph);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String paragraph = matcher.group(1);
			result.add(parseParagraph(paragraph));
			if (!matcher.group(2).isEmpty()) {
				String whitespace = matcher.group(2);
				for (int i = 0; i < whitespace.length(); i++) {
					char current = whitespace.charAt(i);
					result.add(parseWhitespace(current));
				}
			}
		}

		return result;
	}

	private void checkTextPart(String textPart, ValidationPattern pattern) {
		if (!textPart.matches(pattern.getPattern())) {
			String message = String.format("Argument value: %s doesn't match to %s pattern: %s",
					textPart, pattern, pattern.getPattern());

			LOG.error(message);
			throw new IllegalArgumentException(message);
		}
	}

	Text parse(InputStream stream) throws IOException {
		String result = IOUtils.toString(stream, Charset.forName("UTF-8"));
		return parseText(result);
	}

}
