package text;

public class WhiteSpace extends SimpleTextPart {

	static {
		symbolPattern = "\\p{Blank}";
	}

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
