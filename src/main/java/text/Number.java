package text;

import java.util.ArrayList;
import java.util.List;

public class Number extends ComplexTextPart {

	public Number() {
		super();
	}

	public Number(List<TextPart> textParts) {
		super(textParts);
	}

	@Override
	public List<Word> getWords() {
		return null;
	}

	@Override
	public List<Number> getNumbers() {
		List<Number> numbers = new ArrayList<>();
		numbers.add(this);
		return numbers;
	}

	@Override
	public List<Signature> getSignatures() {
		return null;
	}

	@Override
	public List<Sentence> getSentences() {
		return null;
	}
}
