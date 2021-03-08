package echo.actor;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
//import static actor.AskWithOutput.ask;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static list.List.list;

public  class EchoActorTest {

	@ParameterizedTest
	@CsvSource({"'Hallo'",
							"'HalloHallo'"})
	public void test(String s) throws InterruptedException {

		Thread.sleep(500);
    assertEquals("bla","bla");
	}
}
