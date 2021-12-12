package inout;

import echo.udp.EchoClient;
import fpinjava.Result;
import tuple.Tuple;

import java.net.*;

public class UDPSocket implements InputOutput {
    private final String EOF = "\u0004";
    private final DatagramSocket socket;
    private final InetAddress address;
    private final int port;
    private static final int BUFSIZE = 1024;
    private boolean shutIn = false;
    private boolean shutOut = false;

    private UDPSocket(DatagramSocket socket, InetAddress address, int port) {
        this.socket = socket;
        this.address = address;
        this.port = port;

    }


    public void send(String s) throws Exception {
        byte[] sende = s.getBytes();
        DatagramPacket packetout = new DatagramPacket(sende, sende.length, address, port);
        socket.send(packetout);
    }

    public String receive(int BUFSIZE) throws Exception {
        byte[] payload = new byte[BUFSIZE];
        DatagramPacket packet = new DatagramPacket(payload, payload.length);
        socket.receive(packet);
        InetSocketAddress addr = new InetSocketAddress(packet.getAddress(), packet.getPort());
        return new String(packet.getData(), 0, packet.getLength());
    }

    @Override
    public Result<Tuple<String, Input>> readLine() {
        try {
            String msg = receive(BUFSIZE);
            if (msg.equals(EOF)) {
                shutdownInput();
                return Result.empty();
            } else {
                return Result.success(new Tuple<>(msg, this));
            }
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    @Override
    public void printLine(String s) {
        try {
            send(s);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void print(String s) {
        try {
            send(s);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void shutdownInput() {
        if (isOutputShutdown()) {
            socket.close();
        } else {
            shutdownInput();
            shutIn = true;
        }
    }

    @Override
    public void shutdownOutput() {
        try {
            send(EOF);
            if (isInputShutdown()) {
                socket.close();
            } else {
                shutdownOutput();
                shutOut = true;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public boolean isInputShutdown() {
        return shutIn;
    }

    public boolean isOutputShutdown() {
        return shutOut;
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    static Input udpReader(int localPort) throws SocketException, UnknownHostException {
        // Datagram zum Empfangen erhält ein Byte-Array
        DatagramSocket datagramSocket = new DatagramSocket(BUFSIZE);
        InetAddress addr = InetAddress.getByName("localhost");
        InputOutput udpReader = new UDPSocket(datagramSocket, addr, localPort);
        udpReader.shutdownOutput();
        return udpReader;
    }

    static Output udpWriter(String remoteHost, int remotePort) throws UnknownHostException, SocketException {
        InetAddress addr = InetAddress.getByName(remoteHost);
        // Datagramm zum Senden erhält IP und Port
        DatagramSocket datagramSocket = new DatagramSocket(remotePort, addr);
        InputOutput udpWriter =  new UDPSocket(datagramSocket, addr, remotePort);
        udpWriter.shutdownInput();
        return udpWriter;
    }

    public static InputOutput udpReaderWriter(int localPort) throws SocketException, UnknownHostException {
        DatagramSocket socket = new DatagramSocket(localPort);
        InetAddress addr = InetAddress.getByName("localhost");
        return new UDPSocket(socket, addr, localPort);
    }
    public static InputOutput udpReaderWriter(String remoteHost, int remotePort) throws SocketException, UnknownHostException {
        DatagramSocket socket = new DatagramSocket();
        return new UDPSocket(socket, InetAddress.getByName(remoteHost), remotePort);
    }
}