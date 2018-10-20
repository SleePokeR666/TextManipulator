package text;

public interface CompositeTextPart extends TextPart {

	void add(TextPart textPart);

	void remove(TextPart textPart);

}
