package civitas.celestis;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.base.AbstractObject;
import civitas.celestis.physics.profile.PhysicsProfile;
import jakarta.annotation.Nonnull;

import java.util.UUID;

public class TestObject extends AbstractObject {
    public TestObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Rotation rotation, @Nonnull PhysicsProfile physics, @Nonnull Model model) {
        super(uniqueId, location, rotation, physics, model);
    }

    public TestObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Vector3 acceleration, @Nonnull Rotation rotation, @Nonnull Rotation rotationRate, @Nonnull PhysicsProfile physics, @Nonnull Model model) {
        super(uniqueId, location, acceleration, rotation, rotationRate, physics, model);
    }
}
