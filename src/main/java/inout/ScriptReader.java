package inout;

import fpinjava.Result;
import list.List;
import tuple.Tuple;

public class ScriptReader implements Input {
    private final List<String> commands;
    public ScriptReader(List<String> commands) {
        super();
        this.commands = commands;
    }
    public ScriptReader(String... commands) {
        super();
        this.commands = List.list(commands);
    }
    public Result<Tuple<String, Input>> readLine() {
        return commands.isEmpty()
                ? Result.failure("Not enough entries in script")
                : Result.success(new Tuple<>(commands.head(),
                new ScriptReader(commands.drop(1))));
    }
    @Override
    public Result<Tuple<Integer, Input>> readInt() {
        try {
            return commands.isEmpty()
                    ? Result.failure("Not enough entries in script")
                    : Integer.parseInt(commands.head()) >= 0
                    ? Result.success(new Tuple<>(Integer.parseInt(
                    commands.head()),
                    new ScriptReader(commands.drop(1))))
                    : Result.empty();
        } catch(Exception e) {
            return Result.failure(e);
        }
    }
    // nicht sicher Methodenkopf und Bezug
    public List<String> toList() {
        return commands;
    }

    @Override
    public void close() throws Exception {

    }
}