package echo.tcp;

import java.io.*;
import java.net.Socket;

// Der EchoClient sendet einen String an den EchoServer,
//empfängt das Echo vom EchoServer und schreibt es auf die Konsole.
public class EchoClient {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) return;

        try (   Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true))
        {
            String text = "Client schickt einen neuen String";
            System.out.println("Sending to server:\n" + text);
            out.println(text);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Client received: " + line);
                clientSocket.close();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
