package civitas.celestis.object.base;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.movable.AbstractMovable;
import civitas.celestis.physics.profile.PhysicsProfile;
import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * <h2>AbstractObject</h2>
 * <p>An adapter class for {@link BaseObject}.</p>
 */
public abstract class AbstractObject extends AbstractMovable implements BaseObject {
    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param rotation Rotation of this object
     * @param physics  Physical profile of this object
     * @param model    Geometric model of this object
     */
    public AbstractObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Rotation rotation,
            @Nonnull PhysicsProfile physics,
            @Nonnull Model model
    ) {
        super(uniqueId, location, rotation);
        this.physics = physics;
        this.model = model;
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId     Unique identifier of this object
     * @param location     Location of this object
     * @param acceleration Acceleration of this object
     * @param rotation     Rotation of this object
     * @param rotationRate Rate of rotation of this object
     * @param physics      Physical profile of this object
     * @param model        Geometric model of this object
     */
    public AbstractObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Vector3 acceleration,
            @Nonnull Rotation rotation,
            @Nonnull Rotation rotationRate,
            @Nonnull PhysicsProfile physics,
            @Nonnull Model model
    ) {
        super(uniqueId, location, acceleration, rotation, rotationRate);
        this.physics = physics;
        this.model = model;
    }

    @Nonnull
    private PhysicsProfile physics;
    @Nonnull
    private Model model;

    /**
     * Default tick behavior.
     *
     * @param delta Duration between the last tick and now in milliseconds.
     */
    @Override
    public void tick(long delta) {
        // Convert delta to seconds
        final double seconds = delta / 1000d;

        // Apply acceleration
        move(getAcceleration().multiply(seconds));

        // Apply rate of rotation
        rotate(getRotationRate().scale(seconds));
    }

    @Override
    @Nonnull
    public PhysicsProfile getPhysics() {
        return physics;
    }

    @Override
    @Nonnull
    public Model getModel() {
        return model;
    }

    @Override
    public void setPhysics(@Nonnull PhysicsProfile physics) {
        this.physics = physics;
    }

    @Override
    public void setModel(@Nonnull Model model) {
        this.model = model;
    }
}
