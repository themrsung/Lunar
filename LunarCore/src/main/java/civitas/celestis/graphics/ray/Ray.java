package civitas.celestis.graphics.ray;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * <h2>Ray</h2>
 * <p>A ray.</p>
 */
public interface Ray {
    /**
     * Gets the origin of this ray.
     *
     * @return Origin of ray
     */
    @Nonnull
    Vector3 origin();

    /**
     * Gets the direction of this ray.
     *
     * @return Direction of ray
     */
    @Nonnull
    Vector3 direction();

    /**
     * Gets the destination of this ray.
     *
     * @param t Distance of travel
     * @return Destination of ray
     */
    @Nonnull
    Vector3 destination(double t);
}
