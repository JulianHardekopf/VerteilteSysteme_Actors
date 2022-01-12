package actor;

import fpinjava.Result;
import inout.Input;
import inout.InputOutput;
import inout.Output;
import inout.TCPReaderWriter;


public class Writer extends AbstractActor<String> {
    private final Input inputObject;
    private final Output outputObject;
    private final Reader reader;
    private final String EOT = "\u0004";
    private static boolean isTransceiver = false;



    public Writer(String id, Type type, Input inputObject, Output outputObject) {
        super(id, type);
        this.inputObject = inputObject;
        this.outputObject = outputObject;
        reader = Reader.createReader("reader", Type.SERIAL,  inputObject , this);

    }

    /*
    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        // Writer schreibt jede Empfange Nachricht auf das Output Objekt
        // Bei EOF wir das outputObjekt geschlossen

        if(message.equals(EOT)) {
            outputObject.shutdownOutput();
        } else {
            outputObject.printLine(message);
        }

    } */
    // Änderung für den Transceiver -> EOT wird auf dem Output geschrieben und dann geschlossen.
    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        if(!isTransceiver && message.equals(EOT)) {
            outputObject.shutdownOutput();
        } if(!isTransceiver) {
            outputObject.printLine(message);
        }

        if(isTransceiver && message.equals(EOT)) {
            outputObject.printLine(EOT);
            outputObject.shutdownOutput();

        } if(isTransceiver) {
            outputObject.printLine(message);

        }

    }

    public void start() {
        // Erzeugter Reader ruft bei Start die Tell Methode auf mit einem leeren String
        // und gibt als Referenz den Writer auf
        isTransceiver = false;
        reader.tell("", self());
    }
    public void start(Result<Actor<String>> consumer) {

        reader.tell("", consumer);
    }
    public static Writer transceiver(Input in, Output out) throws Exception {
        isTransceiver = true;
        return new Writer("Transceiver", Type.SERIAL, in, out);
    }

}
