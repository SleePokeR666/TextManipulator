package text;

public class Symbol implements TextPart {

	private static final String SYMBOL_PATTERN = "\\w";
	private char symbol;

	public Symbol() {

	}

	public Symbol(char symbol) {
		this.symbol = symbol;
	}

	public Symbol(String symbol) {
		if (symbol.matches(SYMBOL_PATTERN)) {
			this.symbol = symbol.charAt(0);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public void add(TextPart textPart) {
		if (symbol == Character.MIN_VALUE && textPart instanceof Symbol) {
			symbol = ((Symbol) textPart).symbol;
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public void remove(TextPart textPart) {
		if (symbol != Character.MIN_VALUE && textPart instanceof Symbol) {
			if (symbol == ((Symbol) textPart).symbol) {
				symbol = Character.MIN_VALUE;
			} else {
				throw new UnsupportedOperationException();
			}
		} else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public String toString() {
		return String.valueOf(symbol);
	}
}