package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * <h2>Tuple</h2>
 * <p>
 * A group of three elements.
 * Unlike {@link Pair}, the order of the elements matter.
 * </p>
 *
 * @param a   First element of this tuple
 * @param b   Second element of this tuple
 * @param c   Third element of this tuple
 * @param <T> Type of element to contain
 */
public record Tuple<T>(
        @Nonnull T a,
        @Nonnull T b,
        @Nonnull T c
) implements Iterable<T> {
    /**
     * Creates a new tuple from three objects.
     *
     * @param a   First object of tuple
     * @param b   Second object of tuple
     * @param c   Third object of tuple
     * @param <A> Class of first object
     * @param <B> Class of second object
     * @param <C> Class of third object
     * @param <D> Shared superclass of {@link A}, {@link B}, {@link C}
     * @return Tuple of three objects
     */
    @Nonnull
    public <A extends D, B extends D, C extends D, D> Tuple<D> of(@Nonnull A a, @Nonnull B b, @Nonnull C c) {
        return new Tuple<>(a, b, c);
    }

    /**
     * Checks if this tuple contains given object.
     *
     * @param obj Object to check
     * @return {@code true} if at least one of the elements equals given object
     */
    public boolean contains(@Nullable Object obj) {
        return Objects.equals(a, obj) || Objects.equals(b, obj) || Objects.equals(c, obj);
    }

    /**
     * Given one object, this returns the other two.
     * When the object is not a member of this tuple, this will throw an {@link IllegalArgumentException}.
     *
     * @param obj Object to check for
     * @return The other two objects
     */
    @Nonnull
    public Pair<T> other(@Nonnull T obj) {
        if (a.equals(obj)) return new Pair<>(b, c);
        if (b.equals(obj)) return new Pair<>(a, c);
        if (c.equals(obj)) return new Pair<>(a, b);

        throw new IllegalArgumentException("Given object is not member of this tuple.");
    }

    /**
     * Checks for equality without regards to the order of elements.
     *
     * @param other Other tuple to compare to
     * @return {@code true} if {@code this} contains all the elements of the other
     */
    public boolean equalsIgnoreOrder(@Nonnull Tuple<?> other) {
        return contains(other.a()) && contains(other.b()) && contains(other.c());
    }

    /**
     * Gets an iterator of the three objects of this tuple.
     *
     * @return Iterator of three objects
     */
    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return List.of(a, b, c).iterator();
    }

    /**
     * Performs given operation to each element of this tuple,
     * then returns the resulting tuple.
     *
     * @param operation Operation to perform
     * @return Resulting tuple
     */
    @Nonnull
    public Tuple<T> apply(@Nonnull UnaryOperator<T> operation) {
        return new Tuple<>(
                operation.apply(a),
                operation.apply(b),
                operation.apply(c)
        );
    }
}
