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


            InputOutput readerWriter = UDPSocket.udpReaderWriterServer(Integer.parseInt(args[0]));
            readerWriter.printLine("gwef");
            readerWriter.readLine();
            readerWriter.printLine(time);

    }
}
