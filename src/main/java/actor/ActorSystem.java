package actor;

import fpinjava.Callable;
import fpinjava.Result;
import inout.InputOutput;
import inout.TCPReaderWriter;

public class ActorSystem {


    public static Runnable publish2one(Actor<String> actor, int port) {
        return () -> {

            try {
                InputOutput readerWriter = TCPReaderWriter.accept(port).call();
                Writer publizierterActor = new Writer("publizierterActor", Actor.Type.SERIAL, readerWriter, readerWriter);
                publizierterActor.start(actor.self());

            } catch (Exception e) {
                System.err.println(e);
            }
        };
    }

    public static Callable<Writer> actorSelection(String host, int port) {
        return () -> {
            Actor<String> producer = new ActorReader("producer", Actor.Type.SERIAL, 10000);
            InputOutput readerWriter = TCPReaderWriter.connectTo(host, port).call();
            return new Writer("entfernterActorClient", Actor.Type.SERIAL, readerWriter, readerWriter);
        };
    }

}
