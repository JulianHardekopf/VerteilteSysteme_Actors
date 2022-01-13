package netcat.uni;

import actor.Actor;
import actor.Writer;
import inout.*;

import static java.lang.Thread.sleep;


public class Netcat {
    //Programmieren Sie für Ihr unidirektionales Netcat einen Parametrized JUnit-Test (netcat.uni.tcp.NetcatTest).
    //Die Testmethode hat einen Parameter vom Typ String,
    //der als Testeingabe für den Input des Netcat-Client dient.
    //Verwenden Sie den ProcessBuilder.
    //Die start-Methode des ProcessBuilders liefert für Client und Server
    //jeweils ein Process-Objekt.
    //Mithilfe der Methoden getInputStream und getOutputStream der Process-Objekte,
    //können Sie beim Netcat-Client die Testeingabe einspeisen und
    //beim Netcat-Server die Ausgabe ablesen.

    public static void main(String[] args) throws Exception {


        Actor<String> producer = null;
        if(args.length == 2) {
            Writer clientWriter = new Writer("netcatclient", Actor.Type.SERIAL, ConsoleReader.stdin(),
                    TCPWriter.connectTo(args[0], Integer.parseInt(args[1])).call(), false);
            clientWriter.start();

        } if(args.length == 1) {
            Writer serverReader = new Writer("netcatserver", Actor.Type.SERIAL,
                    TCPReader.accept(Integer.parseInt(args[0])).call(), ConsoleWriter.stdout(), false);
            serverReader.start();

        }
    }

}
