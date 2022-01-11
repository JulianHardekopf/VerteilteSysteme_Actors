package actor;

import fpinjava.Callable;
import fpinjava.Result;
import inout.InputOutput;
import inout.TCPReaderWriter;

import java.net.ServerSocket;
import java.net.Socket;

public class ActorSystem {


    public static Runnable publish2one(Actor<String> actor, int port) {
        return () -> {
            try {
                InputOutput readerWriter = TCPReaderWriter.accept(port).call();
                Writer transceiver = Writer.transceiver(readerWriter, readerWriter);
                transceiver.start(actor.self());

            } catch (Exception e) {
                System.err.println(e);
            }
        };
    }

    public static Callable<Writer> actorSelection(String host, int port) {
        return () -> {
            InputOutput readerWriter = TCPReaderWriter.connectTo(host, port).call();
            return Writer.transceiver(readerWriter, readerWriter);
        };
    }

    public static Runnable publish2multiple(Actor<String> actor, int port) {
        return () -> {

                while (true) {
                    InputOutput slave = null;
                    try {
                        slave = TCPReaderWriter.accept(port).call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new Writer("Slave", Actor.Type.SERIAL, slave ,slave).start(actor.self());

            }
        };

    }
}
