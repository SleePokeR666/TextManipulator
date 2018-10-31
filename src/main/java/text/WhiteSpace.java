package text;

import java.util.ArrayList;
import java.util.List;

public class WhiteSpace extends SimpleTextPart {

	public WhiteSpace() {
		super();
	}

	public WhiteSpace(char symbol) {
		super(symbol);
	}

	public WhiteSpace(String symbol) {
		super(symbol);
	}

	@Override
	public List<WhiteSpace> getWhitespaces() {
		List<WhiteSpace> whiteSpaces = new ArrayList<>();
		whiteSpaces.add(this);
		return whiteSpaces;
	}
}
