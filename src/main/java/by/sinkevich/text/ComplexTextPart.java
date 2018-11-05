package by.sinkevich.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
		return textParts.stream()
				.mapToInt(TextPart::length)
				.reduce((t1, t2) -> t1 + t2)
				.orElse(0);
	}

	@Override
	public boolean add(TextPart textPart) {
		return textParts.add(textPart);
	}

	@Override
	public List<Symbol> getSymbols() {
		return textParts.stream()
				.map(TextPart::getSymbols)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public List<Digit> getDigits() {
		return textParts.stream()
				.map(TextPart::getDigits)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public List<WhiteSpace> getWhitespaces() {
		return textParts.stream()
				.map(TextPart::getWhitespaces)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public List<Punctuation> getPunctuations() {
		return textParts.stream()
				.map(TextPart::getPunctuations)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public List<Word> getWords() {
		return textParts.stream()
				.map(TextPart::getWords)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public List<Number> getNumbers() {
		return textParts.stream()
				.map(TextPart::getNumbers)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public List<Signature> getSignatures() {
		return textParts.stream()
				.map(TextPart::getSignatures)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public List<Sentence> getSentences() {
		return textParts.stream()
				.map(TextPart::getSentences)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public int countWords() {
		return textParts.stream()
				.mapToInt(TextPart::countWords)
				.reduce((t1, t2) -> t1 + t2)
				.orElse(0);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		textParts.forEach(s -> result.append(s.toString()));
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
