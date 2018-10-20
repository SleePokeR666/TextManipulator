package text;

public class Symbol extends SimpleTextPart {

	private static final String SYMBOL_PATTERN = "\\w";

	public Symbol() {
		super();
	}

	public Symbol(char symbol) {
		super(symbol);
	}

	public Symbol(String symbol) {
		super(symbol);
	}

}