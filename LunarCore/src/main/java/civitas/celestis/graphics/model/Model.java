package civitas.celestis.graphics.model;

import civitas.celestis.graphics.vertex.Vertex;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * <h2>Model</h2>
 * <p>
 * A three-dimensional representation of an object.
 * </p>
 */
public interface Model {
    /**
     * Gets the list of points of this model.
     *
     * @return List of points
     */
    @Nonnull
    List<Vector3> getPoints();

    /**
     * Gets the number of points of this model.
     *
     * @return Number of points
     */
    int getPointCount();

    /**
     * Gets the list of vertices of this model.
     *
     * @return List of vertices
     */
    @Nonnull
    List<Vertex> getVertices();

    /**
     * Gets the number of vertices of this model.
     *
     * @return Number of vertices
     */
    int getVertexCount();
}
