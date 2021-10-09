package inout;

import java.io.PrintWriter;

public  class AbstractWriter implements Output {
    protected final PrintWriter pw;

    protected AbstractWriter(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void print(String s) {
    }

    @Override
    public void printLine(String s) {
        Output.super.printLine(s);
    }

    @Override
    public void shutdownOutput() {
        Output.super.shutdownOutput();
    }

    @Override
    public void close() throws Exception {
    }
}
