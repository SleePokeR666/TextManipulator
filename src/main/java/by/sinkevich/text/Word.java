package by.sinkevich.text;

import java.util.ArrayList;
import java.util.List;

public class Word extends ComplexTextPart {

	public Word() {
		super();
	}

	public Word(List<TextPart> textParts) {
		super(textParts);
	}

	@Override
	public List<Word> getWords() {
		List<Word> words = new ArrayList<>();
		words.add(this);
		return words;
	}

	@Override
	public List<Number> getNumbers() {
		return null;
	}

	@Override
	public List<Signature> getSignatures() {
		return null;
	}

	@Override
	public List<Sentence> getSentences() {
		return null;
	}

	@Override
	public int countWords() {
		return 1;
	}
}
