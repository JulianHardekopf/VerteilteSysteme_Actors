package echo.actor;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

import actor.Actor;
import actor.Writer;
import inout.ScriptReader;
import inout.ScriptWriter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public  class Input2OutputTest {

	private Actor<String> producer;

	@ParameterizedTest
	@CsvSource({"'Hallo'",
							"'HalloHallo'"})
	public void test(String s) throws InterruptedException {
		ScriptReader scriptReader = new ScriptReader(s);
		ScriptWriter scriptWriter = new ScriptWriter();
		Writer w = new Writer(s, Actor.Type.SERIAL, scriptReader, scriptWriter, producer);
		// Input to Output mit den Writer als Runnable ausführen
		w.start();
		// Damit der MainThread nicht sofort terminiert
		sleep(500);


		assertEquals(scriptReader.toList(), scriptWriter.toList());

  }
}
