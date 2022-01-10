package echo.actor;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import stream.Stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistEchoActorTest {



        @ParameterizedTest
        @CsvSource({"'Hallo'",
                "'HalloHallo'"})
        public void test(String s) throws Exception {

            String[] commandServer = {"java", "jar", "C:\\Users\\julian\\IdeaProjects\\template\\out\\artifacts\\EchoTransceiver_jar\\praktikum.jar", "8111"};
            String[] commandClient = {"java", "jar", "C:\\Users\\julian\\IdeaProjects\\template\\out\\artifacts\\EchoTransceiver_jar\\praktikum.jar", "localhost", "8111", s};
            ProcessBuilder processBuilderServer = new ProcessBuilder(commandServer);
            ProcessBuilder processBuilderClient = new ProcessBuilder(commandClient);
            Process processServer = processBuilderServer.start();
            Process processClient = processBuilderClient.start();

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(processServer.getInputStream()));
            BufferedWriter serverOutput = new BufferedWriter(new OutputStreamWriter(processServer.getOutputStream()));

            BufferedReader clientInput = new BufferedReader(new InputStreamReader(processClient.getInputStream()));
            BufferedWriter clientOutput = new BufferedWriter(new OutputStreamWriter(processClient.getOutputStream()));


            clientOutput.write(s);
            clientOutput.flush();
            clientOutput.close();
            serverInput.readLine();
            serverOutput.write(s);



            String compare = clientInput.readLine();
            processClient.destroy();
            processServer.destroy();

            // prüfen ob element von Ask gleich dem String s
            assertEquals(compare, s);
        }


}
