package echo.runnable;


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
		ScriptReader sr = new ScriptReader(s);
		ScriptWriter sw = new ScriptWriter();
		Input2Output.input2output(sr, sw).run();
		assertEquals(sr.toList(), sw.toList());

	}

	public static void main(String[] args) {
		ScriptReader sr = new ScriptReader("wef \n");

		ScriptWriter sw = new ScriptWriter();

		Input2Output.input2output(sr, sw).run();
		System.out.println(sr.toList());
		System.out.println(sw.toList());

	}

}
