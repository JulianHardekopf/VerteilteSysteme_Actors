package daytime.actor;
import actor.AbstractActor;
import actor.Actor;
import fpinjava.Result;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DaytimeActor extends AbstractActor<String> {
    public DaytimeActor(String id, Type type) {
        super(id, type);
    }
    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {

        sender.forEach(s -> s.tell("daytime is:" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), sender));
    }

}


