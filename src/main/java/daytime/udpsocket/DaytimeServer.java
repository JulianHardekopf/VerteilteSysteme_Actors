package daytime.udpsocket;

import inout.InputOutput;
import inout.UDPSocket;
import tuple.Tuple;

import static inout.UDPSocket.*;

import java.time.LocalDateTime;

public class DaytimeServer {
    private static final String time = String.valueOf(LocalDateTime.now());

    public static void main(String[] args) throws Exception {

        InputOutput udpReaderWriter = udpReaderWriter(Integer.parseInt(args[0]));
        System.out.println("Server hört auf Port" + Integer.parseInt(args[0]));
        udpReaderWriter.readLine();
        // Woher kriegt der Server adresse und port vom Client?
        udpReaderWriter.printLine(time);

    }
}
