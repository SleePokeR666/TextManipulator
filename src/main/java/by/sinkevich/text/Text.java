package by.sinkevich.text;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Text extends ComplexTextPart {

	public Text() {
		super();
	}

	public Text(List<TextPart> textParts) {
		super(textParts);
	}

	@Override
	public List<Paragraph> getParagraphs() {
		return getTextParts().stream()
				.map(TextPart::getParagraphs)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}
}
