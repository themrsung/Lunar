package civitas.celestis.graphics.vertex;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.group.Tuple;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * <h2>Vertex</h2>
 * <p>
 * A surface with three corners.
 * Vertices are used as the base building block for all graphics objects.
 * </p>
 */
public interface Vertex extends Iterable<Vector3> {
    //
    // Geometry
    //

    /**
     * Gets the first point of this vertex.
     *
     * @return Point A
     */
    @Nonnull
    Vector3 a();

    /**
     * Gets the second point of this vertex.
     *
     * @return Point B
     */
    @Nonnull
    Vector3 b();

    /**
     * Gets the third point of this vertex.
     *
     * @return Point C
     */
    @Nonnull
    Vector3 c();

    /**
     * Gets the geometric centroid of this vertex.
     *
     * @return Geometric centroid
     */
    @Nonnull
    Vector3 centroid();

    /**
     * Gets a tuple of the three points of this vertex.
     *
     * @return Tuple of three points
     */
    @Nonnull
    Tuple<Vector3> points();

    /**
     * Gets the surface normal of this vertex.
     *
     * @return Surface normal
     */
    @Nonnull
    Vector3 normal();

    //
    // Translucency
    //

    /**
     * Whether this vertex allows rays to pass through.
     *
     * @return {@code true} if this vertex is translucent
     */
    boolean translucent();

    /**
     * Gets the alpha of this vertex.
     * <p>
     * An alpha of {@code 0} means this vertex is completely transparent,
     * and rays will not reflect off of this vertex.
     * </p>
     * <p>
     * An alpha of {@code 1} means this vertex is completely opaque,
     * and rays will not pass through this vertex.
     * </p>
     * <p>
     * Values in between will have both a reflecting ray and a pass-through ray
     * (if {@link Vertex#translucent()}) is {@code true}),
     * with varying intensities.
     * </p>
     *
     * @return Alpha of vertex
     */
    double alpha();

    //
    // Methods
    //

    /**
     * Applies given action to all points of this vertex, then returns the resulting vertex.
     *
     * @param operation Action to apply to each point
     * @return Resulting vertex
     */
    @Nonnull
    Vertex apply(@Nonnull UnaryOperator<Vector3> operation);

    /**
     * Inflates this vertex by given scale.
     *
     * @param scale Scale to inflate by
     * @return Inflated vertex
     */
    @Nonnull
    Vertex inflate(double scale);

    /**
     * Translates this vertex to a relative coordinate system.
     *
     * @param origin New origin of this vertex
     * @return Translated vertex
     */
    @Nonnull
    Vertex translate(@Nonnull Vector3 origin);

    /**
     * Rotates this vertex by given rotation.
     *
     * @param rq Rotation quaternion
     * @return Rotated vertex
     */
    @Nonnull
    Vertex rotate(@Nonnull Quaternion rq);

    /**
     * Performs the following operations in sequence, then returns the resulting vertex.
     * This has performance benefits over calling the component methods in a method chain.
     * <ol>
     *     <li>{@link Vertex#translate(Vector3)}</li>
     *     <li>{@link Vertex#rotate(Quaternion)}</li>
     *     <li>{@link Vertex#inflate(double)}</li>
     * </ol>
     *
     * @param origin New origin of this vertex
     * @param rq     Rotation quaternion to apply
     * @param scale  Scale to inflate by (set to 1 for no inflation)
     * @return Resulting vertex
     */
    @Nonnull
    Vertex transform(@Nonnull Vector3 origin, @Nonnull Quaternion rq, double scale);

    //
    // Cloning
    //

    /**
     * Returns an identical copy of {@code this}.
     *
     * @return Copy of {@code this}
     */
    @Nonnull
    Vertex copy();
}
