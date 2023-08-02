package civitas.celestis.graphics.model.wavefront;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.graphics.vertex.Vertex;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>WavefrontModel</h2>
 * <p>A model based on Wavefront .OBJ files.</p>
 */
public class WavefrontModel implements Model {
    /**
     * Creates a new Wavefront model.
     *
     * @param object 3D model data
     * @param scale  Scale to apply
     */
    public WavefrontModel(@Nonnull Obj object, double scale) {
        for (int i = 0; i < object.getNumFaces(); i++) {
            final ObjFace face = object.getFace(i);

            final Vector3 v1 = WavefrontParser.wavefrontToLunar(object.getVertex(face.getVertexIndex(0)));
            final Vector3 v2 = WavefrontParser.wavefrontToLunar(object.getVertex(face.getVertexIndex(1)));
            final Vector3 v3 = WavefrontParser.wavefrontToLunar(object.getVertex(face.getVertexIndex(2)));

            vertices.add(new Vertex(v1, v2, v3, Color.GRAY).inflate(scale));
        }
    }

    private final List<Vertex> vertices = new ArrayList<>();

    @Nonnull
    @Override
    public List<Vertex> vertices(@Nonnull Vector3 origin, @Nonnull Rotation rotation) {
        return vertices.stream()
                .map(v -> v.apply(p -> p.rotate(rotation)).apply(p -> p.add(origin)))
                .toList();
    }
}
