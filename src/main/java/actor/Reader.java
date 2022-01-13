package actor;

import fpinjava.Result;
import inout.Input;

public class Reader extends AbstractActor<String> {
    private final Input inputObject;
    private final String EOT = "\u0004";
    private final Actor<String> producer;
    private final boolean isTransceiver;
    public Reader(String id, Type type, Input inputObject, Actor<String> producer, boolean isTransceiver) {
        super(id, type);
        this.inputObject = inputObject;
        this.producer = producer;
        this.isTransceiver = isTransceiver;
    }

    // Änderung für Transceiver -> sendet kein EOT zeichen
    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        if(isTransceiver) {
            inputObject.readLines().forEach(s -> sender.forEach(ac -> ac.tell(s, producer)));
        } else {
            //Textzeilen werden als Stream verarbeitet und an den Writer übergeben
            inputObject.readLines().forEach(s -> sender.forEach(stringActor -> stringActor.tell(s)));
            // Bei erfolgreichen einlesen wird dem Writer am ende EOT mitgeteilt
            sender.forEach(a -> a.tell(EOT, self()));
        }

    }

    public static Reader createReader(String id, Type type, Input input, Actor<String> producer) {
        return new Reader(id, type, input, producer, false);
    }
    public static Reader transceiver(Input in, Actor<String> producer) {
        return new Reader("transceiver", Type.SERIAL, in, producer, true);
    }

}
