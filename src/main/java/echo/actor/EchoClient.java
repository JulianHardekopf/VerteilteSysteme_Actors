package echo.actor;

import actor.Actor;
import actor.ActorSystem;
import actor.AskStream;
import actor.Writer;
import inout.ConsoleWriter;
import stream.Stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EchoClient {

    public static void main(String[] args) throws Exception {

        Writer writer = ActorSystem.actorSelection(args[0], Integer.parseInt(args[1])).call();
        Stream<String> stream = AskStream.ask(writer, args[2], 1000);
        stream.forEach(System.out::println);

    }
}