package echo.udp;
import java.net.*;


public class EchoClient {
    private static final int BUFSIZE = 508;
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Usage: java DayTimeClient <host> <port>"); return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try (DatagramSocket socket = new DatagramSocket()){
            InetAddress addr = InetAddress.getByName(host);
            String echo = "Echo an Server";
            byte[] data = echo.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);
            while (true) {
                socket.send(packet);
                System.out.println("Sende folgendes Paket: " + echo);
                packet = new DatagramPacket(data, data.length);
                socket.receive(packet);
                String received = new String(
                        packet.getData(), 0, packet.getLength());
                System.out.println("Empfangen: " + received);
                socket.close();

            }
        }

        catch (Exception e) {
            System.err.println(e);
        }
    }

}
