package actor;

import fpinjava.Callable;
import fpinjava.Result;
import inout.TCPReader;
import inout.TCPReaderWriter;

public class ActorSystem {


    public static Runnable publish2one(Actor<String> actor, int port) throws Exception {
        return () -> {
        Actor<String> producer = null;
            try {
                Writer publizierterActor = new Writer("publizierterActor", Actor.Type.SERIAL, TCPReaderWriter.accept(port).call(), TCPReaderWriter.accept(port).call(), producer);
                publizierterActor.start();
            } catch (Exception e) {
                System.err.println(e);
            }
        };
    }

    public static Callable<Writer> actorSelection(String host, int port) {
        return () -> {
            Actor<String> producer = null;
            try {
                Writer entfernterActor = new Writer("entfernterActor", Actor.Type.SERIAL, TCPReaderWriter.connectTo(host, port).call(), TCPReaderWriter.connectTo(host, port).call(), producer);
                entfernterActor.start();
                return entfernterActor;
            } catch (Exception e) {
                System.err.println(e);
                return null;
            }
        };
    }
}
