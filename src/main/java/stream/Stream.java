package stream;

import fpinjava.Effect;
import fpinjava.Supplier;



public abstract class Stream<A> {
    private static Stream EMPTY = new Empty();

    public abstract A head();

    public abstract Stream<A> tail();

    public abstract Boolean isEmpty();

    public abstract void forEach(Effect<A> ef);

    // geändet auf Public
    public Stream() {
    }


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

        // neu

        public void forEach(Effect<A> ef) {
            // Do nothing
        }
    }

        // geändet auf Public??
        public static class Cons<A> extends Stream<A> {
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
            public void forEach(Effect<A> ef) {
                ef.apply(h);
                t.forEach(ef);
            }

        }
            public static <A> Stream<A> cons(Supplier<A> hd, Supplier<Stream<A>> tl) {
                return new Cons<>(hd, tl);
            }

            public static <A> Stream<A> cons(Supplier<A> hd, Stream<A> tl) {
                return new Cons<>(hd, () -> tl);
            }

            @SuppressWarnings("unchecked")
            public static <A> Stream<A> empty() {
                return EMPTY;
            }

            public static Stream<Integer> from(int i) {
                return cons(() -> i, () -> from(i + 1));
            }

}


