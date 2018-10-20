package text;

public class WhiteSpace extends SimpleTextPart {

	private static final String WHITESPACE_PATTERN = "\\p{Blank}";

	public WhiteSpace() {
		super();
	}

	public WhiteSpace(char symbol) {
		super(symbol);
	}

	public WhiteSpace(String symbol) {
		super(symbol);
	}

}
