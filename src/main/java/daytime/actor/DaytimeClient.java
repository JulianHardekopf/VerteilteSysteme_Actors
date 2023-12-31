package daytime.actor;

import actor.Actor;
import actor.ActorSystem;
import actor.AskStream;
import actor.Writer;
import echo.actor.EchoActor;
import inout.ConsoleWriter;
import stream.Stream;

public class DaytimeClient {

    public static void main(String[] args) throws Exception {
        Writer writer = ActorSystem.actorSelection(args[0], Integer.parseInt(args[1])).call();
        Stream<String> stream = AskStream.ask(writer, "", 1000);
        ConsoleWriter consoleWriter = ConsoleWriter.stdout();
        consoleWriter.printLine(stream.head());

    }

}
