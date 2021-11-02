package inout;

public interface InputOutput extends Input, Output {
    // Shutdown Input/Output angeben

    @Override
    default void shutdownInput() {
        Input.super.shutdownInput();
    }

    @Override
    default void shutdownOutput() {
        Output.super.shutdownOutput();
    }
}
