package by.sinkevich.util;

public enum ValidationPattern {

	SYMBOL("[A-Za-zА-я_-]"),
	DIGIT("\\d"),
	WHITESPACE("[\\h\\v]"),
	PUNCTUATION("[\\p{Punct}«»—]"),
	WORD("\\b[A-Za-zА-я]\\b|(?:\\b[A-Za-zА-я][A-Za-zА-я_-]*\\B[A-Za-zА-я]\\b)"),
	NUMBER("\\d*[.]?\\d*"),
	SENTENCE("[^!?.].*?(?:[!?.]\\h|[!?.):]$)|(?:.+)"),
	PARAGRAPH("\\V+");

	private String pattern;

	ValidationPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

}
