package text;

public class Symbol extends SimpleTextPart {

	private static final String SYMBOL_PATTERN = "\\w";

	public Symbol() {
		super();
	}

	public Symbol(char symbol) {
		this(String.valueOf(symbol));
	}

	public Symbol(String symbol) {
		if (symbol.matches(SYMBOL_PATTERN)) {
			setSymbol(symbol.charAt(0));
		} else {
			throw new UnsupportedOperationException("Can not create Symbol!");
		}
	}
}