package daytime.actor;

import actor.Actor;
import actor.ActorSystem;
import actor.AskStream;
import echo.actor.EchoActor;
import inout.ConsoleReader;

public class DaytimeServer {
    public static void main(String[] args) throws Exception {
        DaytimeActor<String> daytimeActor = new DaytimeActor<>("dayTimeActor", Actor.Type.SERIAL);
        ActorSystem.publish2multiple(daytimeActor, Integer.parseInt(args[0])).run();


    }
}
