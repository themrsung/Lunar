package civitas.celestis.task.internal;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * <h2>SyncScheduler</h2>
 * <p>
 * A synchronous scheduler.
 * </p>
 */
public final class SyncScheduler implements Scheduler {
    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public void register(@Nonnull Task t) {
        tasks.add(t);
        times.put(t, System.currentTimeMillis());
    }

    @Override
    public void registerSync(@Nonnull Collection<Task> tasks) {
        for (final Task t : tasks) {
            register(t);
        }
    }

    @Override
    public void registerAsync(@Nonnull Collection<Task> tasks) {
        for (final Task t : tasks) {
            register(t);
        }

        System.out.println("Warning: Scheduler::registerAsync(Collection<Task>) was called to a synchronous scheduler");
        System.out.println("Tasks will be sent to a single thread, as sync schedulers have only one.");
    }

    @Override
    public void unregister(@Nonnull Task t) {
        tasks.remove(t);
    }

    @Override
    public void unregister(@Nonnull Collection<Task> t) {
        tasks.removeAll(t);
    }

    private final List<Task> tasks = new ArrayList<>();
    private final Map<Task, Long> times = new HashMap<>();
    private final Thread thread = new Thread(() -> {

        while (true) {
            // Respect interruption
            if (Thread.interrupted()) return;

            // Iterate through tasks
            for (final Task t : List.copyOf(tasks)) {
                // Calculate delta
                final long now = System.currentTimeMillis();
                final long previous = times.getOrDefault(t, now);
                final long delta = now - previous;

                // Respect interval of task
                if (delta < t.interval()) continue;

                // Execute task and keep time
                t.execute(delta);
                times.put(t, now);
            }
        }

    }, "SyncScheduler");
}
