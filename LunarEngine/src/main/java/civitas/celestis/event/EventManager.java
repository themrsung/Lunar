package civitas.celestis.event;

import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * <h2>EventManager</h2>
 * <p>
 *     A primary module of Lunar Engine.
 *     The event manager handles the processing of events.
 * </p>
 * @see Event
 * @see EventHandler
 * @see Listener
 */
public interface EventManager {
    /**
     * Starts processing queued events.
     */
    void start();

    /**
     * Stops processing queued events.
     * This will terminate the thread on its next iteration.
     */
    void stop();

    /**
     * Calls an event to be handled.
     * @param event Event being called
     * @param <E> Class of event being called
     */
    <E extends Event> void call(@Nonnull E event);

    /**
     * Registers an event listener.
     * @param listener Instance of listener to register
     */
    void register(@Nonnull Listener listener);

    /**
     * Registers multiple event listeners.
     * @param listeners Collection of listener references to register
     */
    void register(@Nonnull Collection<Listener> listeners);

    /**
     * Unregisters an event listener.
     * @param listener Instance of listener to unregister
     */
    void unregister(@Nonnull Listener listener);

    /**
     * Unregisters multiple event listeners.
     * @param listeners Collection of listener references to unregister
     */
    void unregister(@Nonnull Collection<Listener> listeners);
}
