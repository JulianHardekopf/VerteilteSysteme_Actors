package echo.tcpsocket;

import inout.InputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static inout.TCPReaderWriter.accept;
import static inout.TCPReaderWriter.connectTo;

public class TCPEchoServer {

    public static void main(String[] args) throws Exception {
        try (InputOutput inout = accept(Integer.parseInt(args[0])).call()
        )
        {	System.out.println("Verbindung hergestellt");
            inout.readLines().forEach(inout::printLine);
            inout.close();
            System.out.println("Verbindung beenden");

        } catch (IOException e) {System.err.println(e);}
    }
}
