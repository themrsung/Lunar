package civitas.celestis.world.internal;

import civitas.celestis.object.world.World;
import civitas.celestis.world.WorldManager;
import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * <h2>SyncWorldManager</h2>
 * <p>
 * A synchronous world manager.
 * </p>
 */
public final class SyncWorldManager implements WorldManager {
    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Nonnull
    @Override
    public List<World> getWorlds() {
        return List.copyOf(worlds);
    }

    @Nonnull
    @Override
    public World getWorld(@Nonnull UUID uniqueId) throws NullPointerException {
        for (final World w : getWorlds()) {
            if (w.getUniqueId().equals(uniqueId)) return w;
        }

        throw new NullPointerException("World of unique identifier " + uniqueId + " cannot be found.");
    }

    @Override
    public void addWorld(@Nonnull World world) {
        worlds.add(world);
        times.put(world, System.currentTimeMillis());
    }

    @Override
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
        times.remove(world);
    }

    private final List<World> worlds = new ArrayList<>();
    private final Map<World, Long> times = new HashMap<>();
    private final Thread thread = new Thread(() -> {
        //
        // Start of world manager thread
        //

        while (true) {
            // Respect interruption
            if (Thread.interrupted()) return;

            // Iterate through worlds
            for (final World w : List.copyOf(worlds)) {
                // Get current time
                final long now = System.currentTimeMillis();

                // Get previous tick time
                final long previous = times.getOrDefault(w, now);

                // Calculate delta
                final long delta = now - previous;

                // Prevent delta of less than 1 ms
                if (delta < 1) continue;

                // Tick world
                w.tick(delta);

                // Keep time
                times.put(w, now);
            }
        }

        //
        // End of world manager thread
        //
    }, "SyncWorldManager");
}
