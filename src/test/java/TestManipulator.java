import org.testng.annotations.Test;
import text.CompositeTextPart;
import util.TestParserData;

import static org.testng.Assert.assertEquals;

public class TestManipulator implements TestParserData {

	private Manipulator manipulator = new Manipulator();

	@Test
	public void parseTest() {
		String expectedText = TestParserData.positiveText;
		CompositeTextPart testedText = manipulator.parse(expectedText);
		assertEquals(testedText.toString(), expectedText);
	}
}
