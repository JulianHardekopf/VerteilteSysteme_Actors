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

            Writer socketWriter = new Writer("socketWriter", Actor.Type.SERIAL, tcpReaderWriter,
                    tcpReaderWriter, producer);
            Writer consoleWriter = new Writer("consolewriter", Actor.Type.SERIAL,
                    ConsoleReader.stdin(), ConsoleWriter.stdout(), producer);
            socketWriter.start(Result.of(consoleWriter));
            consoleWriter.start(Result.of(socketWriter));

        }
    }
}
