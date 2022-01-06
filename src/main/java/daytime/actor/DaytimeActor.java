package daytime.actor;
import actor.AbstractActor;
import actor.Actor;
import fpinjava.Result;
import java.time.LocalDateTime;



public class DaytimeActor<T> extends AbstractActor<String> {
    public DaytimeActor(String id, Type type) {
        super(id, type);
    }
    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        sender.forEach(s -> s.tell(message + (LocalDateTime.now()), sender));
    }

}


