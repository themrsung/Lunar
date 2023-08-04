package civitas.celestis.graphics.vertex;

import civitas.celestis.graphics.Colors;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.group.Tuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.awt.*;
import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * <h2>ColoredVertex</h2>
 * <p>A vertex with a single mutable color component.</p>
 */
public class ColoredVertex implements Vertex {
    //
    // Constructors
    //

    /**
     * Creates a new colored vertex.
     *
     * @param a     Point A of this vertex
     * @param b     Point B of this vertex
     * @param c     Point C of this vertex
     * @param color Color of this vertex
     */
    public ColoredVertex(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c, @Nonnull Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;

        invalidateCache();
    }

    /**
     * Creates a new colored vertex.
     *
     * @param points Tuple of points of this vertex
     * @param color  Color of this vertex
     */
    public ColoredVertex(@Nonnull Tuple<Vector3> points, @Nonnull Color color) {
        this(points.a(), points.b(), points.c(), color);
    }

    //
    // Constants
    //

    /**
     * The maximum alpha of a translucent vertex.
     * Alphas above this threshold will be considered opaque.
     */
    private static final double TRANSLUCENT_THRESHOLD = 1 - Double.MIN_VALUE;

    //
    // Properties
    //
    @Nonnull
    private final Vector3 a;
    @Nonnull
    private final Vector3 b;
    @Nonnull
    private final Vector3 c;
    @Nonnull
    private Color color;

    //
    // Color
    //

    /**
     * Gets the color of this vertex.
     *
     * @return Color of vertex
     */
    @Nonnull
    public Color color() {
        return color;
    }

    /**
     * Applies shading to this vertex's color.
     *
     * @param shade  Shade to apply
     * @param weight Weight of shade (relative to {@code 1})
     */
    public void shade(@Nonnull Color shade, double weight) {
        this.color = Colors.mix(color, shade, 1, weight);
    }

    /**
     * Sets the color of this vertex.
     *
     * @param color Color to set to
     */
    public void color(@Nonnull Color color) {
        this.color = color;
    }

    //
    // Caching
    //
    // Since vertex information is queried several times per frame,
    // return values for derivative methods are cached on the first method call.
    //
    @Nullable
    private Vector3 centroid;
    @Nullable
    private Tuple<Vector3> points;
    @Nullable
    private Vector3 normal;
    private double alpha;

    private void computeCentroid() {
        this.centroid = a.add(b).add(c).divide(3);
    }

    private void computePoints() {
        this.points = new Tuple<>(a, b, c);
    }

    private void computeNormal() {
        this.normal = b.subtract(a).cross(c.subtract(a));
    }

    private void computeAlpha() {
        this.alpha = color.getAlpha() / 255d;
    }

    /**
     * Forcibly pre-computes all cache.
     */
    public void forceCompute() {
        computeCentroid();
        computePoints();
        computeNormal();
        computeAlpha();
    }

    /**
     * Invalidates cached information.
     */
    public void invalidateCache() {
        centroid = null;
        points = null;
        normal = null;
        alpha = Double.NaN;
    }

    //
    // Implementation
    //

    @Nonnull
    @Override
    public Vector3 a() {
        return a;
    }

    @Nonnull
    @Override
    public Vector3 b() {
        return b;
    }

    @Nonnull
    @Override
    public Vector3 c() {
        return c;
    }

    @Nonnull
    @Override
    public Vector3 centroid() {
        if (centroid == null) {
            computeCentroid();
        }

        return centroid;
    }

    @Nonnull
    @Override
    public Tuple<Vector3> points() {
        if (points == null) {
            computePoints();
        }

        return points;
    }

    @Nonnull
    @Override
    public Vector3 normal() {
        if (normal == null) {
            computeNormal();
        }

        return normal;
    }

    @Override
    public boolean translucent() {
        return alpha() <= TRANSLUCENT_THRESHOLD;
    }

    @Override
    public double alpha() {
        if (Double.isNaN(alpha)) {
            computeAlpha();
        }

        return alpha;
    }

    @Override
    public Iterator<Vector3> iterator() {
        return points().iterator();
    }

    //
    // Operators
    //

    @Nonnull
    @Override
    public ColoredVertex apply(@Nonnull UnaryOperator<Vector3> operation) {
        return new ColoredVertex(operation.apply(a), operation.apply(b), operation.apply(c), color);
    }

    @Nonnull
    @Override
    public Vertex inflate(double scale) {
        return new ColoredVertex(a.multiply(scale), b.multiply(scale), c.multiply(scale), color);
    }

    @Nonnull
    @Override
    public Vertex translate(@Nonnull Vector3 origin) {
        return new ColoredVertex(a.subtract(origin), b.subtract(origin), c.subtract(origin), color);
    }

    @Nonnull
    @Override
    public Vertex rotate(@Nonnull Quaternion rq) {
        return new ColoredVertex(a.rotate(rq), b.rotate(rq), c.rotate(rq), color);
    }

    @Nonnull
    @Override
    public Vertex transform(@Nonnull Vector3 origin, @Nonnull Quaternion rq, double scale) {
        return new ColoredVertex(
                a.subtract(origin).rotate(rq).multiply(scale),
                b.subtract(origin).rotate(rq).multiply(scale),
                c.subtract(origin).rotate(rq).multiply(scale),
                color
        );
    }

    //
    // Cloning
    //

    /**
     * All-args constructor used for cloning.
     * <b>Do not use elsewhere.</b>
     */
    private ColoredVertex(
            @Nonnull Vector3 a,
            @Nonnull Vector3 b,
            @Nonnull Vector3 c,
            @Nonnull Color color,
            @Nullable Vector3 centroid,
            @Nullable Tuple<Vector3> points,
            @Nullable Vector3 normal,
            double alpha
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
        this.centroid = centroid;
        this.points = points;
        this.normal = normal;
        this.alpha = alpha;
    }

    @Nonnull
    @Override
    public ColoredVertex copy() {
        // References to vectors do not need cloning as vectors are immutable (cloned vectors maintain equality)
        return new ColoredVertex(a, b, c, color, centroid, points, normal, alpha);
    }

    //
    // Util
    //

    @Override
    @Nonnull
    public String toString() {
        return "ColoredVertex{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", color=" + color +
                ", centroid=" + centroid +
                ", points=" + points +
                ", normal=" + normal +
                ", alpha=" + alpha +
                '}';
    }
}
