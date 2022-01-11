package echo.actor;

import actor.Actor;
import actor.ActorSystem;
import actor.AskStream;
import actor.Writer;
import inout.ConsoleWriter;
import stream.Stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class EchoTransceiver {

    public static void main(String[] args) throws Exception {
        if(args.length == 1) {
            EchoActor<String> echoActor = new EchoActor<>("echoActor", Actor.Type.SERIAL);
            ActorSystem.publish2one(echoActor, Integer.parseInt(args[0])).run();
        } if(args.length == 2) {
            Writer writer = ActorSystem.actorSelection(args[0], Integer.parseInt(args[1])).call();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            Stream<String> stream = AskStream.ask(writer, bufferedReader.readLine(), 1000);
            ConsoleWriter consoleWriter = ConsoleWriter.stdout();
            consoleWriter.printLine(stream.head());
        }
    }
}
