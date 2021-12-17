package echo.actor;

import actor.Actor;
import actor.ActorSystem;
import actor.AskStream;
import actor.Writer;
import inout.ConsoleWriter;
import stream.Stream;

public class EchoClient {

    public static void main(String[] args) throws Exception {

        Writer writer = ActorSystem.actorSelection(args[0], Integer.parseInt(args[1])).call();
        Stream<String> stream = AskStream.ask2(writer, "ECHO", 10000);
        ConsoleWriter consoleWriter = ConsoleWriter.stdout();
        stream.forEach(System.out::println);
        consoleWriter.printLine(stream.head());

    }
}