package daytime.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class DaytimeServer {
    private static final String time = String.valueOf(LocalDateTime.now());


    public static void main(String[] args) throws IOException {
        if(args.length < 1) return;
        int port = Integer.parseInt(args[0]);
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server hört auf Port" + port);
            while(true) {
            Socket socket = server.accept();
            System.out.println("Client connected");
            OutputStream out = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(out, true);
            printWriter.println(time);
            server.close();
            }
        } catch (IOException e) { System.err.println(e);}

    }

}
