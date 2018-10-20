package util;

public enum ValidationPattern {

	SYMBOL("\\w"),
	WHITESPACE("\\p{Blank}"),
	PUNCTUATION("\\p{Punct}"),
	WORD("\\w+"),
	SENTENCE("[^!?.]+[!?.]"),
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
