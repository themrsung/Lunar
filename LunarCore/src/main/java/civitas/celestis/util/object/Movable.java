package civitas.celestis.util.object;

import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * <h2>Movable</h2>
 * <p>
 * A movable object.
 * Movable objects have location and rotation, as well as their corresponding derivative methods.
 * </p>
 */
public interface Movable extends Unique {
    /**
     * Gets the current location of this object.
     *
     * @return Location
     */
    @Nonnull
    Vector3 getLocation();

    /**
     * Gets the current acceleration of this object.
     *
     * @return Acceleration
     */
    @Nonnull
    Vector3 getAcceleration();

    /**
     * Gets the current rotation of this object.
     *
     * @return Rotation
     */
    @Nonnull
    Rotation getRotation();

    /**
     * Gets the rate of rotation of this object.
     *
     * @return Rate of rotation
     */
    @Nonnull
    Rotation getRotationRate();

    /**
     * Sets the location of this object.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Vector3 location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector3 acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Rotation rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rate Rate of rotation
     */
    void setRotationRate(@Nonnull Rotation rate);

    //
    // Derivative modifiers
    //
    // While these methods were put in place for easier modification,
    // they offer performance benefits as fewer method calls happen. (usually)
    //

    /**
     * Moves this object by given vector.
     *
     * @param v Vector to move object by
     */
    void move(@Nonnull Vector3 v);

    /**
     * Accelerates this object by given vector.
     *
     * @param v Vector to accelerate object by
     */
    void accelerate(@Nonnull Vector3 v);

    /**
     * Rotates this object by given rotation.
     *
     * @param r Rotation to rotate by
     */
    void rotate(@Nonnull Rotation r);

    /**
     * Changes the rate of rotation of this object by given rotation.
     *
     * @param r Rotation to rotate the rate of rotation by
     */
    void rotateRate(@Nonnull Rotation r);
}
