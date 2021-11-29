package echo.tcpsocket;

import inout.InputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static inout.TCPReaderWriter.connectTo;

public class TCPEchoClient {

        public static void main(String[] args) throws Exception {
            if (args.length < 2) return;

            try (InputOutput inout = connectTo(args[0], Integer.parseInt(args[1])).call())
            {
                String text = "Client schickt einen neuen String";
                System.out.println("Sending to server:\n" + text);
                inout.printLine(text);
                inout.readLines().forEach(System.out::println);

                inout.close();
                System.out.println("Connection closed");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
}


