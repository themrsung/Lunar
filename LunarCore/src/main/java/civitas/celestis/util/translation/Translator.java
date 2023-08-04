package civitas.celestis.util.translation;

import civitas.celestis.math.vector.Vector3;
import de.javagl.obj.FloatTuple;
import jakarta.annotation.Nonnull;

/**
 * <h2>Translator</h2>
 * <p>
 * Contains translation utility methods.
 * </p>
 */
public final class Translator {
    /**
     * Converts a Wavefront vertex to a {@link Vector3}.
     *
     * @param in Input values
     * @return Converted vector
     */
    @Nonnull
    public static Vector3 wavefrontVertexToVector3(@Nonnull FloatTuple in) {
        return new Vector3(in.getZ(), in.getY(), in.getX());
    }
}
