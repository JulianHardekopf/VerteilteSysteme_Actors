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
    public void onReceive(String message, Result<Actor<String>> sender) {
        // Writer schreibt jede Empfange Nachricht auf das Output Objekt
        // Bei EOF wir das outputObjekt geschlossen
        if(message.equals("\u0004")) {
            outputObject.shutdownOutput();
        } else {
            outputObject.printLine(message);
        }

    }
    public void start() {
        // Erzeugter Reader ruft bei Start die Tell Methode auf mit einem leeren String
        // und gibt als Referenz den Writer auf
        reader.tell("",self());
    }
}
