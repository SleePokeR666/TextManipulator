package text;

public abstract class SimpleTextPart implements TextPart {

	static String symbolPattern;
	private char symbol;

	SimpleTextPart() {
	}

	SimpleTextPart(char symbol) {
		this.symbol = symbol;
	}

	SimpleTextPart(String symbol) {
		if (symbol.matches(symbolPattern)) {
			this.symbol = symbol.charAt(0);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public void add(TextPart textPart) {
		if (symbol == Character.MIN_VALUE && textPart instanceof SimpleTextPart) {
			symbol = ((SimpleTextPart) textPart).getSymbol();
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public void remove(TextPart textPart) {
		if (symbol != Character.MIN_VALUE && textPart instanceof SimpleTextPart) {
			if (symbol == ((SimpleTextPart) textPart).getSymbol()) {
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

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
