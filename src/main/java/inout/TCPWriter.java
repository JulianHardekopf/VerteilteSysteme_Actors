package inout;

import fpinjava.Callable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPWriter extends AbstractWriter {
    private final Socket socket;
    private TCPWriter(PrintWriter pw, Socket socket) {
        super(pw);
        this.socket = socket;
    }

    @Override
    public void print(String s) {
        super.print(s);
    }

    @Override
    public void printLine(String s) {
        super.printLine(s);
    }

    @Override
    public void shutdownOutput() {
        try {
            socket.shutdownOutput();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    public static Callable<Output> accept(int localPort) {
      return () -> {
          ServerSocket serverSocket = new ServerSocket(localPort);
          Socket socket = serverSocket.accept();
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          socket.shutdownInput();
          return new TCPWriter(out, socket);
      };
    }
    static Callable<Output> connectTo(String remoteHost, int remotePort) {
        return () -> {
            Socket socket = new Socket(remoteHost, remotePort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            socket.shutdownInput();
            return new TCPWriter(out, socket);
        };
    }
    static TCPWriter tcpWriter(PrintWriter printWriter, Socket socket) {
        return new TCPWriter(printWriter, socket);
    }
}
