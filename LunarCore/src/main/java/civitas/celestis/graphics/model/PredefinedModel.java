package civitas.celestis.graphics.model;

import civitas.celestis.graphics.vertex.ColoredVertex;
import civitas.celestis.graphics.vertex.Vertex;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.translation.Translator;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>PredefinedModel</h2>
 * <p>
 * A model where the points and vertices are predefined.
 * </p>
 */
public class PredefinedModel implements Model {
    /**
     * Creates a new predefined model.
     *
     * @param points   List of points of this model
     * @param vertices List of vertices of this model
     */
    public PredefinedModel(@Nonnull List<Vector3> points, @Nonnull List<Vertex> vertices) {
        this.points = List.copyOf(points); // Make lists immutable
        this.vertices = List.copyOf(vertices);
    }

    /**
     * Creates a new predefined model from a Wavefront OBJ file.
     *
     * @param obj   Object data provided by {@link Obj}
     * @param color Default color to set all vertices to
     */
    public PredefinedModel(@Nonnull Obj obj, @Nonnull Color color) {
        final List<Vector3> p = new ArrayList<>();
        final List<Vertex> v = new ArrayList<>();

        //
        // Reference
        //
        // A Wavefront vertex is a tuple of three floating points,
        // which is equivalent to a Lunar Vector3.
        //
        // A Wavefront face is a tuple of three Wavefront vertices,
        // which is equivalent to a Lunar Vertex.
        //

        // Add points
        for (int i = 0; i < obj.getNumVertices(); i++) {
            p.add(Translator.wavefrontVertexToVector3(obj.getVertex(i)));
        }

        // Add vertices
        for (int i = 0; i < obj.getNumFaces(); i++) {
            final ObjFace f = obj.getFace(i);

            // Conserve memory by sharing vector references
            final Vector3 a = p.get(f.getVertexIndex(0));
            final Vector3 b = p.get(f.getVertexIndex(1));
            final Vector3 c = p.get(f.getVertexIndex(2));

            v.add(new ColoredVertex(a, b, c, color));
        }

        this.points = List.copyOf(p); // Make lists immutable
        this.vertices = List.copyOf(v);
    }

    @Nonnull
    private final List<Vector3> points;
    @Nonnull
    private final List<Vertex> vertices;

    @Override
    @Nonnull
    public List<Vector3> getPoints() {
        return points; // It is safe to return a direct pointer to an immutable list of immutable elements
    }

    @Override
    public int getPointCount() {
        return points.size();
    }

    @Override
    @Nonnull
    public List<Vertex> getVertices() {
        final List<Vertex> copied = new ArrayList<>();

        for (final Vertex v : vertices) {
            copied.add(v.copy()); // Deep-copy vertices
        }

        return copied;
    }

    @Override
    public int getVertexCount() {
        return vertices.size();
    }

    /**
     * Serializes this model into a string.
     *
     * @return Serialized string of {@code this}
     */
    @Override
    @Nonnull
    public String toString() {
        return "PredefinedModel{" + "\n" +
                "  points=" + points + "\n" +
                "  vertices=" + vertices + "\n" +
                '}';
    }
}
