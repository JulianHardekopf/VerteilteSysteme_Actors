package inout;

import echo.udp.EchoClient;
import fpinjava.Result;
import stream.Stream;
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

    private UDPSocket(DatagramSocket socket, InetAddress address, int port) throws UnknownHostException {
        this.socket = socket;
        this.address = address;
        this.port = port;

    }


    public void send(String s) throws Exception {
        byte[] sende = s.getBytes("utf-8");
        DatagramPacket packetout = new DatagramPacket(sende, sende.length, address, port);
        socket.send(packetout);
    }

    public String receive() throws Exception {
        String msg;
        DatagramPacket packet = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
        try {
            socket.receive(packet);
            msg = new String(packet.getData(), 0, packet.getLength(), "utf-8");
        } catch (Exception e) {
            System.err.println(e);
            msg = null;
        }
        return msg;
    }


    @Override
    public Result<Tuple<String, Input>> readLine() {
        try {
            String msg = receive();
            if (msg.equals(EOF)) {
                shutdownInput();
                return Result.empty();
            } else {
                return Result.success(new Tuple<>(msg, this));
            }
        } catch (Exception e) {
            System.err.println("error in Readline");
            return Result.failure(e);
        }
    }

    @Override
    public Stream<String> readLines() {
        return InputOutput.super.readLines();
    }

    @Override
    public void printLine(String s) {
        try {
            send(s);
        } catch (Exception e) {
            System.err.println("Printline geht nicht");
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
        return shutIn = true;
    }

    public boolean isOutputShutdown() {
        return shutOut = true;
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    public static Input udpReader(int localPort) throws SocketException, UnknownHostException {
        // Datagram zum Empfangen erhält ein Byte-Array
        DatagramSocket datagramSocket = new DatagramSocket(BUFSIZE);
        InetAddress addr = InetAddress.getByName("localhost");
        InputOutput udpReader = new UDPSocket(datagramSocket, addr, localPort);
        udpReader.shutdownOutput();
        return udpReader;
    }

    public static Output udpWriter(String remoteHost, int remotePort) throws UnknownHostException, SocketException {
        InetAddress addr = InetAddress.getByName(remoteHost);
        // Datagramm zum Senden erhält IP und Port
        DatagramSocket datagramSocket = new DatagramSocket(remotePort, addr);
        InputOutput udpWriter =  new UDPSocket(datagramSocket, addr, remotePort);
        udpWriter.shutdownInput();
        return udpWriter;
    }

    public static InputOutput udpReaderWriterServer(int localPort) throws SocketException, UnknownHostException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress addr = InetAddress.getByName("localhost");
        return new UDPSocket(datagramSocket, addr, localPort);
    }
    public static InputOutput udpReaderWriterClient(String remotehost, int remoteport) throws SocketException, UnknownHostException {
        DatagramSocket datagramSocket = new DatagramSocket(remoteport);
        InetAddress addr = InetAddress.getByName(remotehost);
        return new UDPSocket(datagramSocket, addr, remoteport);
    }
}