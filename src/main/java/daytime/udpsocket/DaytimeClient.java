package daytime.udpsocket;

import inout.InputOutput;
import inout.UDPSocket;

import java.net.DatagramPacket;
import java.net.InetAddress;

import static inout.UDPSocket.udpReaderWriter;

public class DaytimeClient {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java DayTimeClient <host> <port>"); return;
        }
        InputOutput udpReaderWriter = UDPSocket.udpReaderWriter(args[0], Integer.parseInt(args[1]));
        System.out.println((args[0] +  Integer.parseInt(args[1])));
        udpReaderWriter.printLine("Anfrage");

        System.out.println("anfrage durch");
        udpReaderWriter.readLine();
        System.out.println(udpReaderWriter.readLine());

    }
}
