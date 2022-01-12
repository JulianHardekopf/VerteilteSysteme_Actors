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

        if(args.length == 1) {
            InputOutput readerWriter = UDPSocket.udpReaderWriterServer(Integer.parseInt(args[0]));
            while (true) {
                readerWriter.printLine(time);
            }

        }


    }
}
