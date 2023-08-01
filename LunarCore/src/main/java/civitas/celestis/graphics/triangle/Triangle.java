package civitas.celestis.graphics.triangle;

import civitas.celestis.geometry.solid.Tetrahedron;
import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.awt.*;

/**
 * <h2>Triangle</h2>
 * <p>A renderable triangle.</p>
 */
public final class Triangle {
    /**
     * Creates a new triangle.
     *
     * @param a     First point of this triangle
     * @param b     Second point of this triangle
     * @param c     Third point of this triangle
     * @param color Base color of this triangle
     */
    public Triangle(
            @Nonnull Vector3 a,
            @Nonnull Vector3 b,
            @Nonnull Vector3 c,
            @Nonnull Color color
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    @Nonnull
    private final Vector3 a;
    @Nonnull
    private final Vector3 b;
    @Nonnull
    private final Vector3 c;
    @Nonnull
    private final Color color;

    /**
     * Gets the first point of this triangle.
     *
     * @return Point A
     */
    @Nonnull
    public Vector3 a() {return a;}

    /**
     * Gets the second point of this triangle.
     *
     * @return Point B
     */
    @Nonnull
    public Vector3 b() {return b;}

    /**
     * Gets the third point of this triangle.
     *
     * @return Point C
     */
    @Nonnull
    public Vector3 c() {return c;}

    /**
     * Gets the color of this triangle.
     *
     * @return Base color
     */
    @Nonnull
    public Color color() {return color;}

    /**
     * Gets the surface normal of this triangle.
     *
     * @return Surface normal
     */
    @Nonnull
    public Vector3 normal() {
        return b.subtract(a).cross(c.subtract(a));
    }

    /**
     * Gets the geometric centroid of this triangle.
     *
     * @return Geometric centroid
     */
    @Nonnull
    public Vector3 centroid() {
        return a.add(b).add(c).divide(3);
    }

    /**
     * Gets the intersection between {@code this} and {@code ray}.
     *
     * @param ray Ray to check intersection with
     * @return Intersection point if found, {@code null} if not
     */
    @Nullable
    public Vector3 intersection(@Nonnull Ray ray) {
        // Initialize variables
        final Vector3 q1 = ray.origin();
        final Vector3 q2 = ray.destination(centroid().distance2(ray.origin()));

        final Vector3 p1 = a;
        final Vector3 p2 = b;
        final Vector3 p3 = c;

        // Get signed volumes

        // These must have different signs
        final double sv1 = new Tetrahedron(q1, p1, p2, p3).signedVolume();
        final double sv2 = new Tetrahedron(q2, p1, p2, p3).signedVolume();

        // These must have the same sign
        final double sv3 = new Tetrahedron(q1, q2, p1, p2).signedVolume();
        final double sv4 = new Tetrahedron(q1, q2, p2, p3).signedVolume();
        final double sv5 = new Tetrahedron(q1, q2, p3, p1).signedVolume();

        // Whether this intersects the ray
        final boolean intersects = sv1 * sv2 < 0 && sv3 * sv4 * sv5 >= 0;

        if (!intersects) return null;

        final Vector3 n = normal();

        final double denominator = ray.direction().dot(n);
        if (denominator == 0) return null;

        // Get the point of intersection
        final double t = centroid().subtract(ray.origin()).dot(n) / denominator;
        if (t < 0) return null;

        return ray.destination(t);
    }

    /**
     * Given a directional vector {@code in}, this returns its reflection vector
     * if the vector were to collide with this triangle.
     * <p>
     * Note that this does not account for whether the two objects intersect,
     * and cannot be used to check if a triangle and line intersect.
     * </p>
     * <p>
     * To check whether the two objects intersect, check for {@code null}
     * in the return value of {@link Triangle#intersection(Ray)}.
     * </p>
     *
     * @param in Input vector
     * @return Reflection vector
     */
    @Nonnull
    public Vector3 reflection(@Nonnull Vector3 in) {
        final Vector3 n = normal();
        return in.subtract(n.multiply(2 * in.dot(n)));
    }
}
