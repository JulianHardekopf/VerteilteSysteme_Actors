package actor;

import fpinjava.Result;
import inout.Input;
import inout.Output;
import inout.ScriptReader;


public class Writer extends AbstractActor<String> {
    private final Input inputObject;
    private final Output outputObject;
    private final Reader reader;

    public Writer(String id, Type type, Input inputObject, Output outputObject) {
        super(id, type);
        this.inputObject = inputObject;
        this.outputObject = outputObject;
        this.reader = new Reader(id, type, inputObject);
    }

    @Override
    public void tell(String message, Result<Actor<String>> sender) {
        outputObject.printLine(message);
    }

    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        // Der Writer schreibt jeden String, den seine tell-Methode empfängt, auf das Output-Objekt.
        // reader.tell(message, sender);
        reader.tell(inputObject.readLine(message).toString(), sender);
        if(message.equals("\u0004")) {
            outputObject.shutdownOutput();
        }
    }
    public void start() {
        reader.tell("",self());
    }
}
