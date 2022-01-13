package actor;

import fpinjava.Result;
import inout.Input;
import inout.Output;
import inout.TCPReaderWriter;


public class Writer extends AbstractActor<String> {
    private final Input inputObject;
    private final Output outputObject;
    private final Reader reader;
    private final String EOT = "\u0004";
    private final boolean isTransceiver;



    public Writer(String id, Type type, Input inputObject, Output outputObject, boolean isTransceiver) {
        super(id, type);
        this.inputObject = inputObject;
        this.outputObject = outputObject;
        if(!isTransceiver) {
            reader = Reader.createReader("reader", Type.SERIAL,  inputObject , this);
        } else {
            reader = Reader.transceiver(inputObject, this);
        }
        this.isTransceiver = isTransceiver;
    }

    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        if(!isTransceiver && message.equals(EOT)) {
            outputObject.shutdownOutput();
        }
        if(isTransceiver && message.equals(EOT)) {
            outputObject.printLine(EOT);
            outputObject.shutdownOutput();
        } else {
            outputObject.printLine(message);
        }

    }

    public void start() {
        // Erzeugter Reader ruft bei Start die Tell Methode auf mit einem leeren String
        // und gibt als Referenz den Writer auf
        reader.tell("", self());
    }
    public void start(Result<Actor<String>> consumer) {
        reader.tell("", consumer);
    }

    public static Writer transceiver(Input in, Output out) {
        return new Writer("Transceiver", Type.SERIAL, in, out, true);
    }

}
