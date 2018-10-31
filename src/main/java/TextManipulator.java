import text.Text;
import text.TextPart;

import java.util.List;

public interface TextManipulator {

	Text parse(String text);

	List<TextPart> sortSentencesByWordsNumber(Text text);

}
