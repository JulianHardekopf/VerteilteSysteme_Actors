package daytime.udpsocket;

import inout.Input;
import inout.InputOutput;
import inout.Output;
import inout.UDPSocket;

import java.net.DatagramPacket;
import java.net.InetAddress;

import static inout.UDPSocket.udpReaderWriter;

public class DaytimeClient {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java DayTimeClient <host> <port>"); return;
        }
        InputOutput readerWriter = UDPSocket.udpReaderWriterClient(args[0], Integer.parseInt(args[1]));
        readerWriter.printLine("test");

        readerWriter.readLine();



    }
}
