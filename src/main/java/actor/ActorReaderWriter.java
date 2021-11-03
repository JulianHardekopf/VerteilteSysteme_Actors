package actor;
import fpinjava.Result;
import inout.Input;
import inout.InputOutput;
import tuple.Tuple;


public class ActorReaderWriter extends AbstractActor<String> implements InputOutput {
    private  final ActorReader actorReader;
    private  final Actor<String> actor;
    public ActorReaderWriter(String id, Actor<String> actor, long timeout, ActorReader actorReader, Actor<String> actor1) {
        super(id, Type.SERIAL);
        this.actorReader = actorReader;
        this.actor = actor1;
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
    static ActorReaderWriter actorReaderWriter(String id, Actor<String> actor, long timeout, ActorReader actorReader, Actor<String> actor1) {
        return new ActorReaderWriter(id, actor, timeout, actorReader, actor1);
    }
    @Override
    public void close() throws Exception {

    }
}
