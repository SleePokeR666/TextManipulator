import text.*;
import text.Number;
import util.ValidationPattern;

import java.util.ArrayList;
import java.util.List;
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

	public CompositeTextPart parseWord(String word) {
		checkTextPart(word, ValidationPattern.WORD);
		return new Word(word);
	}

	public CompositeTextPart parseNumber(String number) {
		checkTextPart(number, ValidationPattern.NUMBER);
		Number result = new Number();
		for (int i = 0; i < number.length(); i++) {
			char current = number.charAt(i);
			if (current == '.') {
				result.add(new Punctuation(current));
			} else {
				result.add(new Digit(current));
			}
		}
		return result;
	}

	public CompositeTextPart parseSentence(String sentence) {
		checkTextPart(sentence, ValidationPattern.SENTENCE);
		List<TextPart> textParts = new ArrayList<>();
		String regexWordGroup = "(" + ValidationPattern.WORD.getPattern() + ")";
		String regexAfterWordGroup = "([" + ValidationPattern.WHITESPACE.getPattern() +
				ValidationPattern.PUNCTUATION.getPattern() + "]+)";
		String regex = regexWordGroup + regexAfterWordGroup;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sentence);
		while (matcher.find()) {
			textParts.add(new Word(matcher.group(1)));
			String afterWord = matcher.group(2);
			for (int i = 0; i < afterWord.length(); i++) {
				if (afterWord.charAt(i) == ' ') {
					textParts.add(new WhiteSpace(afterWord.charAt(i)));
				} else {
					textParts.add(new Punctuation(afterWord.charAt(i)));
				}
			}
		}
		return new Sentence(textParts);
	}

	private void checkTextPart(String textPart, ValidationPattern pattern) {
		if (!textPart.matches(pattern.getPattern())) {
			String message = String.format("Argument value: %s doesn't match to %s pattern: %s",
					textPart, pattern, pattern.getPattern());
			throw new IllegalArgumentException(message);
		}
	}

}
