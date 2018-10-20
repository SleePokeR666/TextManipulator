import text.*;
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
		if (!isTextPartValid(word, ValidationPattern.WORD)) {
			throw new IllegalArgumentException(String.format(
					"Слово %s не подходит под заданный паттерн!", word));
		}
		return new Word(word);
	}

	public CompositeTextPart parseSentence(String sentence) {
		if (!isTextPartValid(sentence, ValidationPattern.SENTENCE)) {
			throw new IllegalArgumentException(String.format(
					"Предложение %s не подходит под заданный паттерн!", sentence));
		}
		List<TextPart> textParts = new ArrayList<>();
		String regex = "([A-Za-zА-Яа-я-_]+)(\\p{Punct}*)(\\p{Blank}*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sentence);
		while (matcher.find()) {
			textParts.add(new Word(matcher.group(1)));
			if (!matcher.group(2).isEmpty()) {
				if (matcher.group(2).length() > 1) {
					for (int i = 0; i < matcher.group(2).length(); i++) {
						textParts.add(new Punctuation(matcher.group(2).charAt(i)));
					}
				} else {
					textParts.add(new Punctuation(matcher.group(2)));
				}
			}
			if (!matcher.group(3).isEmpty()) {
				textParts.add(new WhiteSpace(" "));
			}
		}
		return new Sentence(textParts);
	}

	private boolean isTextPartValid(String textPart, ValidationPattern pattern) {
		return textPart.matches(pattern.getPattern());
	}

}
