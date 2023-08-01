package civitas.celestis.util.collection.list;

import civitas.celestis.util.Unique;
import jakarta.annotation.Nonnull;

import java.util.Collection;
import java.util.UUID;

/**
 * <h2>LUniqueArray</h2>
 * <p>An array-list of unique elements.</p>
 *
 * @param <U> Type of unique element to hold
 */
public class LUniqueArray<U extends Unique> extends LArrayList<U> implements LUniqueList<U> {
    /**
     * Creates a new empty list.
     */
    public LUniqueArray() {
    }

    /**
     * Creates an empty list with given initial capacity.
     *
     * @param initialCapacity Initial capacity of this array
     */
    public LUniqueArray(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new list from an array.
     *
     * @param a   Array to use
     * @param <V> Type of element in array
     */
    public <V extends U> LUniqueArray(@Nonnull V[] a) {
        super(a);
    }

    /**
     * Creates a new list from an existing collection.
     *
     * @param c Collection to use
     */
    public LUniqueArray(@Nonnull Collection<? extends U> c) {
        super(c);
    }

    @Nonnull
    @Override
    public U get(@Nonnull UUID uniqueId) throws NullPointerException {
        for (U u : this) {
            if (u.getUniqueId().equals(uniqueId)) return u;
        }

        throw new NullPointerException("Object of unique identifier " + uniqueId + " cannot be found.");
    }
}
