package echo.actor;

import actor.Actor;
import actor.ActorSystem;

public class EchoServer {

    public static void main(String[] args) throws Exception {
        EchoActor<String> echoActor = new EchoActor<>("echoActor", Actor.Type.SERIAL);
        ActorSystem.publish2one(echoActor, Integer.parseInt(args[0])).run();

    }
}
