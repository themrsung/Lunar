package civitas.celestis;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

/**
 * <h2>LunarSampleGame</h2>
 * <p>
 * A sample game built using only the first-party code of Lunar engine.
 * </p>
 */
public final class LunarSampleGame extends LunarEngine {
    /**
     * Main method. Starts the game.
     *
     * @param args Array of arguments
     */
    public static void main(@Nonnull String[] args) {
        // Call this after initial setup
        instance.start();

        instance.getScheduler().register(new Task() {
            @Override
            public void execute(long delta) {
                System.out.println("Stopping game");
                instance.stop();
            }

            @Override
            public long interval() {
                return 5000;
            }
        });
    }


    //
    // Singleton
    //

    /**
     * Gets the game instance.
     *
     * @return Instance of {@link LunarSampleGame}
     */
    @Nonnull
    public static LunarSampleGame getInstance() {
        return instance;
    }

    private static final LunarSampleGame instance = new LunarSampleGame();
}