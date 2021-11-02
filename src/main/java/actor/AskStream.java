package actor;

import fpinjava.Result;
import stream.Stream;


public class AskStream  {

    public static Stream<String> ask(Actor<String> actor, String message, long timeout) {
        // ask erzeugt einen neuen Actor
        ActorReader tempActor = new ActorReader(message, Actor.Type.SERIAL, timeout);
        // tempActor sendet die Nachricht an den Actor
        actor.tell(message, tempActor);
        // tempActor nimmt die antwort entgegen und gibt diese als Stream wieder <- onReceive?
        // Timeout-Frist muss auch beachtet werden <- blockingQueue?
        // return antwort als Stream<String>
        return tempActor.readLines();

    }


}
