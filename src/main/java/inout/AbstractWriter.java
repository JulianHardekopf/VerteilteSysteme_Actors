package inout;

import java.io.PrintWriter;

public  class AbstractWriter implements Output {
    protected final PrintWriter pw;

    protected AbstractWriter(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void print(String s) {
        pw.print(s);
        pw.flush();
    }

    @Override
    public void printLine(String s) {
        pw.println(s);
    }

    @Override
    public void close() throws Exception {
    }
}
