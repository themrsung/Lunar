package civitas.celestis.graphics.ray;

import civitas.celestis.graphics.triangle.Vertex;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.awt.*;

/**
 * <h2>LightRay</h2>
 * <p>
 * A ray of light.
 * Lunar engine assumes the travel of light is instantaneous.
 * </p>
 */
public final class LightRay implements Ray {
    /**
     * Creates a new light ray.
     *
     * @param origin Origin of ray
     * @param direction Direction of ray (automatically normalized)
     * @param color Color of ray
     * @param intensity Intensity of ray
     */
    public LightRay(
            @Nonnull Vector3 origin,
            @Nonnull Vector3 direction,
            @Nonnull Color color,
            double intensity
    ) {
        this.origin = origin;
        this.direction = direction.normalize();
        this.color = color;
        this.intensity = intensity;
    }

    @Nonnull
    private final Vector3 origin;
    @Nonnull
    private final Vector3 direction;
    @Nonnull
    private final Color color;
    private final double intensity;

    @Override
    @Nonnull
    public Vector3 origin() {return origin;}

    @Override
    @Nonnull
    public Vector3 direction() {return direction;}

    @Nonnull
    @Override
    public Vector3 destination(double t) {
        return origin.add(direction.multiply(t));
    }

    @Nullable
    @Override
    public LightRay reflect(@Nonnull Vertex surface) {
        final Vector3 i = surface.intersection(this);
        if (i == null) return null;

        final Vector3 r = surface.reflection(direction);
        return new LightRay(i, r, color, intensity); // FIXME add Material and reflectionFactor or something
    }

    /**
     * Gets the color of this ray.
     * @return Color of ray
     */
    @Nonnull
    public Color color() {return color;}

    /**
     * Gets the intensity of this ray.
     * @return Intensity factor
     */
    public double intensity() {return intensity;}
}
