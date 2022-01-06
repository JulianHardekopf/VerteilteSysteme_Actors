package echo.actor;

import actor.AbstractActor;
import actor.Actor;
import fpinjava.Result;

public class EchoActor<T> extends AbstractActor<String> {

    public EchoActor(String id, Type type) {
        super(id, type);
    }

    public void onReceive(String message, Result<Actor<String>> sender) {
        // Empfängt eine Nachricht und sendet diese zurück zum Sender
        sender.forEach(s -> s.tell(message, this));

    }

}
