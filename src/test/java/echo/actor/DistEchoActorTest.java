package echo.actor;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;

public class DistEchoActorTest {



    @ParameterizedTest
    @CsvSource({"'Hallo'",
            "'HalloHallo'"})
    public void test(String s) throws Exception {
        // Als Target ausführen -> working directory angeben / file seperator?
        // File DIRECTORY = new File(System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes");
        //String[] commandServer = {"java", "", "8111"};
        //  String path = System.getProperty("java.class.path");


        String path = System.getProperty("java.class.path");

        ProcessBuilder echoServer = new ProcessBuilder("java","-cp", path, "echo.actor.EchoServer", "8111");
        Process processServer =  echoServer.start();


        ProcessBuilder echoClient = new ProcessBuilder("java", "-cp", path, "echo.actor.EchoClient", "localhost", "8111", s);
        Process processClient = echoClient.start();

        BufferedReader clientInput = new BufferedReader(new InputStreamReader(processClient.getInputStream()));


        String compare = clientInput.readLine();
        processClient.destroy();
        processServer.destroy();

        assertEquals(compare, s);
    }


}
