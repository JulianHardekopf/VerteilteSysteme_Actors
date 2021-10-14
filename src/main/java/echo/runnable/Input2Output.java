package echo.runnable;
import inout.*;


public class Input2Output {

    public static void main(String[] args) {
        input2output(ConsoleReader.stdin(),ConsoleWriter.stdout()).run();

    }

    static Runnable input2output(Input in, Output out) {

        return () -> { in.readLines().forEach(out::print);
            out.shutdownOutput();
        };
    }


}

