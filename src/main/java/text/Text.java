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

	public List<TextPart> getAllParagraphs() {
		List<TextPart> paragraphs = new ArrayList<>();
		for (TextPart textPart : getTextParts()) {
			if (textPart instanceof Paragraph) {
				paragraphs.add(textPart);
			}
		}
		return paragraphs;
	}

	public List<TextPart> getAllSentences() {
		List<TextPart> paragraphs = getAllParagraphs();
		List<TextPart> sentences = new ArrayList<>();
		for (TextPart paragraph : paragraphs) {
			for (TextPart textPart : paragraph.getTextParts()) {
				if (textPart instanceof Sentence) {
					sentences.add(textPart);
				}
			}
		}
		return sentences;
	}
}
