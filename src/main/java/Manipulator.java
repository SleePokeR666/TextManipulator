import text.Text;
import text.TextPart;

import java.util.Comparator;
import java.util.List;

public class Manipulator implements TextManipulator {

	private static final Parser PARSER = Parser.getInstance();
	private Text text;

	public Manipulator(String text) {
		parse(text);
	}

	@Override
	public void parse(String text) {
		this.text = PARSER.parseText(text);
	}

	@Override
	public void sortSentencesByWordsNumber() {
		List<TextPart> sentences = text.getAllSentences();

		sentences.sort(new Comparator<TextPart>() {
			@Override
			public int compare(TextPart o1, TextPart o2) {
				return o1.countWords() - o2.countWords();
			}
		});
	}
}
