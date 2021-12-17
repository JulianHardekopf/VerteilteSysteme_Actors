package netcat.bidi;

import actor.Actor;
import actor.Writer;
import fpinjava.Result;
import inout.ConsoleReader;
import inout.ConsoleWriter;
import inout.InputOutput;
import inout.TCPReaderWriter;

public class Server {
    public static void main(String[] args) throws Exception {
        Actor<String> producer = null;


        InputOutput tcpReaderWriter =  TCPReaderWriter.accept(Integer.parseInt(args[0])).call();
        if(args.length == 1) {
            Writer socketWriter = new Writer("socketWriter", Actor.Type.SERIAL,
                    tcpReaderWriter, tcpReaderWriter, producer);

            Writer consoleWriter = new Writer("sever", Actor.Type.SERIAL,
                    ConsoleReader.stdin(), ConsoleWriter.stdout(), producer);
            socketWriter.start(Result.of(consoleWriter));
            consoleWriter.start(Result.of(socketWriter));

        }
    }
}
