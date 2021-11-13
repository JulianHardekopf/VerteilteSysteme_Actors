package echo.actor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import actor.Actor;
import actor.Writer;
import inout.ScriptReader;
import inout.ScriptWriter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public  class Input2OutputTest {

	@ParameterizedTest
	@CsvSource({"'Hallo'",
							"'HalloHallo'"})
	public void test(String s) throws InterruptedException {
		ScriptReader scriptReader = new ScriptReader(s);
		ScriptWriter scriptWriter = new ScriptWriter();
		Writer w = new Writer(s, Actor.Type.SERIAL, scriptReader, scriptWriter);
		w.start();

		assertEquals(scriptReader.toList(), scriptWriter.toList());

  }
}
