package netcat.bidi.tcp;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class BidiNetcatTest {

    //Programmieren Sie für Ihr unidirektionales Netcat einen Parametrized JUnit-Test (netcat.uni.tcp.NetcatTest).
    //Die Testmethode hat einen Parameter vom Typ String,
    //der als Testeingabe für den Input des Netcat-Client dient.
    //Verwenden Sie den ProcessBuilder.
    //Die start-Methode des ProcessBuilders liefert für Client und Server
    //jeweils ein Process-Objekt.
    //Mithilfe der Methoden getInputStream und getOutputStream der Process-Objekte,
    //können Sie beim Netcat-Client die Testeingabe einspeisen und
    //beim Netcat-Server die Ausgabe ablesen.

    @ParameterizedTest
    @CsvSource({"Hallo  , Test!"})
    public void test(String s, String f) throws Exception {


        String[] commandServer = {"java", "-jar", "/home/julian/IdeaProjects/template/out/artifacts/BidiNetcat_jar2/praktikum.jar", "10006"};
        String[] commandClient = {"java", "-jar", "/home/julian/IdeaProjects/template/out/artifacts/BidiNetcat_jar2/praktikum.jar", "localhost", "10006"};
        ProcessBuilder processBuilderClient = new ProcessBuilder(commandClient);
        ProcessBuilder processBuilderServer = new ProcessBuilder(commandServer);
        Process processServer = processBuilderServer.start();
        Process processClient = processBuilderClient.start();
        BufferedReader serverinputStream = new BufferedReader(new InputStreamReader(processServer.getInputStream()));
        BufferedWriter serverOutput = new BufferedWriter(new OutputStreamWriter(processServer.getOutputStream()));
        BufferedReader clientinputStream = new BufferedReader(new InputStreamReader(processClient.getInputStream()));
        BufferedWriter clientOutput = new BufferedWriter(new OutputStreamWriter(processClient.getOutputStream()));

        serverOutput.write(f);
        serverOutput.flush();
        serverOutput.close();
        clientOutput.write(s);
        clientOutput.flush();
        clientOutput.close();

        String clienttext = serverinputStream.readLine();
        String servertext = clientinputStream.readLine();

        processServer.destroy();
        processClient.destroy();
        // Vergleich was beim Server ankommt und Parameterübergabe
        assertEquals(f, servertext);
        assertEquals(s, clienttext);

    }
}
