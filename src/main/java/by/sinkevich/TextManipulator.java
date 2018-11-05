package by.sinkevich;

import by.sinkevich.text.Number;
import by.sinkevich.text.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TextManipulator {

	Symbol parseSymbol(char symbol);

	Digit parseDigit(char digit);

	WhiteSpace parseWhitespace(char whitespace);

	Punctuation parsePunctuation(char punctuation);

	Word parseWord(String word);

	Number parseNumber(String number);

	Sentence parseSentence(String sentence);

	Paragraph parseParagraph(String paragraph);

	Text parseText(String text);

	Text parse(InputStream stream) throws IOException;

	List<TextPart> sortSentencesByWordsNumber(Text text);

}
