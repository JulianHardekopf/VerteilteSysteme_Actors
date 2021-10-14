package inout;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ConsoleWriter extends AbstractWriter{
    protected ConsoleWriter(PrintWriter pw) {
        super(pw);
    }

    public static ConsoleWriter stdout() {
        return new ConsoleWriter(new PrintWriter(
                new OutputStreamWriter(System.out), true));
    }
}
