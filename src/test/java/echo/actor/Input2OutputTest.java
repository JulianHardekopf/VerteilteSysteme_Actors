package echo.actor;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

import actor.Actor;
import actor.Writer;
import echo.runnable.Input2Output;
import inout.ScriptReader;
import inout.ScriptWriter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static echo.runnable.Input2Output.input2output;

public  class Input2OutputTest {

	@ParameterizedTest
	@CsvSource({"'Hallo'",
							"'HalloHallo'"})
	public void test(String s) throws InterruptedException {
		ScriptReader scriptReader = new ScriptReader(s);
		ScriptWriter scriptWriter = new ScriptWriter();
		Writer w = new Writer(s, Actor.Type.SERIAL, scriptReader, scriptWriter);
		// Input to Output mit den Writer als Runnable ausführen
		input2output(w).run();
		// Damit der MainThread nicht sofort terminiert
		sleep(500);

		assertEquals(scriptReader.toList(), scriptWriter.toList());

  }
}
