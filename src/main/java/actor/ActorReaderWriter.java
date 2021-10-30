package actor;
import fpinjava.Result;
import inout.Input;
import inout.InputOutput;
import tuple.Tuple;


public class ActorReaderWriter extends AbstractActor<String> implements InputOutput {
    private  ActorReader actorReader;
    private  Actor<String> actor;
    public ActorReaderWriter(String id, Actor<String> actor, long timeout) {
        // Type.Serial idk nur damit Fehlermeldung weggeht
        super(id, Type.SERIAL);

        // Konstruktor match nicht mit der vorgebenenen Fabrikmethode von Krohn

        // this.actorReader = actorReader;
        // this.actor = actor;

    }

    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        // Nachricht wieder reinholen
        actorReader.tell(message);
    }

    @Override
    public Result<Tuple<String, Input>> readLine() {
        // Das Interface Input können Sie dann ganz einfach implementieren,
        // indem Sie die Funktionalität an den ActorReader delegieren ???
        // so ?
        return actorReader.readLine();
    }

    @Override
    public void print(String s) {
        // richtig
        actor.tell(s ,self());
    }
    static ActorReaderWriter actorReaderWriter(String id, Actor<String> actor, long timeout) {
        // richtig
        return new ActorReaderWriter(id, actor, timeout);
    }
    @Override
    public void close() throws Exception {

    }
}
