package civitas.celestis.graphics.ray;

import civitas.celestis.graphics.triangle.Vertex;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * <h2>Ray</h2>
 * <p>
 * A ray.
 * Rays can be shot into a screen and reflect off of surfaces.
 * </p>
 */
public interface Ray {
    /**
     * Gets the origin of this ray.
     *
     * @return Origin
     */
    @Nonnull
    Vector3 origin();

    /**
     * Gets the directional unit vector of this ray.
     *
     * @return Direction
     */
    @Nonnull
    Vector3 direction();

    /**
     * Given a distance {@code t}, this returns the destination of this ray.
     *
     * @param t Distance between origin and destination
     * @return Destination of this ray
     */
    @Nonnull
    Vector3 destination(double t);

    /**
     * Given a surface, this returns the reflection ray originating from the surface.
     *
     * @param surface Surface this ray has collided with
     * @return Reflection ray
     */
    @Nullable
    Ray reflect(@Nonnull Vertex surface);
}
