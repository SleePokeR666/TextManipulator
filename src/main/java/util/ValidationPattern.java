package util;

public enum ValidationPattern {

	SYMBOL("[A-Za-zА-я_-]"),
	DIGIT("\\d"),
	WHITESPACE("\\p{Space}"),
	PUNCTUATION("[\\p{Punct}«»—]"),
	WORD("[A-Za-zА-я]|(?:[A-Za-zА-я][A-Za-zА-я_-]*\\B[A-Za-zА-я])"),
	NUMBER("\\d*[.]?\\d*"),
	SIGNATURE("[\\w&&[^0-9_]]\\w*"),
	SENTENCE("[^\\p{Space}:!?.].*?[:!?.]"),
	PARAGRAPH("\\V+"),
	TEXT(""),
	LEXEME("\\w+"),
	LISTING("");

	private String pattern;

	ValidationPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

}
