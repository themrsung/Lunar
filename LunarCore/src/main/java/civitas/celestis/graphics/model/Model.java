package civitas.celestis.graphics.model;

import civitas.celestis.graphics.triangle.Vertex;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * <h2>Model</h2>
 * <p>
 *     A three-dimensional model of an object.
 * </p>
 */
public interface Model {
    /**
     * Gets the vertices of this model.
     * @param origin Absolute origin of this model (most likely the location of its parent object)
     * @param rotation Rotation of this model (most likely the rotation of its parent object)
     * @return List of vertices
     */
    @Nonnull
    List<Vertex> vertices(@Nonnull Vector3 origin, @Nonnull Rotation rotation);
}
