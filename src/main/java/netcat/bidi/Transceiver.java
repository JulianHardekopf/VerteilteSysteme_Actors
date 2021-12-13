package netcat.bidi;

import actor.Actor;
import actor.Writer;
import inout.ConsoleReader;
import inout.ConsoleWriter;
import inout.InputOutput;
import inout.TCPReaderWriter;

public class Transceiver {

    public static void main(String[] args) throws Exception {
        Actor<String> producer = null;


        if(args.length == 2) {
            InputOutput clientTcpReaderWriter = TCPReaderWriter.connectTo(args[0], Integer.parseInt(args[1])).call();
            Writer socketWriter = new Writer("socketWriter", Actor.Type.SERIAL,
                    ConsoleReader.stdin(), clientTcpReaderWriter, producer);
            Writer consoleWriter = new Writer("consolewriter", Actor.Type.SERIAL,
                    clientTcpReaderWriter, ConsoleWriter.stdout(), producer);
            socketWriter.start();
            consoleWriter.start();

        } else {
            InputOutput serverTcpReaderWriter = TCPReaderWriter.accept(Integer.parseInt(args[0])).call();
            Writer socketWriter = new Writer("socketWriter", Actor.Type.SERIAL,
                    ConsoleReader.stdin(), serverTcpReaderWriter, producer);
            Writer consoleWriter = new Writer("sever", Actor.Type.SERIAL,
                    serverTcpReaderWriter, ConsoleWriter.stdout(), producer);
            socketWriter.start();
            consoleWriter.start();
        }
    }
}
