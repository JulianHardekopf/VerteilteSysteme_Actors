package actor;

import fpinjava.Result;
import inout.Input;
import tuple.Tuple;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ActorReader extends AbstractActor<String> implements Input {
    private final long timeout;
    private final int capacityOfQueue = 50;
    BlockingQueue<String> blockingQueue
            = new LinkedBlockingQueue<>(
            capacityOfQueue);

    public ActorReader(String id, Type type, long timeout) {
        super(id, type);
        this.timeout = timeout;

    }

    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        //  was soll getan werden, wenn eine Nachricht erhalten wird?
        // hier die Nachricht in die BlockingQueue gespeichert
        // Dies muss weiter an die Readline gegeben werden

            try {
                blockingQueue.offer(message);
            } catch (NullPointerException e) {
                blockingQueue.remove();
            }

    }


    @Override
    public Result<Tuple<String , Input>> readLine() {
        // Die BlockingQueue hält die Messages
        // poll Methode wartet timeout lang auf ein Element
        // schmeißt sonst das Result.failure
        // Tuple evtl. anders aufbauen
        return Result.of(() -> blockingQueue.poll(timeout, TimeUnit.MILLISECONDS)).map(s -> new Tuple<>(s, this));

    }
    static Input actorReader(String id, Type type, long timeout) {

        return new ActorReader(id, type, timeout);
    }
    @Override
    public void close() throws Exception {

    }


}


