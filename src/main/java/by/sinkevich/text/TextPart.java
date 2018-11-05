package by.sinkevich.text;

import java.util.List;

public interface TextPart {

	default int length() {
		return 1;
	}

	default List<TextPart> getTextParts() {
		return null;
	}

	default List<Symbol> getSymbols() {
		return null;
	}

	default List<Digit> getDigits() {
		return null;
	}

	default List<WhiteSpace> getWhitespaces() {
		return null;
	}

	default List<Punctuation> getPunctuations() {
		return null;
	}

	default List<Word> getWords() {
		return null;
	}

	default List<Number> getNumbers() {
		return null;
	}

	default List<Signature> getSignatures() {
		return null;
	}

	default List<Sentence> getSentences() {
		return null;
	}

	default List<Paragraph> getParagraphs() {
		return null;
	}

	default int countWords() {
		return 0;
	}

}