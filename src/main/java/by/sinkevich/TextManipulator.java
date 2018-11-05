package by.sinkevich;

import by.sinkevich.text.*;
import by.sinkevich.text.Number;

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

	List<TextPart> sortSentencesByWordsNumber(Text text);

}
