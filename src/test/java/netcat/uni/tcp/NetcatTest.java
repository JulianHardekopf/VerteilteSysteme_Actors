package netcat.uni.tcp;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


class NetcatTest {
    private static final String JAVA_FILE_LOCATION = "~/IdeaProjects/template/src/main/java/netcat/uni/Netcat.java";
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
    @CsvSource({"'Hallo'",
            "'HalloHallo'"})
    public void test(String s) throws Exception {

        String[] commandServer = {"java",  "-jar", "/home/julian/IdeaProjects/template/out/artifacts/UniNetcat/praktikum.jar", "10005"};

        String[] commandClient = {"java",  "-jar", "/home/julian/IdeaProjects/template/out/artifacts/UniNetcat/praktikum.jar", "localhost", "10005"};
        ProcessBuilder processBuilderServer = new ProcessBuilder(commandServer);
        ProcessBuilder processBuilderClient = new ProcessBuilder(commandClient);

        Process processServer = processBuilderServer.start();

        Process processClient = processBuilderClient.start();

        BufferedReader serverStream = new BufferedReader(new InputStreamReader(processServer.getInputStream()));
        BufferedWriter clientStream = new BufferedWriter(new OutputStreamWriter(processClient.getOutputStream()));

        clientStream.write(s);
        clientStream.flush();
        clientStream.close();

        String compare = serverStream.readLine();

        processClient.destroy();
        processServer.destroy();
        Assertions.assertEquals(s, compare);

        // Vergleich was beim Server ankommt und Parameterübergabe



    }
}
