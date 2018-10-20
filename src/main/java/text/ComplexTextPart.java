package text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class ComplexTextPart implements CompositeTextPart {

	private List<TextPart> textParts;

	ComplexTextPart() {
		textParts = new ArrayList<>();
	}

	ComplexTextPart(List<TextPart> textParts) {
		this.textParts = textParts;
	}

	@Override
	public int length() {
		int length = 0;
		for (TextPart textPart : textParts) {
			length += textPart.length();
		}
		return length;
	}

	@Override
	public void add(TextPart textPart) {
		textParts.add(textPart);
	}

	@Override
	public void remove(TextPart textPart) {
		textParts.remove(textPart);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (TextPart textPart : textParts) {
			result.append(textPart.toString());
		}
		return result.toString();
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

	List<TextPart> getTextParts() {
		return textParts;
	}

	void setTextParts(List<TextPart> textParts) {
		this.textParts = textParts;
	}

}
