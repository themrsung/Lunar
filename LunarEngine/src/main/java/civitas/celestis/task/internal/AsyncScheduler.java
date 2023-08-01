package civitas.celestis.task.internal;

import civitas.celestis.task.Task;
import civitas.celestis.util.Reiterator;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * <h2>AsyncScheduler</h2>
 * <p>
 * An asynchronous scheduler.
 * </p>
 */
public final class AsyncScheduler implements Scheduler {
    @Override
    public void start() {
        for (final AsyncSchedulerCore core : cores) {
            core.start();
        }
    }

    @Override
    public void stop() {
        for (final AsyncSchedulerCore core : cores) {
            core.stop();
        }
    }

    @Override
    public void register(@Nonnull Task task) {
        cores[distributor.next()].register(task);
    }

    @Override
    public void registerSync(@Nonnull Collection<Task> tasks) {
        cores[distributor.next()].register(tasks);
    }

    @Override
    public void registerAsync(@Nonnull Collection<Task> tasks) {
        tasks.forEach(this::register);
    }

    @Override
    public void unregister(@Nonnull Task task) {
        for (final AsyncSchedulerCore core : cores) {
            core.unregister(task);
        }
    }

    @Override
    public void unregister(@Nonnull Collection<Task> tasks) {
        for (final AsyncSchedulerCore core : cores) {
            core.unregister(tasks);
        }
    }

    private final AsyncSchedulerCore[] cores = {
            new AsyncSchedulerCore("AsyncSchedulerCore-1"),
            new AsyncSchedulerCore("AsyncSchedulerCore-2"),
            new AsyncSchedulerCore("AsyncSchedulerCore-3"),
            new AsyncSchedulerCore("AsyncSchedulerCore-4"),
            new AsyncSchedulerCore("AsyncSchedulerCore-5"),
            new AsyncSchedulerCore("AsyncSchedulerCore-6"),
            new AsyncSchedulerCore("AsyncSchedulerCore-7"),
            new AsyncSchedulerCore("AsyncSchedulerCore-8")
    };

    private final Reiterator distributor = new Reiterator(cores.length);
}
