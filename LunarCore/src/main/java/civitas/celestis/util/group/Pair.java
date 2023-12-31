package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * <h2>Pair</h2>
 * <p>
 * A pair of two elements.
 * Given objects {@code A, B}, {@code Pair{A, B} == Pair{B, A}}.
 * </p>
 *
 * @param a   First element of this pair
 * @param b   Second element of this pair
 * @param <T> Class of elements to hold in this pair
 */
public record Pair<T>(@Nonnull T a, @Nonnull T b) implements Iterable<T> {
    /**
     * Creates a pair from two objects.
     *
     * @param a   First object of pair
     * @param b   Second object of pair
     * @param <A> Class of first object
     * @param <B> Class of second object
     * @param <C> Shared superclass of {@link A} and {@link B}
     * @return Pair of two objects
     */
    @Nonnull
    public <A extends C, B extends C, C> Pair<C> of(@Nonnull A a, @Nonnull B b) {
        return new Pair<>(a, b);
    }

    /**
     * Checks if this pair contains given object.
     *
     * @param obj Object to check
     * @return {@code true} if either the first or second object is equal to given object
     */
    public boolean contains(@Nullable Object obj) {
        return Objects.equals(a, obj) || Objects.equals(b, obj);
    }

    /**
     * Given one object, this returns the other.
     * When the object is not a member of this pair, this will throw an {@link IllegalArgumentException}.
     *
     * @param obj Object to check for
     * @return The other object
     */
    @Nonnull
    public T other(@Nonnull T obj) {
        if (a.equals(obj)) return b;
        if (b.equals(obj)) return a;

        throw new IllegalArgumentException("Given object is not a member of this pair.");
    }

    /**
     * Checks for equality.
     *
     * @param o Object to compare to
     * @return {@code true} if given object is a pair, and the two components match
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Pair<?> pair)) return false;
        return (Objects.equals(a, pair.a) && Objects.equals(b, pair.b)) ||
                (Objects.equals(a, pair.b) && Objects.equals(b, pair.a));
    }

    /**
     * Gets an iterator of this pair.
     *
     * @return Iterator of pair
     */
    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return List.of(a, b).iterator();
    }

    /**
     * Performs given operation to both objects of this pair,
     * then returns the resulting pair.
     *
     * @param operation Operation to perform
     * @return Resulting pair
     */
    @Nonnull
    public Pair<T> apply(@Nonnull UnaryOperator<T> operation) {
        return new Pair<>(
                operation.apply(a),
                operation.apply(b)
        );
    }
}
