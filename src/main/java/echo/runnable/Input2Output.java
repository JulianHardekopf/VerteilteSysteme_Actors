package echo.runnable;
import inout.ConsoleReader;
import inout.ConsoleWriter;
import inout.Input;
import inout.Output;

public class Input2Output {

    public static void main(String[] args) {
        Input input = ConsoleReader.stdin();
        Output output = ConsoleWriter.stdout();
        input2output(input,output).run();
        output.shutdownOutput();
    }

    static Runnable input2output(Input in, Output out) {
        return in == null ? null : () -> in.readLine().forEach(System.out::println);
    }

}
