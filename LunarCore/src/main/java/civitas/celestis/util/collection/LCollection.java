package civitas.celestis.util.collection;

import jakarta.annotation.Nonnull;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <h2>LCollection</h2>
 * <p>
 * A specialized collection of objects.
 * LCollection adds additional features to {@link Collection}.
 * </p>
 * <p>
 * Note that this class has overhead relative to native Java collections,
 * and must be used sparingly.
 * </p>
 */
public interface LCollection<E> extends Collection<E> {
    /**
     * Applies a filter to each element of this collection,
     * then returns the resulting stream.
     *
     * @param filter Filter to apply
     * @return Stream of filtered objects
     */
    @Nonnull
    Stream<E> filter(@Nonnull Predicate<E> filter);

    /**
     * Applies a mapper function to all elements of this collection,
     * then returns the resulting stream.
     *
     * @param mapper Mapper to apply
     * @param <F>    Class of return value's element
     * @return Stream of mapped objects
     */
    @Nonnull
    <F extends E> Stream<F> map(@Nonnull Function<? super E, F> mapper);

    /**
     * Performs an action for each element of this collection.
     *
     * @param action Action to execute
     */
    void forEach(@Nonnull BiConsumer<Integer, E> action);
}
