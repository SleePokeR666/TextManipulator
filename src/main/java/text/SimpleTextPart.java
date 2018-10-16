package text;

import java.util.Objects;

public abstract class SimpleTextPart implements TextPart {

	private char symbol;

	SimpleTextPart() {
	}

	public void add(TextPart textPart) {
		throw new UnsupportedOperationException();
	}

	public void remove(TextPart textPart) {
		throw new UnsupportedOperationException();
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
