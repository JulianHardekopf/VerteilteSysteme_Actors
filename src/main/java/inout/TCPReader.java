package inout;

import fpinjava.Callable;
import fpinjava.Result;
import stream.Stream;
import tuple.Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class TCPReader extends AbstractReader{
    private final Socket socket;

    private TCPReader(BufferedReader reader, Socket socket) {
        super(reader);
        this.socket = socket;
    }



    @Override
    public Result<Tuple<String, Input>> readLine(String message) {
        return super.readLine(message);
    }

    @Override
    public Result<Tuple<Integer, Input>> readInt(String message) {
        return super.readInt(message);
    }

    @Override
    public Stream<String> readLines() {
        return super.readLines();
    }

    @Override
    public Stream<Integer> readInts() {
        return super.readInts();
    }

    @Override
    public void shutdownInput() {
        try {
            socket.shutdownInput();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @Override
    public void close() throws Exception {
        socket.close();
    }

    static Callable<Input> accept(int localPort) {

        return () -> {
            ServerSocket server = new ServerSocket(localPort);
            Socket socket = server.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socket.shutdownOutput();
            return new TCPReader(bufferedReader, socket);
        };
    }
     public static Callable<Input> connectTo(String remoteHost, int remotePort) {
        return () -> {
            Socket socket = new Socket(remoteHost, remotePort);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socket.shutdownOutput();
            return new TCPReader(bufferedReader, socket);

        };
    }
    static TCPReader tcpReader(BufferedReader bufferedReader, Socket socket) {
        return new TCPReader(bufferedReader, socket);
    }

}
