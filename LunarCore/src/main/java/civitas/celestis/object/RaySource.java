package civitas.celestis.object;

import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.object.movable.Movable;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * <h2>RaySource</h2>
 * <p>
 * A source of rays.
 * </p>
 */
public interface RaySource extends Movable {
    /**
     * Returns a list of rays to shoot.
     * This method is called every render cycle.
     * <p>
     * The order will be respected by the scene object.
     * </p>
     *
     * @return List of rays to shoot
     */
    @Nonnull
    List<Ray> shoot();
}
