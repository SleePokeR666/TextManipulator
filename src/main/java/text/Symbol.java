package text;

public class Symbol extends SimpleTextPart {

	static {
		symbolPattern = "\\w";
	}

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