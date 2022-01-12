package daytime.udpsocket;

import fpinjava.Result;
import inout.Input;
import inout.InputOutput;
import inout.Output;
import inout.UDPSocket;
import tuple.Tuple;

import java.net.DatagramPacket;
import java.net.InetAddress;


public class DaytimeClient {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java DayTimeClient <host> <port>"); return;
        } else {
            InputOutput readerWriter = UDPSocket.udpReaderWriterClient(args[0], Integer.parseInt(args[1]));
            readerWriter.printLine("");
            Result<Tuple<String, Input>> answer = readerWriter.readLine();
            System.out.println(answer);
        }

    }
}
