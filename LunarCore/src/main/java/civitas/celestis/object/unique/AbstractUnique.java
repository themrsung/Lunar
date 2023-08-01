package civitas.celestis.object.unique;

import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * <h2>AbstractUnique</h2>
 * <p>An adapter class for {@link Unique}.</p>
 */
public abstract class AbstractUnique implements Unique {
    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     */
    public AbstractUnique(@Nonnull UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Nonnull
    private final UUID uniqueId;

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }
}
