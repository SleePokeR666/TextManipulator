package text;

public class Punctuation extends SimpleTextPart {

	static {
		symbolPattern = "\\p{Punct}";
	}

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
