package netcat.uni;

import actor.Actor;
import inout.ConsoleWriter;
import inout.TCPReader;

public class Writer {

    public static void main(String[] args) throws Exception {
        Actor<String> producer = null;
        actor.Writer serverWriter = new actor.Writer("netcatserver", Actor.Type.SERIAL,
                TCPReader.accept(Integer.parseInt(args[0])).call(), ConsoleWriter.stdout(), false);
        serverWriter.start();
    }
}
