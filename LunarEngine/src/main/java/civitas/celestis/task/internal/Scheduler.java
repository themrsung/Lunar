package civitas.celestis.task.internal;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * <h2>Scheduler</h2>
 * <p>
 * A primary module of Lunar engine.
 * </p>
 */
public interface Scheduler {
    /**
     * Starts this scheduler,
     */
    void start();

    /**
     * Stops this scheduler.
     */
    void stop();

    /**
     * Registers a new task to this scheduler.
     *
     * @param task Task to register
     */
    void register(@Nonnull Task task);

    /**
     * Registers multiple tasks to a single thread.
     *
     * @param tasks Tasks to register synchronously
     */
    void registerSync(@Nonnull Collection<Task> tasks);

    /**
     * Registers multiple tasks to multiple tasks.
     *
     * @param tasks Tasks to register asynchronously
     */
    void registerAsync(@Nonnull Collection<Task> tasks);

    /**
     * Unregisters a task from this scheduler.
     *
     * @param task Task to unregister
     */
    void unregister(@Nonnull Task task);

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks Collection of tasks to unregister
     */
    void unregister(@Nonnull Collection<Task> tasks);
}
