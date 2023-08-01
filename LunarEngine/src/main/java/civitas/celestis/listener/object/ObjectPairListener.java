package civitas.celestis.listener.object;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import civitas.celestis.event.object.ObjectsCollidedEvent;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.base.BaseObject;
import jakarta.annotation.Nonnull;

/**
 * <h2>ObjectPairListener</h2>
 * <p>Handles the processing object pair events.</p>
 */
public final class ObjectPairListener implements Listener {
    @EventHandler(priority = HandlerPriority.PERMISSIVE)
    public void onObjectsCollided(@Nonnull ObjectsCollidedEvent event) {
        // Respect cancellation
        if (event.isCancelled()) return;

        // Get pointers to objects
        final BaseObject o1 = event.getObjects().a();
        final BaseObject o2 = event.getObjects().b();

        // Get masses
        final double m1 = o1.getPhysics().mass();
        final double m2 = o2.getPhysics().mass();

        if (m1 + m2 <= 0) {
            // Cannot divide by zero; Fallback to stopping both objects
            o1.setAcceleration(Vector3.ZERO);
            o2.setAcceleration(Vector3.ZERO);
            return;
        }

        // Get initial velocities
        final Vector3 u1 = o1.getAcceleration();
        final Vector3 u2 = o2.getAcceleration();

        // Calculate final velocities
        final Vector3 v1 = u1.multiply((m1 - m2) / m1 + m2).add(u2.multiply((2 * m2 * m2) / (m1 + m2)));
        final Vector3 v2 = u1.multiply((2 * m1) / (m1 + m2)).add(u2.multiply((m2 - m1) / (m1 + m2)));

        // Apply final velocities
        o1.setAcceleration(v1);
        o2.setAcceleration(v2);
    }
}
