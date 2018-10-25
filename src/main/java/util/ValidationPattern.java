package util;

public enum ValidationPattern {

	SYMBOL("[A-Za-zА-я_-]"),
	DIGIT("\\d"),
	WHITESPACE("\\p{Space}"),
	PUNCTUATION("\\p{Punct}"),
	WORD("[A-Za-zА-я]|(?:[A-Za-zА-я][A-Za-zА-я_-]*\\B[A-Za-zА-я])"),
	NUMBER("\\d*[.]?\\d*"),
	SENTENCE("[^\\p{Punct}\\p{Space}].*[!?.]"),
	PARAGRAPH(""),
	TEXT(""),
	LEXEME(""),
	LISTING("");

	private String pattern;

	ValidationPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

}
