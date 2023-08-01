package civitas.celestis.object;

import civitas.celestis.object.base.BaseObject;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

/**
 * <h2>LunarObjects</h2>
 * <p>Contains utility methods related to Lunar objects.</p>
 */
public final class LunarObjects {
    /**
     * Checks if two objects overlap.
     *
     * @param o1 First object
     * @param o2 Second object
     * @return {@code true} if the two objects have at least one point in common
     */
    public static boolean overlaps(@Nonnull BaseObject o1, @Nonnull BaseObject o2) {
        final Solid s1 = o1.getPhysics().build(o1.getLocation(), o1.getRotation());
        final Solid s2 = o2.getPhysics().build(o2.getLocation(), o2.getRotation());

        return s1.overlaps(s2);
    }
}
