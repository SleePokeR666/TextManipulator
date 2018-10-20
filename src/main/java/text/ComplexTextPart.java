package text;

import java.util.ArrayList;
import java.util.List;

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

	public List<TextPart> getTextParts() {
		return textParts;
	}

	public void setTextParts(List<TextPart> textParts) {
		this.textParts = textParts;
	}

}
