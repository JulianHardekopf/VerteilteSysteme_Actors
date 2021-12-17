package daytime.udpsocket;

import inout.Input;
import inout.InputOutput;
import inout.Output;
import inout.UDPSocket;
import tuple.Tuple;

import static inout.UDPSocket.*;

import java.time.LocalDateTime;

public class DaytimeServer {
    private static final String time = String.valueOf(LocalDateTime.now());

    public static void main(String[] args) throws Exception {

        while (true) {
            InputOutput readerWriter = UDPSocket.udpReaderWriter(7777);

            // Woher kriegt der Server adresse und port vom Client?
            readerWriter.printLine(time);
        }
    }
}
