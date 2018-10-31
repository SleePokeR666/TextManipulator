import text.Sentence;
import text.Text;
import text.TextPart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Manipulator implements TextManipulator {

	private static final Parser PARSER = Parser.getInstance();

	public Manipulator() {

	}

	@Override
	public Text parse(String text) {
		return PARSER.parseText(text);
	}

	@Override
	public List<TextPart> sortSentencesByWordsNumber(Text text) {
		List<Sentence> sentences = text.getSentences();
		List<TextPart> result = new ArrayList<>(sentences);

		result.sort(new Comparator<TextPart>() {
			@Override
			public int compare(TextPart o1, TextPart o2) {
				return o1.countWords() - o2.countWords();
			}
		});

		return result;
	}
}
