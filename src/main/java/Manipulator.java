import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import text.*;
import text.Number;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Manipulator implements TextManipulator {

	private static final Logger LOG = LogManager.getLogger();
	private static final Parser PARSER = Parser.getInstance();

	public Manipulator() {

	}

	@Override
	public Symbol parseSymbol(char symbol) {
		return PARSER.parseSymbol(symbol);
	}

	@Override
	public Digit parseDigit(char digit) {
		return PARSER.parseDigit(digit);
	}

	@Override
	public WhiteSpace parseWhitespace(char whitespace) {
		return PARSER.parseWhitespace(whitespace);
	}

	@Override
	public Punctuation parsePunctuation(char punctuation) {
		return PARSER.parsePunctuation(punctuation);
	}

	@Override
	public Word parseWord(String word) {
		return PARSER.parseWord(word);
	}

	@Override
	public Number parseNumber(String number) {
		return PARSER.parseNumber(number);
	}

	@Override
	public Sentence parseSentence(String sentence) {
		return PARSER.parseSentence(sentence);
	}

	@Override
	public Paragraph parseParagraph(String paragraph) {
		return PARSER.parseParagraph(paragraph);
	}

	@Override
	public Text parse(String text) {
		Text result = PARSER.parseText(text);
		LOG.info(text);
		return result;
	}

	@Override
	public List<TextPart> sortSentencesByWordsNumber(Text text) {
		List<TextPart> result = new ArrayList<>(text.getSentences());
		result.sort(Comparator.comparingInt(TextPart::countWords));
		logList(result);
		return result;
	}

	private void logList(List<TextPart> text) {
		for (int i = 0; i < text.size(); i++) {
			String message = String.format("Item №%d ---> %s", i, text.get(i));
			LOG.info(message);
		}
	}
}
