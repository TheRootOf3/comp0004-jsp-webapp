package comp0004.util;

public class Pair<E> {
    private final E item1;
    private final E item2;

    public Pair(E item1, E item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public E get1() {
        return this.item1;
    }

    public E get2() {
        return this.item2;
    }
}
