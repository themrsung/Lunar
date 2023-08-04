package civitas.celestis.graphics.ray;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * <h2>LightRay</h2>
 * <p>A ray of light.</p>
 */
public class LightRay implements Ray {
    /**
     * Creates a new light ray.
     *
     * @param origin    Origin of this ray
     * @param direction Direction of this ray
     * @param color     Color of this ray
     * @param intensity Intensity of this ray
     */
    public LightRay(@Nonnull Vector3 origin, @Nonnull Vector3 direction, @Nonnull Color color, double intensity) {
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


    @Nonnull
    @Override
    public Vector3 origin() {
        return origin;
    }

    @Nonnull
    @Override
    public Vector3 direction() {
        return direction;
    }

    @Nonnull
    @Override
    public Vector3 destination(double t) {
        return origin.add(direction.multiply(t));
    }

    /**
     * Gets the color of this light ray.
     *
     * @return Color of ray
     */
    @Nonnull
    public Color color() {
        return color;
    }

    /**
     * Gets the intensity of this light ray.
     *
     * @return Intensity of ray
     */
    public double intensity() {
        return intensity;
    }
}
