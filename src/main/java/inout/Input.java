package inout;


import fpinjava.Result;
import tuple.Tuple;

import java.util.stream.Stream;


public interface Input extends AutoCloseable {




    Result<Tuple<String, Input>> readLine();

    // Resulttyp Tuple<Integer,Input> ! Buch Seite 375
    // ret und sus rausfinden
    // aktuell seite 158
    // Falls faltung S.160
    default Result<Tuple<Integer, Input>> readInt() {
        return readLine().flatMap(s -> readMaybe(s.fst).map(a -> new Tuple<>(a, s.snd)));
    }

    default Result<Tuple<String, Input>> readLine(String message) {
        return readLine();
    }
    default Result<Tuple<Integer, Input>> readInt(String message) {
        return readInt();
    }


    static Result<Integer> readMaybe(String s){
        return Result.of(()->Integer.parseInt(s));
    }
/*
    default Stream<String> readLines() {
        return Stream.<String,Input>unfold(this, Input::readLine);
    }

    default Stream<Integer> readInts() {
        return Stream.<Integer,Input>unfold(this, Input::readInt);
    }
*/
    default void shutdownInput(){
        try {
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
