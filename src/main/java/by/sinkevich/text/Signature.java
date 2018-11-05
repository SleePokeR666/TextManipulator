package by.sinkevich.text;

import java.util.ArrayList;
import java.util.List;

public class Signature extends ComplexTextPart {

	public Signature() {
		super();
	}

	public Signature(List<TextPart> textParts) {
		super(textParts);
	}

	@Override
	public List<Word> getWords() {
		return null;
	}

	@Override
	public List<Number> getNumbers() {
		return null;
	}

	@Override
	public List<Signature> getSignatures() {
		List<Signature> signatures = new ArrayList<>();
		signatures.add(this);
		return signatures;
	}

	@Override
	public int countWords() {
		return 1;
	}

	@Override
	public List<Sentence> getSentences() {
		return null;
	}
}
