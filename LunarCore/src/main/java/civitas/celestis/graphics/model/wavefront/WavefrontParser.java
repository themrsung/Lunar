package civitas.celestis.graphics.model.wavefront;

import civitas.celestis.math.vector.Vector3;
import de.javagl.obj.FloatTuple;
import jakarta.annotation.Nonnull;

/**
 * <h2>WavefrontParser</h2>
 * <p>Contains parsing utility methods for .OBJ files.</p>
 */
public final class WavefrontParser {
    /**
     * Converts a Wavefront coordinate into a Lunar coordinate.
     *
     * @param in Input coordinate
     * @return Converted coordinate
     */
    @Nonnull
    public static Vector3 wavefrontToLunar(@Nonnull FloatTuple in) {
        return new Vector3(in.getZ(), in.getY(), in.getX());
    }
}
