package by.sinkevich.text;

import java.util.ArrayList;
import java.util.List;

public class Sentence extends ComplexTextPart {

	public Sentence() {
		super();
	}

	public Sentence(List<TextPart> textParts) {
		super(textParts);
	}

	@Override
	public List<Sentence> getSentences() {
		List<Sentence> sentences = new ArrayList<>();
		sentences.add(this);
		return sentences;
	}
}
