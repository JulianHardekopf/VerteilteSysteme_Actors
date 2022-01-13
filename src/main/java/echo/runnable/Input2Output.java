package echo.runnable;
import actor.Actor;
import actor.Writer;
import inout.*;

import static java.lang.Thread.sleep;


public class Input2Output {



    public static void main(String[] args) throws InterruptedException {
       // input2output(ConsoleReader.stdin(),ConsoleWriter.stdout()).run();
        input2output(new Writer("", Actor.Type.SERIAL, ConsoleReader.stdin(), ConsoleWriter.stdout(), false)).run();
        sleep(10000);
    }

    static Runnable input2output(Input in, Output out) {

        return () -> { in.readLines().forEach(out::print);
            out.shutdownOutput();
        };
    }
    public static Runnable input2output(Writer writer) {
        // MethodenReferenz zur Klasse Writer -> Methode start
        return writer::start;
    }


}

