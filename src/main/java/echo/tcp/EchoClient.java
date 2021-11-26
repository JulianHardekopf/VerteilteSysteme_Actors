package echo.tcp;

import java.io.*;
import java.net.Socket;

// Der EchoClient sendet einen String an den EchoServer,
//empfängt das Echo vom EchoServer und schreibt es auf die Konsole.
public class EchoClient {

    public static void main(String[] args) {
        if (args.length < 2) return;
        String hostname = args[0];
        int port = 7777;
        try (   Socket socket = new Socket(args[0], port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                )
        {
            String text = "Client schickt einen neuen String";
            out.println(text);
            in.readLine();
            out.println(in);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
