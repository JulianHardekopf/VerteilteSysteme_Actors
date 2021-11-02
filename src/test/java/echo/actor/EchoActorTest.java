package echo.actor;

import actor.Actor;
import actor.AskStream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public  class EchoActorTest {

	@ParameterizedTest
	@CsvSource({"'Hallo'",
							"'HalloHallo'"})
	public void test(String s) {

		EchoActor<String> echo = new EchoActor<>("echo", Actor.Type.SERIAL);

		// prüfen ob element von Ask gleich dem String s
    	assertEquals(AskStream.ask(echo,s, 10).head(), s);
	}

}
