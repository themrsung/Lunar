package civitas.celestis.graphics.triangle;

import civitas.celestis.geometry.solid.Tetrahedron;
import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.ui.element.Polygon2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * <h2>Vertex</h2>
 * <p>A renderable triangle.</p>
 */
public final class Vertex implements Iterable<Vector3> {
    /**
     * Creates a new vertex.
     *
     * @param a     First point of this vertex
     * @param b     Second point of this vertex
     * @param c     Third point of this vertex
     * @param color Base color of this vertex
     */
    public Vertex(
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

    /**
     * Creates a new vertex.
     * @param points A list of points containing exactly 3 points
     * @param color Base color of this vertex
     */
    public Vertex(
            @Nonnull List<Vector3> points,
            @Nonnull Color color
    ) {
        if (points.size() != 3) throw new IllegalArgumentException("A vertex must have at least and only 3 points.");

        this.a = points.get(0);
        this.b = points.get(1);
        this.c = points.get(2);
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
     * Gets the first point of this vertex.
     *
     * @return Point A
     */
    @Nonnull
    public Vector3 a() {return a;}

    /**
     * Gets the second point of this vertex.
     *
     * @return Point B
     */
    @Nonnull
    public Vector3 b() {return b;}

    /**
     * Gets the third point of this vertex.
     *
     * @return Point C
     */
    @Nonnull
    public Vector3 c() {return c;}

    /**
     * Gets a list of points in this vertex.
     * @return List of points
     */
    @Nonnull
    public List<Vector3> points() {
        return List.of(a, b, c);
    }

    /**
     * Gets the color of this vertex.
     *
     * @return Base color
     */
    @Nonnull
    public Color color() {return color;}

    /**
     * Gets the surface normal of this vertex.
     *
     * @return Surface normal
     */
    @Nonnull
    public Vector3 normal() {
        return b.subtract(a).cross(c.subtract(a));
    }

    /**
     * Gets the geometric centroid of this vertex.
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
     * if the vector were to collide with this vertex.
     * <p>
     * Note that this does not account for whether the two objects intersect,
     * and cannot be used to check if a vertex and line intersect.
     * </p>
     * <p>
     * To check whether the two objects intersect, check for {@code null}
     * in the return value of {@link Vertex#intersection(Ray)}.
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

    /**
     * Applies a unary operator to all vector components,
     * then returns the modified instance.
     *
     * @param action Action to apply
     * @return Modified instance
     */
    @Nonnull
    public Vertex apply(@Nonnull UnaryOperator<Vector3> action) {
        final Vector3 x = action.apply(a);
        final Vector3 y = action.apply(b);
        final Vector3 z = action.apply(c);

        return new Vertex(x, y, z, color);
    }

    /**
     * Inflates this vertex by given scale in respect to the relative coordinates of this vertex.
     *
     * @param scale Scale of inflation
     * @return Inflated vertex
     */
    @Nonnull
    public Vertex inflate(double scale) {
        return apply(v -> v.multiply(scale));
    }

    /**
     * Transforms this vertex to a coordinate system of different origin.
     *
     * @param origin New origin of this vertex (relative to current origin)
     * @return Transformed vertex
     */
    @Nonnull
    public Vertex transform(@Nonnull Vector3 origin) {
        return apply(v -> v.subtract(origin));
    }

    /**
     * Rotates this vertex in respect to the relative coordinates of this vertex.
     *
     * @param rotation Rotation to apply
     * @return Rotated vertex
     */
    @Nonnull
    public Vertex rotate(@Nonnull Rotation rotation) {
        return apply(v -> v.rotate(rotation));
    }

    /**
     * Assuming this vertex has been translated to a coordinate system relative to the viewer,
     * this method converts the vertex to a renderable polygon.
     * <p>
     *     Since {@link Polygon2} extends {@link Polygon}, the return value of this method
     *     can be directly be inputted into a {@code Graphics} object.
     * </p>
     * @param focalLength Focal length of camera at this time
     * @return Renderable polygon
     */
    @Nonnull
    public Polygon2 polygon(double focalLength) {
        final Polygon2 p = new Polygon2();

        for (final Vector3 v : this) {
            final double z = ((focalLength / (focalLength + v.z())));
            p.addPoint(new Vector2(z * v.x(), z * -v.y()));
        }

        return p;
    }

    /**
     * Gets an iterator of the component vectors of this vertex.
     * @return Iterator of vectors
     */
    @Override
    @Nonnull
    public Iterator<Vector3> iterator() {
        return points().iterator();
    }
}
