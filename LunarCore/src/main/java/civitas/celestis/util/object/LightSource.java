package civitas.celestis.util.object;

import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * <h2>LightSource</h2>
 * <p>
 * A source of light.
 * Light sources emit either directional or omnidirectional light
 * of a certain shade and intensity.
 * </p>
 */
public interface LightSource extends Movable {
    @Nonnull
    Color getLightColor();

    void setLightColor(@Nonnull Color c);
}
