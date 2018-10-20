package text;

import java.util.Objects;

public abstract class SimpleTextPart implements TextPart {

	private char symbol;

	SimpleTextPart() {
	}

	SimpleTextPart(char symbol) {
		this.symbol = symbol;
	}

	SimpleTextPart(String symbol) {
		this.symbol = symbol.charAt(0);
	}

	@Override
	public int length() {
		return 1;
	}

	@Override
	public String toString() {
		return String.valueOf(symbol);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SimpleTextPart that = (SimpleTextPart) o;
		return symbol == that.symbol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol);
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
