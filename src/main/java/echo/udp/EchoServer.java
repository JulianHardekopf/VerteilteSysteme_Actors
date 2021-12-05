package echo.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {
    private static final int BUFSIZE = 508;
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer Port");
            return;
        }
        int port = Integer.parseInt(args[0]);
        // Socket an Port binden
        try (DatagramSocket socket = new DatagramSocket(port)) {
            // Paket zum Empfangen bzw. Senden
            DatagramPacket packet = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
            while (true) {
                // Paket empfangen
                socket.receive(packet);
                System.out.print("Received: "+ packet.getLength() +" bytes");
                // Paket an den Absender zurueckschicken
                socket.send(packet);
            }
        } catch (Exception e) {System.err.println(e);}
    }
}