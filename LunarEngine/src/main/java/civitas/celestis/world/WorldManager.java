package civitas.celestis.world;

import civitas.celestis.object.world.World;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

/**
 * <h2>WorldManager</h2>
 * <p>
 *     A primary module of Lunar Engine.
 *     Handles the lifecycle of worlds.
 * </p>
 */
public interface WorldManager {
    /**
     * Starts ticking worlds.
     */
    void start();

    /**
     * Stops ticking worlds.
     */
    void stop();

    /**
     * Gets a list of worlds.
     * @return List of worlds
     */
    @Nonnull
    List<World> getWorlds();

    /**
     * Gets a world by unique identifier.
     * @param uniqueId Unique identifier of world
     * @return World of unique identifier
     * @throws NullPointerException When a world of unique identifier cannot be found
     */
    @Nonnull
    World getWorld(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Adds a world to this manager.
     * @param world World to add
     */
    void addWorld(@Nonnull World world);

    /**
     * Removes a world from this manager.
     * @param world World to remove
     */
    void removeWorld(@Nonnull World world);
}
