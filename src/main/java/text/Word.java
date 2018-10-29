package text;

import java.util.ArrayList;
import java.util.List;

public class Word extends ComplexTextPart {

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
	public int countWords() {
		return 1;
	}
}
