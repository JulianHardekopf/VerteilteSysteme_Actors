package actor;

import fpinjava.Result;
import inout.Input;

public class Reader extends AbstractActor<String> {
    private final Input inputObject;
    public Reader(String id, Type type, Input inputObject) {
        super(id, type);
        this.inputObject = inputObject;
    }


    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        //Textzeilen werden als Stream verarbeitet und an den Writer übergeben
        inputObject.readLines().forEach(s -> sender.forEach(stringActor -> stringActor.tell(s)));
        // Bei erfolgreichen einlesen wird dem Writer am ende EOT mitgeteilt
        sender.successValue().tell("\u0004", self());
    }

}
