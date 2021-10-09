package inout;


import fpinjava.Result;
import tuple.Tuple;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class ConsoleReader extends AbstractReader {
   

    protected ConsoleReader(BufferedReader reader) {
        super(reader);
    }

    @Override
    public Result<Tuple<String, Input>> readLine(String message) {
        return super.readLine(message);
    }

    @Override
    public Result<Tuple<Integer, Input>> readInt(String message) {
        System.out.print(message + " ");
        return readInt();
    }
/*
    @Override
    public Stream<String> readLines() {
        return super.readLines();
    }

    @Override
    public Stream<Integer> readInts() {
        return super.readInts();
    }
*/
    @Override
    public void shutdownInput() {
        super.shutdownInput();
    }

    public static ConsoleReader stdin() {
        return new ConsoleReader(new BufferedReader(
                new InputStreamReader(System.in)));
    }

    @Override
    public void close() throws Exception {

    }
}