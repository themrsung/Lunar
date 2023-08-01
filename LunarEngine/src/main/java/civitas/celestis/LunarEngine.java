package civitas.celestis;

import civitas.celestis.event.EventManager;
import civitas.celestis.event.internal.SyncEventManager;
import civitas.celestis.task.internal.AsyncScheduler;
import civitas.celestis.task.internal.Scheduler;
import jakarta.annotation.Nonnull;

/**
 * <h2>LunarEngine</h2>
 * <p>
 * The main class of Lunar engine.
 * This class is designed to be inherited, instead of being contained.
 * </p>
 */
public class LunarEngine {
    //
    // Constructors
    //

    /**
     * Default constructor.
     * This is useful for simple projects.
     */
    public LunarEngine() {
        this.scheduler = new AsyncScheduler();
        this.eventManager = new SyncEventManager();
    }

    //
    // Lifecycle
    //

    /**
     * Starts the engine.
     */
    public void start() {
        eventManager.start();
        scheduler.start();
    }

    /**
     * Stops the engine.
     */
    public void stop() {
        eventManager.stop();
        scheduler.stop();
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
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Gets the event manager instance.
     *
     * @return Instance of {@link EventManager}
     */
    @Nonnull
    public EventManager getEventManager() {
        return eventManager;
    }

    private final Scheduler scheduler;
    private final EventManager eventManager;


}
