package civitas.celestis.event;

/**
 * <h2>Cancellable</h2>
 * <p>
 * Cancellable events can be declared as cancelled by lower priority listeners.
 * Higher priority listeners <b>should</b> respect this declaration and not process the event.
 * </p>
 */
public interface Cancellable {
    /**
     * Checks if this event has been cancelled by a lower priority listener.
     *
     * @return {@code true} if this event has been cancelled
     */
    boolean isCancelled();

    /**
     * Sets the cancellation state of this event.
     *
     * @param cancelled {@code true} to cancel this event
     */
    void setCancelled(boolean cancelled);
}
