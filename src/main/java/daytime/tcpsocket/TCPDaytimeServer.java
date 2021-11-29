package daytime.tcpsocket;

import inout.Output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import static inout.TCPWriter.*;

public class TCPDaytimeServer {
    private static final String time = String.valueOf(LocalDateTime.now());

    public static void main(String[] args) throws Exception {
        if(args.length < 1) return;
        int port = Integer.parseInt(args[0]);
        try (Output out = accept(port).call())
        {
            System.out.println("Server hört auf Port" + port);
            System.out.println("Client connected");
            out.printLine(time);
            System.out.println("Verbindung beendet");

        } catch (IOException e) { System.err.println(e);}

    }

}