package util;

public enum ValidationPattern {

	SYMBOL("\\w"),
	WHITESPACE("\\p{Space}"),
	PUNCTUATION("\\p{Punct}"),
	WORD("[A-Za-zА-я]|(?:[A-Za-zА-я][A-Za-zА-я_-]*\\B[A-Za-zА-я])"),
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
