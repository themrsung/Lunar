package civitas.celestis.object;

import jakarta.annotation.Nonnull;

/**
 * <h2>Nameable</h2>
 * <p>
 * A nameable object can be identified by a unique name.
 * The names uniqueness only extends to an arbitrary scope,
 * which is usually defined in the comments.
 * </p>
 */
public interface Nameable {
    /**
     * Gets the name of this object.
     *
     * @return Name
     */
    @Nonnull
    String getName();
}
