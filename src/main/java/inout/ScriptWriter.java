package inout;

import list.List;

public class ScriptWriter implements Output {
    private List<String> commands;

    public ScriptWriter() {
        super();
        commands = List.list();
    }

    @Override
    public void print(String s) {
         commands = commands.cons(s);
    }

    public void printLine(String s) {
        commands = commands.cons(s);
    }

    public List<String> toList() {

        return commands;
    }

    @Override
    public void close() throws Exception {

    }
}
