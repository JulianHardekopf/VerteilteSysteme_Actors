package inout;

import java.io.*;

public class test {

    public static void main(String[] args) throws IOException, InterruptedException {
        String f = "test";
        String s = "hallo";
        String[] command = {"java",  "-jar", "/home/julian/IdeaProjects/template/out/artifacts/BidiNetcat_jar2/praktikum.jar"};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process clientProcess = processBuilder.start();
        Process serverProcess = processBuilder.start();
        //System.setOut(new PrintStream(clientProcess.getOutputStream(), true));
        //System.setOut(new PrintStream(serverProcess.getOutputStream(), true));
        BufferedReader serverinputStream = new BufferedReader(new InputStreamReader(serverProcess.getInputStream()));
        PrintWriter serverOutput = new PrintWriter(new PrintWriter(serverProcess.getOutputStream(), true));
        BufferedReader clientinputStream = new BufferedReader(new InputStreamReader(clientProcess.getInputStream()));
        PrintWriter clientOutput = new PrintWriter(new PrintWriter(clientProcess.getOutputStream(), true));

        StringBuilder builder = new StringBuilder();
        clientOutput.printf(s);
        serverOutput.printf(f);


        //clientOutput.println(s);
        //serverOutput.print(f);
        String clienttext;
        String servertext;
        while ((servertext = serverinputStream.readLine()) != null) {
            builder.append(servertext);
        }
        while ((clienttext = clientinputStream.readLine()) != null) {
            builder.append(clienttext);
        }
        String result = builder.toString();
        System.out.println(result);


        // Vergleich was beim Server ankommt und Parameterübergabe

        serverinputStream.close();
        clientinputStream.close();

    }
}