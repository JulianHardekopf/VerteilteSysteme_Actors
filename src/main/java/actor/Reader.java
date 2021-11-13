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
    public void tell(String message, Result<Actor<String>> sender) {
      inputObject.readLines().forEach(string -> sender.forEach(stringActor -> stringActor.tell(string, self())));
      //  sender.forEach(actor -> actor.tell(message, sender));

    }

    @Override
    public void onReceive(String message, Result<Actor<String>> sender) {
        /*
        Result<Tuple<String, Input>> tupleResult = message == null || message.equals("\u0004")
                // Oder tell("EOT", sender)
                ? tell(message + "\u0004")
                : inputObject.readLine(message);

      //sender.forEach(actor -> actor.tell(inputObject.readLine(message).toString(),sender));
        sender.forEach(actor -> actor.tell(message,sender));
*/
        if(inputObject.readLine() == null) {
            tell(message + "\u0004", sender);
        }

        sender.forEach(actor -> actor.tell(inputObject.readLine(message).toString(),sender));
    }
    // Ein einziger Aufruf der tell-Methode des Readers
    //ruft die tell-Methode des Absenders
    //für jede vom Input gelesene Textzeile auf.
}
