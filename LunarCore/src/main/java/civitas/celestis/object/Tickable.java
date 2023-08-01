package civitas.celestis.object;

/**
 * <h2>Tickable</h2>
 * <p>
 * A tickable object.
 * Tickable objects are ticked every tick loop, and are provided with a delta
 * denoting the amount of time between ticks. Unit is in milliseconds.
 * </p>
 */
public interface Tickable {
    /**
     * Ticks this object.
     *
     * @param delta Duration between the last tick and now in milliseconds.
     */
    void tick(long delta);
}
