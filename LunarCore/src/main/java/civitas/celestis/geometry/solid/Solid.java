package civitas.celestis.geometry.solid;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * <h2>Solid</h2>
 * <p>The superinterface for all solids.</p>
 */
public interface Solid {
    /**
     * Gets the geometric centroid of this solid.
     *
     * @return Geometric centroid
     */
    @Nonnull
    Vector3 centroid();

    /**
     * Gets a list of corners of this solid.
     *
     * @return List of corners
     */
    @Nonnull
    List<Vector3> corners();

    /**
     * Gets the volume of this solid.
     *
     * @return Volume
     */
    double volume();

    /**
     * Gets the signed volume of this solid.
     *
     * @return Signed volume
     */
    double signedVolume();

    /**
     * Checks if given point is within the bounds of {@code this}.
     *
     * @param point Point to check
     * @return {@code true} if this solid contains given point
     */
    boolean contains(@Nonnull Vector3 point);

    /**
     * Checks if this solid overlaps another.
     *
     * @param other Solid to check
     * @return {@code true} if this solid has at least one common point with given solid
     */
    boolean overlaps(@Nonnull Solid other);
}
