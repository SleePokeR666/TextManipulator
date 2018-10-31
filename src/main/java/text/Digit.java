package text;

import java.util.ArrayList;
import java.util.List;

public class Digit extends SimpleTextPart {

	public Digit() {
		super();
	}

	public Digit(char symbol) {
		super(symbol);
	}

	public Digit(String symbol) {
		super(symbol);
	}

	@Override
	public List<Digit> getDigits() {
		List<Digit> digits = new ArrayList<>();
		digits.add(this);
		return digits;
	}
}
