package text;

import java.util.List;

public interface TextPart {

	int length();

	List<TextPart> getTextParts();

	int countWords();

}