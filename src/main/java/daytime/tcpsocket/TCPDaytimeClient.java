package daytime.tcpsocket;

import inout.Input;
import static inout.TCPReader.*;

public class TCPDaytimeClient {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Usage: java DayTimeClient <host> <port>");
            return;
        }
        try (Input in = connectTo(args[0], Integer.parseInt(args[1])).call())
        {
            in.readLines().forEach(System.out::println);
        } catch (Exception e) { System.err.println(e);}
    }
}
