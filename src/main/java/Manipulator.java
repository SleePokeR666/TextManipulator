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
		List<TextPart> result = new ArrayList<>(text.getSentences());
		result.sort(Comparator.comparingInt(TextPart::countWords));
		return result;
	}
}
