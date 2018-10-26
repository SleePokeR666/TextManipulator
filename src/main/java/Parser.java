import text.*;
import text.Number;
import util.ValidationPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	private static Parser instance;

	private Parser() {

	}

	public static Parser getInstance() {
		if (instance == null) {
			instance = new Parser();
		}
		return instance;
	}

	public TextPart parseSymbol(char symbol) {
		checkTextPart(String.valueOf(symbol), ValidationPattern.SYMBOL);
		return new Symbol(symbol);
	}

	public TextPart parseDigit(char digit) {
		checkTextPart(String.valueOf(digit), ValidationPattern.DIGIT);
		return new Digit(digit);
	}

	public TextPart parseWhitespace(char whitespace) {
		checkTextPart(String.valueOf(whitespace), ValidationPattern.WHITESPACE);
		return new WhiteSpace(whitespace);
	}

	public TextPart parsePunctuation(char punctuation) {
		checkTextPart(String.valueOf(punctuation), ValidationPattern.PUNCTUATION);
		return new Punctuation(punctuation);
	}

	public CompositeTextPart parseWord(String word) {
		checkTextPart(word, ValidationPattern.WORD);
		CompositeTextPart result = new Word();
		for (int i = 0; i < word.length(); i++) {
			char current = word.charAt(i);
			result.add(parseSymbol(current));
		}
		return result;
	}

	public CompositeTextPart parseNumber(String number) {
		checkTextPart(number, ValidationPattern.NUMBER);
		CompositeTextPart result = new Number();
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

	public CompositeTextPart parseSignature(String signature) {
		checkTextPart(signature, ValidationPattern.SIGNATURE);
		CompositeTextPart result = new Signature();
		for (int i = 0; i < signature.length(); i++) {
			char current = signature.charAt(i);
			if (String.valueOf(current).matches(ValidationPattern.DIGIT.getPattern())) {
				result.add(parseDigit(current));
			} else {
				result.add(parseSymbol(current));
			}
		}
		return result;
	}

	public CompositeTextPart parseSentence(String sentence) {
		checkTextPart(sentence, ValidationPattern.SENTENCE);
		CompositeTextPart result = new Sentence();
		String regex = constructSentenceRegex();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sentence);
		while (matcher.find()) {
			String wordOrNumber = matcher.group(1);
			if (wordOrNumber.matches(ValidationPattern.WORD.getPattern())) {
				result.add(parseWord(wordOrNumber));
			} else if (wordOrNumber.matches(ValidationPattern.NUMBER.getPattern())){
				result.add(parseNumber(wordOrNumber));
			} else {
				result.add(parseSignature(wordOrNumber));
			}

			String afterWord = matcher.group(2);
			for (int i = 0; i < afterWord.length(); i++) {
				char current = afterWord.charAt(i);
				if (current == ' ') {
					result.add(parseWhitespace(current));
				} else {
					result.add(parsePunctuation(current));
				}
			}
		}
		return result;
	}

	private String constructSentenceRegex() {
		StringBuilder regex = new StringBuilder();
		String regexWord = ValidationPattern.WORD.getPattern();
		String regexNumber = ValidationPattern.NUMBER.getPattern();
		String regexSignature = ValidationPattern.SIGNATURE.getPattern();

		regex.append("((?:").append(regexWord).append(")|");
		regex.append("(?:").append(regexNumber).append(")|");
		regex.append("(?:").append(regexSignature).append("))");
		regex.append("([\\p{Space}\\p{Punct}]+)");
		return regex.toString();
	}

	private void checkTextPart(String textPart, ValidationPattern pattern) {
		if (!textPart.matches(pattern.getPattern())) {
			String message = String.format("Argument value: %s doesn't match to %s pattern: %s",
					textPart, pattern, pattern.getPattern());
			throw new IllegalArgumentException(message);
		}
	}

}
