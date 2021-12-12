package netcat.bidi;

import actor.Actor;
import actor.Reader;
import actor.Writer;
import fpinjava.Result;
import inout.ConsoleReader;
import inout.ConsoleWriter;
import inout.InputOutput;
import inout.TCPReaderWriter;

public class Server {
    public static void main(String[] args) throws Exception {
        Actor<String> producer = null;
        Result<Actor<String>> consumer = null;

        InputOutput tcpReaderWriter =  TCPReaderWriter.accept(Integer.parseInt(args[0])).call();
        if(args.length == 1) {
            Writer socketWriter = new Writer("socketWriter", Actor.Type.SERIAL,
                    ConsoleReader.stdin(), tcpReaderWriter, producer);
            socketWriter.start(Result.of(socketWriter));
            Writer consoleWriter = new Writer("sever", Actor.Type.SERIAL,
                    tcpReaderWriter, ConsoleWriter.stdout(), producer);
            consoleWriter.start(Result.of(consoleWriter));

        }
    }
}
