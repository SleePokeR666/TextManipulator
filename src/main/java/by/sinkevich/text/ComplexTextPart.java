package by.sinkevich.text;

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
	public boolean add(TextPart textPart) {
		return textParts.add(textPart);
	}

	@Override
	public List<Symbol> getSymbols() {
		List<Symbol> symbols = new ArrayList<>();
		for (TextPart textPart : textParts) {
			List<Symbol> temp = textPart.getSymbols();
			if (temp != null) {
				symbols.addAll(temp);
			}
		}
		return symbols;
	}

	@Override
	public List<Digit> getDigits() {
		List<Digit> digits = new ArrayList<>();
		for (TextPart textPart : textParts) {
			List<Digit> temp = textPart.getDigits();
			if (temp != null) {
				digits.addAll(temp);
			}
		}
		return digits;
	}

	@Override
	public List<WhiteSpace> getWhitespaces() {
		List<WhiteSpace> whiteSpaces = new ArrayList<>();
		for (TextPart textPart : textParts) {
			List<WhiteSpace> temp = textPart.getWhitespaces();
			if (temp != null) {
				whiteSpaces.addAll(temp);
			}
		}
		return whiteSpaces;
	}

	@Override
	public List<Punctuation> getPunctuations() {
		List<Punctuation> punctuations = new ArrayList<>();
		for (TextPart textPart : textParts) {
			List<Punctuation> temp = textPart.getPunctuations();
			if (temp != null) {
				punctuations.addAll(temp);
			}
		}
		return punctuations;
	}

	@Override
	public List<Word> getWords() {
		List<Word> words = new ArrayList<>();
		for (TextPart textPart : textParts) {
			List<Word> temp = textPart.getWords();
			if (temp != null) {
				words.addAll(temp);
			}
		}
		return words;
	}

	@Override
	public List<Number> getNumbers() {
		List<Number> numbers = new ArrayList<>();
		for (TextPart textPart : textParts) {
			List<Number> temp = textPart.getNumbers();
			if (temp != null) {
				numbers.addAll(temp);
			}
		}
		return numbers;
	}

	@Override
	public List<Signature> getSignatures() {
		List<Signature> signatures = new ArrayList<>();
		for (TextPart textPart : textParts) {
			List<Signature> temp = textPart.getSignatures();
			if (temp != null) {
				signatures.addAll(temp);
			}
		}
		return signatures;
	}

	@Override
	public List<Sentence> getSentences() {
		List<Sentence> sentences = new ArrayList<>();
		for (TextPart textPart : textParts) {
			List<Sentence> temp = textPart.getSentences();
			if (temp != null) {
				sentences.addAll(temp);
			}
		}
		return sentences;
	}

	@Override
	public int countWords() {
		int wordsNumber = 0;
		for (TextPart textPart : textParts) {
			wordsNumber += textPart.countWords();
		}
		return wordsNumber;
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
		if (!(o instanceof ComplexTextPart)) return false;
		ComplexTextPart that = (ComplexTextPart) o;
		return getTextParts().equals(that.getTextParts());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTextParts());
	}

	@Override
	public List<TextPart> getTextParts() {
		return textParts;
	}

}
