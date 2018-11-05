package by.sinkevich.text;

import java.util.ArrayList;
import java.util.List;

public class Punctuation extends SimpleTextPart {

	public Punctuation() {
		super();
	}

	public Punctuation(char symbol) {
		super(symbol);
	}

	public Punctuation(String symbol) {
		super(symbol);
	}

	@Override
	public List<Punctuation> getPunctuations() {
		List<Punctuation> punctuations = new ArrayList<>();
		punctuations.add(this);
		return punctuations;
	}
}
