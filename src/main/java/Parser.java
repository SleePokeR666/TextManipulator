import text.CompositeTextPart;
import text.Word;
import util.ValidationPattern;

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

	public CompositeTextPart parseWord(String word) throws Exception {
		String pattern = ValidationPattern.WORD.getPattern();
		if (word.matches(pattern)) {
			return new Word(word);
		} else {
			throw new Exception("Слово " + word + " не подходит под заданный паттерн!");
		}
	}
}
