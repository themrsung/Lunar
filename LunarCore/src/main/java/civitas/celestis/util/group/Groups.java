package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Groups</h2>
 * <p>Contains utility methods related to groups.</p>
 */
public final class Groups {
    /**
     * Given a list of elements of type {@link T}, this returns a list of {@link Pair}
     * containing given types.
     * <p>
     * Duplicate pairs ({@code Pair{A, B}, Pair{B, A}}) are not counted.
     * Self-pairs ({@code Pair{C, C}} are also not counted.
     * </p>
     *
     * @param list List to get pairs of
     * @param <T>  Type of elements of list
     * @return List of pairs of list
     */
    @Nonnull
    public static <T> List<Pair<T>> pairsOf(@Nonnull List<T> list) {
        final List<Pair<T>> pairs = new ArrayList<>();

        list.forEach(a -> list.stream().filter(o -> !o.equals(a)).forEach(b -> {
            final Pair<T> pair = new Pair<>(a, b);

            if (!pairs.contains(pair)) pairs.add(pair);
        }));

        return pairs;
    }
}
