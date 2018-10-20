package text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Word extends ComplexTextPart {

	private static final String wordPattern = "\\w+";

	public Word() {
		super();
	}

	public Word(String word) {
		List<TextPart> letters = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			letters.add(new Symbol(word.charAt(i)));
		}
		setTextParts(letters);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Word word = (Word) o;
		return getTextParts().equals(word.getTextParts());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTextParts());
	}

}
