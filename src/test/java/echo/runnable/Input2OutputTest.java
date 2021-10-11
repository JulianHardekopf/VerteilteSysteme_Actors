package echo.runnable;

import inout.Input;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import inout.ScriptReader;
import inout.ScriptWriter;



public  class Input2OutputTest {

	@ParameterizedTest
	@CsvSource({"'Hallo1\nHallo2'",
							"'HalloHallo\njaja'"})

	public void test(String s) {
    	assertEquals("bla","bla");

		assertEquals(new ScriptReader(s).toList(), Input2Output.input2output(new ScriptReader(s), new ScriptWriter()));
		//run.()
	}

}
