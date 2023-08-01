package civitas.celestis.util.collection.list;

import civitas.celestis.util.Pair;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <h2>LArray</h2>
 * <p>
 * A specialized version of {@link ArrayList}.
 * This class inherits all the properties of {@link LList} and its parent classes.
 * </p>
 */
public class LArray<E> extends ArrayList<E> implements LList<E> {
    /**
     * Creates a new empty list.
     */
    public LArray() {
    }

    /**
     * Creates a new list with given initial capacity.
     *
     * @param initialCapacity Initial capacity of list
     */
    public LArray(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new list from an array,
     *
     * @param a   Array of elements
     * @param <F> Any subtype of {@link E}
     */
    @SafeVarargs
    public <F extends E> LArray(@Nonnull F... a) {
        this(Arrays.asList(a));
    }

    /**
     * Creates a new list from an existing collection.
     *
     * @param c Collection to use
     */
    public LArray(@Nonnull Collection<? extends E> c) {
        super(c);
    }

    @Nonnull
    @Override
    public Stream<E> filter(@Nonnull Predicate<E> filter) {
        return stream().filter(filter);
    }

    @Nonnull
    @Override
    public <F extends E> Stream<F> map(@Nonnull Function<? super E, F> mapper) {
        return stream().map(mapper);
    }

    @Override
    public void forEach(@Nonnull BiConsumer<Integer, E> action) {
        for (int i = 0; i < size(); i++) {
            action.accept(i, get(i));
        }
    }

    @Nonnull
    @Override
    public LList<Pair<E>> pairs() {
        final LList<Pair<E>> pairs = new LArray<>(size());

        forEach(a -> filter(e -> !e.equals(a)).forEach(b -> {
            final Pair<E> pair = new Pair<>(a, b);

            if (!pairs.contains(pair)) pairs.add(pair);
        }));

        return pairs;
    }
}
