package echo.actor;

import actor.ActorSystem;
import actor.AskStream;
import actor.Writer;
import inout.ConsoleWriter;
import stream.Stream;

public class EchoClient2 {
    public static void main(String[] args) throws Exception {

        Writer writer = ActorSystem.actorSelection(args[0], Integer.parseInt(args[1])).call();
        Stream<String> stream = AskStream.ask(writer, args[2], 1000);
        ConsoleWriter consoleWriter = ConsoleWriter.stdout();
        consoleWriter.printLine(stream.head());

    }
}
