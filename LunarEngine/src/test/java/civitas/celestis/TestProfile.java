package civitas.celestis;

import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.physics.profile.PhysicsProfile;
import civitas.celestis.physics.solid.Solid;
import civitas.celestis.physics.solid.Tetrahedron;
import jakarta.annotation.Nonnull;

public class TestProfile implements PhysicsProfile {
    @Override
    public double mass() {
        return 1;
    }

    @Override
    public double volume() {
        return 1;
    }

    @Override
    public double density() {
        return 1;
    }

    @Override
    public double dragCoefficient(@Nonnull Vector3 direction) {
        return 0.5;
    }

    @Override
    public double crossSection(@Nonnull Vector3 direction) {
        return 1;
    }

    @Nonnull
    @Override
    public Solid build(@Nonnull Vector3 origin, @Nonnull Rotation rotation) {
        return new Tetrahedron(Vector3.ZERO.add(origin), Vector3.ZERO.add(origin), Vector3.ZERO.add(origin), Vector3.ZERO.add(origin));
    }
}
