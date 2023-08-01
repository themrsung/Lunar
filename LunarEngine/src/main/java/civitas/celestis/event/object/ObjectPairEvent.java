package civitas.celestis.event.object;

import civitas.celestis.event.Event;
import civitas.celestis.object.base.BaseObject;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

/**
 * <h2>ObjectPairEvent</h2>
 * <p>An event involving a pair of objects.</p>
 */
public abstract class ObjectPairEvent implements Event {
    /**
     * Creates a new object pair event.
     *
     * @param objects Pair of objects involved in this event
     */
    public ObjectPairEvent(@Nonnull Pair<BaseObject> objects) {
        this.objects = objects;
    }

    @Nonnull
    private final Pair<BaseObject> objects;

    /**
     * Gets the pair of objects involved in this event.
     *
     * @return Pair of objects
     */
    @Nonnull
    public Pair<BaseObject> getObjects() {
        return objects;
    }
}
