package actor;

import fpinjava.Result;
import stream.Stream;


public class AskStream extends AbstractActor<String> {


    public AskStream(String id, Type type) {
        super(id, type);
    }

    public static Stream<String> ask(Actor<String> actor, String message, long timeout) {
        // ask erzeugt einen neuen Actor
        ActorReader tempActor = new ActorReader(message, Actor.Type.SERIAL, timeout);
        // tempActor sendet die Nachricht an den Actor
        tempActor.tell(message, actor);
        // tempActor nimmt die antwort entgegen und gibt diese als Stream wieder <- onReceive?
        // Timeout-Frist muss auch beachtet werden <- blockingQueue?
        // return antwort als Stream<String>
        return tempActor.readLines();

    }


    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        sender.forEach(stringActor -> stringActor.tell(message , self()));
    }
}
