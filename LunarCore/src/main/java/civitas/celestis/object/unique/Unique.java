package civitas.celestis.object.unique;

import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * <h2>Unique</h2>
 * <p>Unique objects have a unique identifier, which is unique per instance.</p>
 */
public interface Unique {
    /**
     * Gets the unique identifier of this object.
     *
     * @return Unique identifier
     */
    @Nonnull
    UUID getUniqueId();
}
