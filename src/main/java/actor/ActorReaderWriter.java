package actor;
import fpinjava.Result;
import inout.Input;
import inout.InputOutput;
import tuple.Tuple;


public class ActorReaderWriter extends AbstractActor<String> implements InputOutput {
    private final ActorReader actorReader;
    private final Actor<String> actor;
    public ActorReaderWriter(String id, Actor<String> actor,  long timeout) {
        super(id, Type.SERIAL);
        this.actor = actor;
        actorReader = new ActorReader("actorreader", Type.SERIAL, 1000);
    }

    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        actorReader.tell(message, sender);

    }

    @Override
    public Result<Tuple<String, Input>> readLine() {
        return actorReader.readLine();

    }


    @Override
    public void print(String s) {
        actor.tell(s ,self());
    }
    /*
    static ActorReaderWriter actorReaderWriter(String id, Actor<String> actor, long timeout, ActorReader actorReader) {
        return new ActorReaderWriter(id, actor, timeout, actorReader);
    }


     */
    @Override
    public void close() throws Exception {

    }
}
