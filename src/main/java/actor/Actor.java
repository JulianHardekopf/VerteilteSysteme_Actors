package actor;

import fpinjava.Result;
import inout.Input;
import tuple.Tuple;

public interface Actor<T> {
    static <T> Result<Actor<T>> noSender() {
        return Result.empty();
    }
    Result<Actor<T>> self();
    ActorContext<T> getContext();
    default void tell(T message) {
        tell(message, self());
    }
    void tell(T message, Result<Actor<T>> sender);
    void shutdown();
    default void tell(T message, Actor<T> sender) {
        tell(message, Result.of(sender));
    }



    enum Type {SERIAL, PARALLEL}

}