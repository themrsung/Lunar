package civitas.celestis.physics;

import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

/**
 * <h2>PhysicsProfile</h2>
 * <p>
 *     Represents an object's physical profile.
 * </p>
 */
public interface PhysicsProfile {
    /**
     * Gets the mass of this profile.
     * @return Mass
     */
    double mass();

    /**
     * Gets the volume of this profile.
     * @return Volume
     */
    double volume();

    /**
     * Gets the density of this profile.
     * @return Density
     */
    double density();

    /**
     * Gets the discrete solid of this profile.
     * @param origin Absolute origin of this profile (most likely the location of its parent object)
     * @param rotation Rotation of this profile (most likely the rotation of its parent object)
     * @return Discrete solid of profile
     */
    @Nonnull
    Solid build(@Nonnull Vector3 origin, @Nonnull Rotation rotation);
}
