package text;

public class WhiteSpace extends SimpleTextPart {

	private static final String WHITESPACE_PATTERN = "\\p{Blank}";

	public WhiteSpace() {
		super();
	}

	public WhiteSpace(char symbol) {
		this(String.valueOf(symbol));
	}

	public WhiteSpace(String symbol) {
		if (symbol.matches(WHITESPACE_PATTERN)) {
			setSymbol(symbol.charAt(0));
		} else {
			throw new UnsupportedOperationException("Can not create WhiteSpace!");
		}
	}
}
