package civitas.celestis.util.collection.list;

import civitas.celestis.util.Pair;
import civitas.celestis.util.collection.LCollection;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * <h2>LList</h2>
 * <p>
 * An ordered version of {@link LCollection}.
 * </p>
 */
public interface LList<E> extends LCollection<E>, List<E> {
    /**
     * Creates a new list from variable arguments of {@link T}.
     *
     * @param elements Array of elements
     * @param <T>      Type of element to hold
     * @return List of elements
     */
    @SafeVarargs
    @Nonnull
    static <T> LList<T> of(@Nonnull T... elements) {
        return new LArray<>(elements);
    }

    /**
     * Gets a list of all possible permutations of this list.
     * <p>
     * Duplicate permutations and self-permutations are not included in this list.
     * </p>
     *
     * @return
     */
    @Nonnull
    LList<Pair<E>> pairs();
}
