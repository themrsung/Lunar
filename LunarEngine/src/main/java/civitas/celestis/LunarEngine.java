package civitas.celestis;

import civitas.celestis.event.EventManager;
import civitas.celestis.event.internal.SyncEventManager;
import civitas.celestis.listener.object.ObjectPairListener;
import civitas.celestis.task.internal.AsyncScheduler;
import civitas.celestis.task.internal.Scheduler;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.logging.Logger;

/**
 * <h2>LunarEngine</h2>
 * <p>
 * The main class of Lunar engine.
 * </p>
 */
public class LunarEngine {
    //
    // Lifecycle
    //

    /**
     * Starts the engine.
     */
    public void start() {
        logger.info("Lunar Engine is starting.");

        // Initialize modules
        registerEventListeners();

        // Start modules
        eventManager.start();
        scheduler.start();

        logger.info("Lunar Engine has started.");
    }

    /**
     * Stops the engine.
     */
    public void stop() {
        logger.info("Lunar Engine is stopping.");

        // Stop modules
        eventManager.stop();
        scheduler.stop();

        logger.info("Lunar Engine has stopped.");
    }

    /**
     * Registers first-party event listeners.
     */
    private void registerEventListeners() {
        eventManager.register(List.of(
                new ObjectPairListener()
        ));
    }

    //
    // Getters & Modules
    //

    /**
     * Gets the scheduler instance.
     *
     * @return Instance of {@link Scheduler}
     */
    @Nonnull
    public static Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Gets the event manager instance.
     *
     * @return Instance of {@link EventManager}
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    private static final Scheduler scheduler = new AsyncScheduler();
    private static final EventManager eventManager = new SyncEventManager();

    //
    // Logger
    //

    /**
     * Gets the logger instance.
     *
     * @return {@link Logger}
     */
    @Nonnull
    public static Logger getLogger() {
        return logger;
    }

    private static final Logger logger = Logger.getLogger("LunarEngine");
}
