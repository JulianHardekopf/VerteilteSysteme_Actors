package netcat.bidi;

import actor.Actor;
import actor.Writer;
import fpinjava.Result;
import inout.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Actor<String> producer = null;
        Result<Actor<String>> consumer = null;
        InputOutput tcpReaderWriter = TCPReaderWriter.connectTo(args[0], Integer.parseInt(args[1])).call();
        if(args.length == 2) {
            // Zu viele TCPReaderWriter kann nur eine pro Klasse geben
            Writer socketWriter = new Writer("socketWriter", Actor.Type.SERIAL, ConsoleReader.stdin(),
                    tcpReaderWriter, producer);
            Writer consoleWriter = new Writer("consolewriter", Actor.Type.SERIAL,
                    tcpReaderWriter, ConsoleWriter.stdout(), producer);
            socketWriter.start(Result.of(socketWriter));
            consoleWriter.start(Result.of(consoleWriter));

        }
    }
}
