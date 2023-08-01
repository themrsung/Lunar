package civitas.celestis.event.object;

import civitas.celestis.event.Cancellable;
import civitas.celestis.object.base.BaseObject;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

/**
 * <h2>ObjectsCollidedEvent</h2>
 * <p>This is called when two objects' bounds overlap for the first time.</p>
 */
public final class ObjectsCollidedEvent extends ObjectPairEvent implements Cancellable {
    /**
     * Creates a new object collision event.
     *
     * @param objects Pair of objects which collided
     */
    public ObjectsCollidedEvent(@Nonnull Pair<BaseObject> objects) {
        super(objects);
        this.cancelled = false;
    }

    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
