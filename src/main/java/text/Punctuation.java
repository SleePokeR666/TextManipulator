package text;

public class Punctuation extends SimpleTextPart {

	private static final String PUNCT_PATTERN = "\\p{Punct}";

	public Punctuation() {
		super();
	}

	public Punctuation(char symbol) {
		super(symbol);
	}

	public Punctuation(String symbol) {
		super(symbol);
	}
}
