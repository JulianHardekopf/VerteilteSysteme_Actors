package stream;

import fpinjava.Effect;
import fpinjava.Result;
import fpinjava.Supplier;
import tuple.Tuple;

import java.util.function.Function;


public abstract class Stream<A> {
    private static Stream EMPTY = new Empty();
    public abstract A head();
    public abstract Stream<A> tail();
    public abstract Boolean isEmpty();
    public abstract void forEach(Effect<A> ef);
    private Stream() {}
    private static class Empty<A> extends Stream<A> {
        @Override
        public Stream<A> tail() {
            throw new IllegalStateException("tail called on empty");
        }
        @Override
        public A head() {
            throw new IllegalStateException("head called on empty");
        }
        @Override
        public Boolean isEmpty() {
            return true;
        }

        @Override
        public void forEach(Effect<A> ef) {
            // empty
        }
    }
    private static class Cons<A> extends Stream<A> {
        private final Supplier<A> head;
        private A h;
        private final Supplier<Stream<A>> tail;
        private Stream<A> t;
        private Cons(Supplier<A> h, Supplier<Stream<A>> t) {
            head = h;
            tail = t;
        }
        @Override
        public A head() {
            if (h == null) {
                h = head.get();
            }
            return h;
        }
        @Override
        public Stream<A> tail() {
            if (t == null) {
                t = tail.get();
            }
            return t;
        }
        @Override
        public Boolean isEmpty() {
            return false;
        }
        @Override
        public void forEach(Effect<A> ef) {
            ef.apply(head());
            tail().forEach(ef);
        }
    }
    static <A> Stream<A> cons(Supplier<A> hd, Supplier<Stream<A>> tl) {
        return new Cons<>(hd, tl);
    }
    static <A> Stream<A> cons(Supplier<A> hd, Stream<A> tl) {
        return new Cons<>(hd, () -> tl);
    }
    @SuppressWarnings("unchecked")
    public static <A> Stream<A> empty() {
        return EMPTY;
    }
    public static Stream<Integer> from(int i) {
        return cons(() -> i, () -> from(i + 1));
    }
    public static <A, S> Stream<A> unfold(S z, Function<S, Result<Tuple<A, S>>> f) {
        return f.apply(z).map(x -> cons(() -> x.fst,
                () -> unfold(x.snd, f))).getOrElse(empty());
    }

       public static Stream<Integer> fibs() {
        return unfold(new Tuple<>(1, 1), x -> Result.success(new Tuple<>(x.fst, new Tuple<>(x.snd, x.fst + x.snd))));
    }

}