import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import text.Text;
import text.TextPart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Manipulator implements TextManipulator {

	private static final Logger LOG = LogManager.getLogger();
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
