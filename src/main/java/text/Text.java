package text;

import java.util.ArrayList;
import java.util.List;

public class Text extends ComplexTextPart {

	public Text() {
		super();
	}

	public Text(List<TextPart> textParts) {
		super(textParts);
	}

	@Override
	public List<Paragraph> getParagraphs() {
		List<Paragraph> result = new ArrayList<>();
		for (TextPart textPart : getTextParts()) {
			List<Paragraph> paragraphs = textPart.getParagraphs();
			if (paragraphs != null) {
				result.addAll(paragraphs);
			}
		}
		return result;
	}
}
