package text;

import java.util.List;

public interface CompositeTextPart extends TextPart {

	void add(TextPart textPart);

	void remove(TextPart textPart);

	List<TextPart> getTextParts();
}
