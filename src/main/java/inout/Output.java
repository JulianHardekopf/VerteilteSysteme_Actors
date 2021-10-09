package inout;

public interface Output extends AutoCloseable {

    void print(String s);

    default void printLine(String s){
        print(s+"\n");
    };

    default void shutdownOutput(){
        try {
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
