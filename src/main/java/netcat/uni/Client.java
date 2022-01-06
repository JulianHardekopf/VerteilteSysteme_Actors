package netcat.uni;

import actor.Actor;
import actor.Writer;
import inout.ConsoleReader;
import inout.TCPWriter;

public class Client {

    public static void main(String[] args) throws Exception {

        actor.Writer clientWriter = new Writer("netcatclient", Actor.Type.SERIAL, ConsoleReader.stdin(),
                TCPWriter.connectTo(args[0], Integer.parseInt(args[1])).call());
        clientWriter.start();
    }
}
