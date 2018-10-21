package util;

public enum ValidationPattern {

	SYMBOL("\\w"),
	WHITESPACE("\\p{Blank}"),
	PUNCTUATION("\\p{Punct}"),
	WORD("(\\b[A-Za-zА-Яа-я]\\b)|(\\b[A-Za-zА-Яа-я]\\B[A-Za-zА-Яа-я_-]*\\B[A-Za-zА-Яа-я]\\b)"),
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
