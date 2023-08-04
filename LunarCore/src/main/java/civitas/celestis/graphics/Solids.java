package civitas.celestis.graphics;

import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.graphics.vertex.Vertex;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * <h2>Solids</h2>
 * <p>A utility class containing geometric functions.</p>
 */
public final class Solids {
    /**
     * Gets the signed volume of a tetrahedron.
     *
     * @param p1 Point 1
     * @param p2 Point 2
     * @param p3 Point 3
     * @param p4 Point 4
     * @return Signed volume of tetrahedron
     */
    public static double signedVolume(@Nonnull Vector3 p1, @Nonnull Vector3 p2, @Nonnull Vector3 p3, @Nonnull Vector3 p4) {
        return (1d / 6d) * p2.subtract(p1).cross(p3.subtract(p1)).dot(p4.subtract(p1));
    }

    /**
     * Given a vertex and a ray, this returns the intersection between the two.
     * If there is no intersection, this will return {@code null}.
     *
     * @param vertex Vertex to check
     * @param ray    Ray to check
     * @return Point of intersection if found, {@code null} if not
     */
    @Nullable
    public static Vector3 intersection(@Nonnull Vertex vertex, @Nonnull Ray ray) {
        // Initialize variables
        final Vector3 q1 = ray.origin();
        final Vector3 q2 = ray.destination(vertex.centroid().distance2(ray.origin()));

        final Vector3 p1 = vertex.a();
        final Vector3 p2 = vertex.b();
        final Vector3 p3 = vertex.c();

        // Get signed volumes

        // These must have different signs
        final double sv1 = signedVolume(q1, p1, p2, p3);
        final double sv2 = signedVolume(q2, p1, p2, p3);

        // These must have the same sign
        final double sv3 = signedVolume(q1, q2, p1, p2);
        final double sv4 = signedVolume(q1, q2, p2, p3);
        final double sv5 = signedVolume(q1, q2, p3, p1);

        // Whether this intersects the ray
        final boolean intersects = sv1 * sv2 < 0 && sv3 * sv4 * sv5 >= 0;

        // No intersection found
        if (!intersects) return null;

        // Get vertex normal
        final Vector3 n = vertex.normal();

        // Check for zero denominator
        final double denominator = ray.direction().dot(n);
        if (denominator == 0) return null; // Cannot divide by zero

        // Get point of intersection
        final double t = vertex.centroid().subtract(ray.origin()).dot(n) / denominator;
        if (t < 0) return null; // Does not intersect

        // Return intersection
        return ray.destination(t);
    }
}
