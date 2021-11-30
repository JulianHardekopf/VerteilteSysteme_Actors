package inout;

import fpinjava.Callable;
import fpinjava.Result;
import tuple.Tuple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReaderWriter implements InputOutput {
    private final Socket socket;
    private final AbstractReader abstractReader;
    private final AbstractWriter abstractWriter;

    private TCPReaderWriter(Socket socket, AbstractReader abstractReader, AbstractWriter abstractWriter) {
        this.socket = socket;
        this.abstractReader = abstractReader;
        this.abstractWriter = abstractWriter;
    }

    @Override
    public Result<Tuple<String, Input>> readLine() {
        return abstractReader.readLine();
    }

    @Override
    public Result<Tuple<String, Input>> readLine(String message) {
        return abstractReader.readLine(message);
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    @Override
    public void print(String s) {
        abstractWriter.print(s);
    }

    @Override
    public void printLine(String s) {
        abstractWriter.printLine(s);
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
    public void shutdownOutput() {
        try {
            socket.shutdownOutput();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static Callable<InputOutput> accept(int localPort){
        return () -> {
            ServerSocket serverSocket = new ServerSocket(localPort);
            Socket socket = serverSocket.accept();

            return getInputOutput(socket);
        };
    }

    private static InputOutput getInputOutput(Socket socket) throws IOException {
        TCPReader tcpReader = new TCPReader(new BufferedReader(new InputStreamReader(socket.getInputStream())), socket);
        TCPWriter tcpWriter = new TCPWriter(new PrintWriter(socket.getOutputStream(), true), socket);
        return new TCPReaderWriter(socket, tcpReader, tcpWriter);
    }

    public static Callable<InputOutput> connectTo(String remoteHost, int remotePort) {
        return () -> {
            Socket socket = new Socket(remoteHost, remotePort);
            return getInputOutput(socket);
        };
    }
}
