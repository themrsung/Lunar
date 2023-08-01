package civitas.celestis.util.collection.list;

import civitas.celestis.util.Unique;
import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * <h2>LUniqueList</h2>
 * <p>A list of unique elements.</p>
 *
 * @param <U> Class of unique element
 */
public interface LUniqueList<U extends Unique> extends LList<U> {
    /**
     * Gets an element by unique identifier.
     * <p>
     * Note that since duplicate entries are not prevented by {@link LUniqueList},
     * this method may result in ignoring the second instance of a given object.
     * </p>
     *
     * @param uniqueId Unique identifier of element
     * @return Element of unique identifier
     * @throws NullPointerException When an object of unique identifier cannot be found
     */
    @Nonnull
    U get(@Nonnull UUID uniqueId) throws NullPointerException;
}
