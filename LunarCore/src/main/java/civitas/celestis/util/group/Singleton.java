package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;

import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * <h2>Singleton</h2>
 * <p>
 * An immutable pointer to one element.
 * The usefulness of this class is questionable.
 * </p>
 * <p>
 * Have you ever wanted to iterate through one element?
 * Here you go!
 * </p>
 *
 * @param element Element to point to
 * @param <T>     Class of element to point to
 */
public record Singleton<T>(@Nonnull T element) implements Iterable<T> {
    /**
     * Gets an iterator of this singleton. (which will always have one element)
     *
     * @return Iterator of one element
     */
    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return List.of(element).iterator();
    }

    /**
     * Applies given operation to element, then returns a new pointer.
     *
     * @param operation Operation to perform
     * @return Resulting singleton
     */
    @Nonnull
    public Singleton<T> apply(@Nonnull UnaryOperator<T> operation) {
        return new Singleton<>(operation.apply(element));
    }
}
