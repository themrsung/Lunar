package civitas.celestis.event.internal;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.lang.reflect.Method;

/**
 * <h2>HandlerReference</h2>
 * <p>
 * A transient reference class to an event handler.
 * </p>
 *
 * @param listener Listener object
 * @param handler  Handler to call
 */
public record HandlerReference(@Nonnull Listener listener, @Nonnull Method handler) {
    /**
     * Gets the priority of this handler reference.
     *
     * @return Handler priority
     */
    @Nonnull
    public HandlerPriority priority() {
        return handler.getAnnotation(EventHandler.class).priority();
    }
}
