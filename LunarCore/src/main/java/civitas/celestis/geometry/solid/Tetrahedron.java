package civitas.celestis.geometry.solid;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * <h2>Tetrahedron</h2>
 * <p>
 * A solid with four regular triangles.
 * </p>
 */
public class Tetrahedron implements Solid {
    /**
     * Creates a new tetrahedron.
     *
     * @param a First point of this tetrahedron
     * @param b Second point of this tetrahedron
     * @param c Third point of this tetrahedron
     * @param d Fourth point of this tetrahedron
     */
    public Tetrahedron(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c, @Nonnull Vector3 d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Nonnull
    private final Vector3 a;
    @Nonnull
    private final Vector3 b;
    @Nonnull
    private final Vector3 c;
    @Nonnull
    private final Vector3 d;

    /**
     * Gets the first point of this tetrahedron.
     *
     * @return Point A
     */
    @Nonnull
    public Vector3 a() {
        return a;
    }

    /**
     * Gets the second point of this tetrahedron.
     *
     * @return Point B
     */
    @Nonnull
    public Vector3 b() {
        return b;
    }

    /**
     * Gets the third point of this tetrahedron.
     *
     * @return Point C
     */
    @Nonnull
    public Vector3 c() {
        return c;
    }

    /**
     * Gets the fourth point of this tetrahedron.
     *
     * @return Point D
     */
    @Nonnull
    public Vector3 d() {
        return d;
    }

    @Nonnull
    @Override
    public Vector3 centroid() {
        return a.add(b).add(c).add(d).divide(4);
    }

    @Nonnull
    @Override
    public List<Vector3> corners() {
        return List.of(a, b, c, d);
    }

    @Override
    public double volume() {
        return Math.abs(signedVolume());
    }

    @Override
    public double signedVolume() {
        return (1d / 6d) * b.subtract(a).cross(c.subtract(a)).dot(d.subtract(a));
    }

    @Override
    public boolean contains(@Nonnull Vector3 point) {
        return false;
    }

    @Override
    public boolean overlaps(@Nonnull Solid other) {
        return false;
    }
}
