package daytime.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDateTime;

public class DaytimeServer {
    private static final String time = String.valueOf(LocalDateTime.now());
    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("Fehlende Argumente"); return;
        }
        try(DatagramSocket socket = new DatagramSocket(Integer.parseInt(args[0]))) {
            while(true) {
                byte[] data = new byte[29];
                DatagramPacket packetIn  = new DatagramPacket(data, data.length);
                socket.receive(packetIn);
                InetAddress address = packetIn.getAddress();
                int port = packetIn.getPort();
                System.out.println( "Anfrage von " + address + " am Port " + port);
                time.getBytes(0, time.length(), data, 0);
                DatagramPacket packetOut  = new DatagramPacket(data, data.length, address, port);
                socket.send(packetOut);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
