package list;

import fpinjava.TailCall;

import java.util.function.Function;

import static fpinjava.TailCall.ret;
import static fpinjava.TailCall.sus;

public abstract class List<A> {
    public abstract A head();
    public abstract List<A> tail();
    public abstract boolean isEmpty();
    public abstract List<A> setHead(A h);
    public abstract List<A> dropWhile(Function<A, Boolean> f);


    @SuppressWarnings("rawtypes")
    public static final List NIL = new Nil();
    private List() {}

    private static class Nil<A> extends List<A> {
        private Nil() {}
        public A head() {
            throw new IllegalStateException("head called en empty list");
        }
        public List<A> tail() {
            throw new IllegalStateException("tail called en empty list");
        }
        public boolean isEmpty() {
            return true;
        }

        @Override
        public List<A> setHead(A h) {
            throw new IllegalStateException("setHead called on empty list");
        }

        @Override
        public List<A> dropWhile(Function<A, Boolean> f) {
            return this;
        }



        public <B> B foldRight(B identity, Function<A, Function<B, B>> f) {
            return reverse().foldLeft(identity, x -> y -> f.apply(y).apply(x));
        }

        public List<A> cons(A a) {
            return new Cons<>(a, this);
        }
        public List<A> drop(int n) {
            return this;
        }
        public String toString() {
            return "[NIL]";
        }
    }
    private static class Cons<A> extends List<A> {
        private final A head;
        private final List<A> tail;
        private Cons(A head, List<A> tail) {
            this.head = head;
            this.tail = tail;
        }
        public A head() {
            return head;
        }
        public List<A> tail() {
            return tail;
        }
        public boolean isEmpty() {
            return false;
        }

        @Override
        public List<A> setHead(A h) {
           return new Cons<>(h, tail());
        }

        @Override
        public List<A> dropWhile(Function<A, Boolean> f) {
            return dropWhile_(this, f).eval();
        }
        private TailCall<List<A>> dropWhile_(List<A> list,
                                             Function<A, Boolean> f) {
            return !list.isEmpty() && f.apply(list.head())
                    ? sus(() -> dropWhile_(list.tail(), f))
                    : ret(list);
        }

    }
    @SuppressWarnings("unchecked")
    public static <A> List<A> list() {
        return NIL;
    }

    @SafeVarargs
    public static <A> List<A> list(A... a) {
        List<A> n = list();
        for (int i = a.length - 1; i >= 0; i--) {
            n = new Cons<>(a[i], n);
        }
        return n;
    }
    public List<A> cons(A a) {
        return new Cons<>(a, this);
    }
    public List<A> drop(int n) {
        return n <= 0
                ? this
                : drop_(this, n).eval();
    }
    private TailCall<List<A>> drop_(List<A> list, int n) {
        return n <= 0 || list.isEmpty()
                ? ret(list)
                : sus(() -> drop_(list.tail(), n - 1));
    }

    public List<A> reverse() {
        return reverse_(list(), this).eval();
    }
    private TailCall<List<A>> reverse_(List<A> acc, List<A> list) {
        return list.isEmpty()
                ? ret(acc)
                : sus(() -> reverse_(new Cons<>(list.head(), acc), list.tail()));
    }

    public String toString() {
        return String.format("[%sNIL]",
                toString(new StringBuilder(), this).eval());
    }

    public <B> B foldLeft(B identity, Function<B, Function<A, B>> f) {
        return foldLeft_(identity, this, f).eval();
    }
    private <B> TailCall<B> foldLeft_(B acc, List<A> list,
                                      Function<B, Function<A, B>> f) {
        return list.isEmpty()
                ? ret(acc)
                : sus(() -> foldLeft_(f.apply(acc).apply(list.head()),
                list.tail(), f));
    }



    public <B> B foldRight(B identity, Function<A, Function<B, B>> f) {
        return foldRight_(identity, this.reverse(), identity, f).eval();
    }
    private <B> TailCall<B> foldRight_(B acc, List<A> ts, B identity,
                                       Function<A, Function<B, B>> f) {
        return ts.isEmpty()
                ? ret(acc)
                : sus(() -> foldRight_(f.apply(ts.head()).apply(acc),
                ts.tail(), identity, f));
    }

    public static <A> List<A> concat(List<A> list1, List<A> list2) {
        return list1.reverse().foldLeft(list2, x -> x::cons);
    }
    // Seite 171 ?? implement in parent class
    /* public static <A> List<A> flatten(List<List<A>> list) {
        return foldRight(list, List.<A>list(), x -> y -> concat(x,y));
    }
*/


    private TailCall<StringBuilder> toString(StringBuilder acc, List<A> list) {
        return list.isEmpty()
                ? ret(acc)
                : sus(() -> toString(acc.append(list.head()).append(", "),
                list.tail()));
    }


}