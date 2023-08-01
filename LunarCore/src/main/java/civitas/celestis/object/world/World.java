package civitas.celestis.object.world;

import civitas.celestis.object.Nameable;
import civitas.celestis.object.Tickable;
import civitas.celestis.object.base.BaseObject;
import civitas.celestis.object.unique.Unique;
import jakarta.annotation.Nonnull;

import java.util.Collection;
import java.util.List;

/**
 * <h2>World</h2>
 * <p>
 * An in-game representation of a world.
 * </p>
 */
public interface World extends Unique, Nameable, Tickable {
    /**
     * Gets a list of objects in this world.
     *
     * @return List of objects
     */
    @Nonnull
    List<BaseObject> getObjects();

    /**
     * Adds an object to this world.
     *
     * @param obj Object to add
     */
    void addObject(@Nonnull BaseObject obj);

    /**
     * Adds multiple objects to this world.
     *
     * @param obj Collection of objects to add
     */
    void addObjects(@Nonnull Collection<BaseObject> obj);

    /**
     * Removes an object from this world.
     *
     * @param obj Object to remove
     */
    void removeObject(@Nonnull BaseObject obj);

    /**
     * Removes multiple objects from this world.
     *
     * @param obj Collection of objects to remove
     */
    void removeObjects(@Nonnull Collection<BaseObject> obj);

    /**
     * Default tick behavior.
     * Ticks every object in this world.
     *
     * @param delta Duration between the last tick and now in milliseconds.
     */
    @Override
    default void tick(long delta) {
        for (final BaseObject o : getObjects()) {
            o.tick(delta);
        }
    }
}
