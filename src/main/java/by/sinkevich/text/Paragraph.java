package by.sinkevich.text;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends ComplexTextPart {

	public Paragraph() {
		super();
	}

	public Paragraph(List<TextPart> textParts) {
		super(textParts);
	}

	@Override
	public List<Paragraph> getParagraphs() {
		List<Paragraph> paragraphs = new ArrayList<>();
		paragraphs.add(this);
		return paragraphs;
	}
}
