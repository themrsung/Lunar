package civitas.celestis.util;

/**
 * <h2>Reiterator</h2>
 * <p>
 *
 * </p>
 */
public class Reiterator {
    /**
     * Creates a new reiterator.
     *
     * @param limit Upper limit of this reiterator
     */
    public Reiterator(int limit) {
        this(limit, 0);
    }

    /**
     * Creates a new reiterator.
     *
     * @param limit Upper limit of this reiterator
     * @param i     Initial counter of this reiterator
     */
    public Reiterator(int limit, int i) {
        this.limit = limit;
        this.i = i;
    }

    private final int limit;
    private int i;

    /**
     * Gets the upper limit of this reiterator.
     *
     * @return Upper limit
     */
    public int limit() {
        return limit;
    }

    /**
     * Increments this iterator, then returns the incremented value.
     *
     * @return Incremented iterator
     */
    public int next() {
        return getNextIterator();
    }

    protected final int getIterator() {
        return i;
    }

    protected final int getNextIterator() {
        if (i + 1 <= limit) {
            return i++;
        } else {
            i = 0;
            return 0;
        }
    }

}
