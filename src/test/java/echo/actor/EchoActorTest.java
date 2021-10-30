package echo.actor;

import actor.AbstractActor;
import actor.Actor;
import actor.ActorReader;
import actor.AskStream;
import inout.AbstractReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static list.List.list;

public  class EchoActorTest {

	@ParameterizedTest
	@CsvSource({"'Hallo'",
							"'HalloHallo'"})
	public void test(String s) {

		EchoActor<String> echo = new EchoActor<>("echo", Actor.Type.SERIAL);
		AskStream.ask(echo, s, 10);
		// prüfen ob element von Ask gleich dem String s

    	assertEquals(AskStream.ask(echo,s, 10).head(), s);
	}
}
