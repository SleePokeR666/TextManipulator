package text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Word implements TextPart {

	private static final String wordPattern = "\\w+";
	private List<TextPart> letters;

	public Word() {
		letters = new ArrayList<TextPart>();
	}

	public Word(String word) {
		letters = new ArrayList<TextPart>();
		if (word.matches(wordPattern)) {
			for (int i = 0; i < word.length(); i++) {
				letters.add(new Symbol(word.charAt(i)));
			}
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public void add(TextPart textPart) {
		letters.add(textPart);
	}

	public void remove(TextPart textPart) {
		letters.remove(textPart);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (TextPart letter : letters) {
			result.append(letter);
		}
		return result.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Word word = (Word) o;
		return letters.equals(word.letters);
	}

	@Override
	public int hashCode() {
		return Objects.hash(letters);
	}

	public List<TextPart> getLetters() {
		return letters;
	}

	public void setLetters(List<TextPart> letters) {
		this.letters = letters;
	}
}
