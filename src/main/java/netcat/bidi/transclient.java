package netcat.bidi;

import actor.Actor;
import actor.ActorSystem;
import actor.Writer;
import fpinjava.Callable;
import inout.ConsoleReader;
import inout.ConsoleWriter;


public class transclient {

    public static void main(String[] args) throws Exception {
        Actor<String> producer = null;


        if(args.length == 2) {

            Callable<Writer> transceiverClient = ActorSystem.actorSelection(args[0], Integer.parseInt(args[1]));
            Writer consoleWriter = new Writer("sever", Actor.Type.SERIAL,
                    ConsoleReader.stdin(), ConsoleWriter.stdout(), producer);

            transceiverClient.call();
            consoleWriter.start();

        } else {
            Runnable transceiverServer = ActorSystem.publish2one(producer, Integer.parseInt(args[1]));
            Writer consoleWriter = new Writer("consolewriter", Actor.Type.SERIAL,
                    ConsoleReader.stdin(), ConsoleWriter.stdout(), producer);
            transceiverServer.run();
            consoleWriter.start();

        }
    }
}
