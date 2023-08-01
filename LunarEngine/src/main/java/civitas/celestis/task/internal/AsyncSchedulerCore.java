package civitas.celestis.task.internal;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * <h2>AsyncSchedulerCore</h2>
 * <p>
 *     A scheduler core for an asynchronous scheduler.
 * </p>
 */
public final class AsyncSchedulerCore {
    public AsyncSchedulerCore(@Nonnull String name) {
        this.thread = new Thread(() -> {

            while (true) {
                if (Thread.interrupted()) return;

                for (final Task t : List.copyOf(tasks)) {

                    final long now = System.currentTimeMillis();
                    final long previous = times.getOrDefault(t, now);

                    final long delta = now - previous;

                    if (delta < t.interval()) continue;

                    t.execute(delta);
                    times.put(t, now);
                }
            }

        }, name);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public void register(@Nonnull Task t) {
        tasks.add(t);
    }

    public void register(@Nonnull Collection<Task> t) {
        tasks.addAll(t);
    }

    public void unregister(@Nonnull Task t) {
        tasks.remove(t);
    }

    public void unregister(@Nonnull Collection<Task> t) {
        tasks.removeAll(t);
    }

    private final List<Task> tasks = new ArrayList<>();
    private final Map<Task, Long> times = new HashMap<>();
    private final Thread thread;
}
