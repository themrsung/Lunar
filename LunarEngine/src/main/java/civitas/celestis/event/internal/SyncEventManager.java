package civitas.celestis.event.internal;

import civitas.celestis.event.Event;
import civitas.celestis.event.EventManager;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <h2>SyncEventManager</h2>
 * <p>
 *     A synchronous implementation of {@link EventManager}.
 *     This implementation is recommended for small-sized projects
 *     which do not require asynchronous event processing.
 * </p>
 * <p>
 *     While there are advantages to use the asynchronous implementation,
 *     the additional overhead may slow down small-sized projects more
 *     than the benefits of additional performance.
 * </p>
 */
public final class SyncEventManager implements EventManager {
    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public <E extends Event> void call(@Nonnull E event) {
        queue.add(event);
    }

    @Override
    public void register(@Nonnull Listener l) {
        listeners.add(l);
    }

    @Override
    public void register(@Nonnull Collection<Listener> l) {
        listeners.addAll(l);
    }

    @Override
    public void unregister(@Nonnull Listener l) {
        listeners.remove(l);
    }

    @Override
    public void unregister(@Nonnull Collection<Listener> l) {
        listeners.removeAll(l);
    }

    private final Queue<Event> queue = new LinkedList<>();
    private final List<Listener> listeners = new ArrayList<>();
    private final Thread thread = new Thread(() -> {

        while (true) {
            // Respect interruption
            if (Thread.interrupted()) return;

            // Poll next event
            final Event event = queue.poll();

            // Null check
            if (event == null) continue;

            // Initialize empty reference list
            final List<HandlerReference> handlers = new ArrayList<>();

            // Add handler references
            for (final Listener l : List.copyOf(listeners)) {
                handlers.addAll(l.getEventHandlers(event.getClass()));
            }

            // Sort handlers by priority
            handlers.sort(Comparator.comparing(HandlerReference::priority));

            // Iterate through handlers
            for (final HandlerReference h : handlers) {
                try {
                    // Try to call event
                    h.handler().invoke(h.listener(), event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Handle exception
                    e.printStackTrace();
                }
            }
        }

    }, "SyncEventManager");
}
