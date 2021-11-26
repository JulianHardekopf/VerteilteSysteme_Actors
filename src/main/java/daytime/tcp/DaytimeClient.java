package daytime.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DaytimeClient {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java DayTimeClient <host> <port>");
            return;
        }
        try (
                Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
                InputStream in = socket.getInputStream()

        )
        {	StringBuilder time = new StringBuilder();
            int c;
            while ((c = in.read()) != -1) {
                time.append((char) c);
            }
            System.out.println(time);

        } catch (Exception e) { System.err.println(e);}
    }
}
