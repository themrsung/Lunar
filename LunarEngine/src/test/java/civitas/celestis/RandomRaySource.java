package civitas.celestis;

import civitas.celestis.graphics.ray.LightRay;
import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.RaySource;
import civitas.celestis.object.movable.AbstractMovable;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RandomRaySource extends AbstractMovable implements RaySource {
    public RandomRaySource(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Rotation rotation) {
        super(uniqueId, location, rotation);
    }

    public RandomRaySource() {
        super(UUID.randomUUID(), Vector3.ZERO, Rotation.NO_ROTATION);
    }

    @Nonnull
    @Override
    public List<Ray> shoot() {
        final List<Ray> rays = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            final Vector3 origin = getLocation();
            final Vector3 direction = Vector3.POSITIVE_Z.rotate(
                    new Quaternion(new Quaternion(Math.random(), Math.random(), Math.random(), Math.random()).normalize())
            );

            rays.add(new LightRay(origin, direction, Color.WHITE, 1));
        }

        return rays;
    }
}
