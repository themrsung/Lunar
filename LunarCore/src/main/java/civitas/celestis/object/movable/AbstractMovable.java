package civitas.celestis.object.movable;

import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.unique.AbstractUnique;
import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * <h2>AbstractMovable</h2>
 * <p>An adapter class for {@link Movable}.</p>
 */
public abstract class AbstractMovable extends AbstractUnique implements Movable {
    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param rotation Rotation of this object
     */
    public AbstractMovable(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Rotation rotation) {
        this(uniqueId, location, Vector3.ZERO, rotation, Rotation.NO_ROTATION);
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId     Unique identifier of this object
     * @param location     Location of this object
     * @param acceleration Acceleration of this object
     * @param rotation     Rotation of this object
     * @param rotationRate Rate of rotation of this object
     */
    public AbstractMovable(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Vector3 acceleration, @Nonnull Rotation rotation, @Nonnull Rotation rotationRate) {
        super(uniqueId);
        this.location = location;
        this.acceleration = acceleration;
        this.rotation = rotation;
        this.rotationRate = rotationRate;
    }


    @Nonnull
    private Vector3 location;
    @Nonnull
    private Vector3 acceleration;
    @Nonnull
    private Rotation rotation;
    @Nonnull
    private Rotation rotationRate;

    @Override
    @Nonnull
    public Vector3 getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Vector3 getAcceleration() {
        return acceleration;
    }

    @Override
    @Nonnull
    public Rotation getRotation() {
        return rotation;
    }

    @Override
    @Nonnull
    public Rotation getRotationRate() {
        return rotationRate;
    }

    @Override
    public void setLocation(@Nonnull Vector3 location) {
        this.location = location;
    }

    @Override
    public void setAcceleration(@Nonnull Vector3 acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void setRotation(@Nonnull Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public void setRotationRate(@Nonnull Rotation rotationRate) {
        this.rotationRate = rotationRate;
    }

    @Override
    public void move(@Nonnull Vector3 v) {
        this.location = location.add(v);
    }

    @Override
    public void accelerate(@Nonnull Vector3 v) {
        this.acceleration = acceleration.add(v);
    }

    @Override
    public void rotate(@Nonnull Rotation r) {
        this.rotation = rotation.rotate(r);
    }

    @Override
    public void rotateRate(@Nonnull Rotation r) {
        this.rotationRate = rotationRate.rotate(r);
    }
}
