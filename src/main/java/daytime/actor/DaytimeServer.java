package daytime.actor;

import actor.Actor;
import actor.ActorSystem;
import echo.actor.EchoActor;

public class DaytimeServer {
    public static void main(String[] args) throws Exception {
        DaytimeActor daytimeActor = new DaytimeActor("dayTimeActor", Actor.Type.SERIAL);
        ActorSystem.publish2one(daytimeActor, Integer.parseInt(args[0])).run();

    }
}
