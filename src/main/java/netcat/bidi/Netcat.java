package netcat.bidi;

import actor.Actor;
import actor.Writer;
import fpinjava.Result;
import inout.ConsoleReader;
import inout.ConsoleWriter;
import inout.InputOutput;
import inout.TCPReaderWriter;

public class Netcat {

    public static void main(String[] args) throws Exception {



        if(args.length == 2) {
            InputOutput clientTcpReaderWriter = TCPReaderWriter.connectTo(args[0], Integer.parseInt(args[1])).call();
            Writer socketWriter = new Writer("socketWriter", Actor.Type.SERIAL,
                    clientTcpReaderWriter, clientTcpReaderWriter);

            Writer consoleWriter = new Writer("sever", Actor.Type.SERIAL,
                    ConsoleReader.stdin(), ConsoleWriter.stdout());
            socketWriter.start(Result.of(consoleWriter));
            consoleWriter.start(Result.of(socketWriter));

        } else {
            InputOutput serverTcpReaderWriter = TCPReaderWriter.accept(Integer.parseInt(args[0])).call();
            Writer socketWriter = new Writer("socketWriter", Actor.Type.SERIAL, serverTcpReaderWriter,
                    serverTcpReaderWriter);
            Writer consoleWriter = new Writer("consolewriter", Actor.Type.SERIAL,
                    ConsoleReader.stdin(), ConsoleWriter.stdout());
            socketWriter.start(Result.of(consoleWriter));
            consoleWriter.start(Result.of(socketWriter));
        }
    }
}
