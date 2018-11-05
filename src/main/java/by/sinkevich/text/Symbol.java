package by.sinkevich.text;

import java.util.ArrayList;
import java.util.List;

public class Symbol extends SimpleTextPart {

	public Symbol() {
		super();
	}

	public Symbol(char symbol) {
		super(symbol);
	}

	public Symbol(String symbol) {
		super(symbol);
	}

	@Override
	public List<Symbol> getSymbols() {
		List<Symbol> symbols = new ArrayList<>();
		symbols.add(this);
		return symbols;
	}
}