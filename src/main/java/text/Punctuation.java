package text;

public class Punctuation extends SimpleTextPart {

	private static final String PUNCT_PATTERN = "\\p{Punct}";

	public Punctuation() {
		super();
	}

	public Punctuation(char symbol) {
		this(String.valueOf(symbol));
	}

	public Punctuation(String symbol) {
		if (symbol.matches(PUNCT_PATTERN)) {
			setSymbol(symbol.charAt(0));
		} else {
			throw new UnsupportedOperationException("Can not create Punctuation!");
		}
	}
}
