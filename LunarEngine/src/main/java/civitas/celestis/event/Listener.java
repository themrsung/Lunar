package civitas.celestis.event;

import civitas.celestis.event.internal.HandlerReference;
import jakarta.annotation.Nonnull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Listener</h2>
 * <p>
 * An event listener.
 * Listeners can be registered to an event manager to listen for events.
 * </p>
 */
public interface Listener {
    /**
     * Gets a list of event handler in this listener.
     * <p>
     * <b>Only override this method if you are changing the architecture of the event manager.</b>
     * Any changes here will be reflected in the event manager's code,
     * and may cause issues when modified without proper context.
     * </p>
     *
     * @param eventClass Class of event to query
     * @param <E>        Type of event to query
     * @return List of handler references which listen to given event
     */
    @Nonnull
    default <E extends Event> List<HandlerReference> getEventHandlers(@Nonnull Class<E> eventClass) {
        final List<HandlerReference> handlers = new ArrayList<>();

        for (final Method m : getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(EventHandler.class)) continue;
            if (m.getParameterCount() != 1) continue;
            if (!m.getParameterTypes()[0].isAssignableFrom(eventClass)) continue;

            handlers.add(new HandlerReference(this, m));
        }

        return handlers;
    }
}
