package civitas.celestis.event;

import jakarta.annotation.Nonnull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h2>EventHandler</h2>
 * <p>
 * This annotation marks a method as being an event handler.
 * Event handler will be called when the event it listens to is called.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Gets the priority of this event handler.
     *
     * @return Handler priority
     * @see HandlerPriority
     */
    @Nonnull
    HandlerPriority priority() default HandlerPriority.NORMAL;
}
