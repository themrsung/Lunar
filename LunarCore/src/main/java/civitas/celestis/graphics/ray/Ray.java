package civitas.celestis.graphics.ray;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * <h2>Ray</h2>
 * <p>
 *     A ray.
 *     Rays can be shot into a screen and reflect off of surfaces.
 * </p>
 */
public interface Ray {
    /**
     * Gets the origin of this ray.
     * @return Origin
     */
    @Nonnull
    Vector3 origin();

    /**
     * Gets the directional unit vector of this ray.
     * @return Direction
     */
    @Nonnull
    Vector3 direction();

    /**
     * Given a distance {@code t}, this returns the destination of this ray.
     * @param t Distance between origin and destination
     * @return Destination of this ray
     */
    @Nonnull
    Vector3 destination(double t);
}
