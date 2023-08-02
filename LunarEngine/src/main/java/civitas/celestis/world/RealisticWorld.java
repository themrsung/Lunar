package civitas.celestis.world;

import civitas.celestis.LunarEngine;
import civitas.celestis.event.object.ObjectsCollidedEvent;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.LunarObjects;
import civitas.celestis.object.base.BaseObject;
import civitas.celestis.object.world.AbstractWorld;
import civitas.celestis.util.group.Groups;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h2>RealisticWorld</h2>
 * <p>
 * A world with real-world-esq physics.
 * </p>
 */
public class RealisticWorld extends AbstractWorld {
    /**
     * Creates a new world.
     *
     * @param uniqueId   Unique identifier of this world
     * @param name       Name of this world
     * @param objects    List of initial objects in this world
     * @param overlaps   List of overlapping object pairs
     * @param gravity    Gravity vector of this world
     * @param airDensity Air density of this world
     */
    public RealisticWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull List<BaseObject> objects,
            @Nonnull List<Pair<BaseObject>> overlaps,
            @Nonnull Vector3 gravity,
            double airDensity
    ) {
        super(uniqueId, name, objects);
        this.overlaps = overlaps;
        this.gravity = gravity;
        this.airDensity = airDensity;
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId   Unique identifier of this world
     * @param name       Name of this world
     * @param objects    List of initial objects in this world
     * @param gravity    Gravity vector of this world
     * @param airDensity Air density of this world
     */
    public RealisticWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull List<BaseObject> objects,
            @Nonnull Vector3 gravity,
            double airDensity
    ) {
        this(uniqueId, name, objects, new ArrayList<>(), gravity, airDensity);
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId Unique identifier of this world
     * @param name     Name of this world
     * @param objects  List of initial objects in this world
     */
    public RealisticWorld(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull List<BaseObject> objects) {
        this(uniqueId, name, objects, Vector3.ZERO, 0);
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId Unique identifier of this world
     * @param name     Name of this world
     */
    public RealisticWorld(@Nonnull UUID uniqueId, @Nonnull String name) {
        this(uniqueId, name, new ArrayList<>());
    }

    @Nonnull
    private final List<Pair<BaseObject>> overlaps;
    @Nonnull
    private Vector3 gravity;
    private double airDensity;

    /**
     * Default tick behavior of a realistic world.
     *
     * @param delta Duration between the last tick and now in milliseconds.
     */
    @Override
    public void tick(long delta) {
        // Cache list to prevent concurrent modification
        final List<BaseObject> objects = getObjects();

        // Get all possible permutations
        final List<Pair<BaseObject>> pairs = Groups.pairsOf(objects);

        // Remove invalid cache
        overlaps.removeIf(o -> !pairs.contains(o));

        // Loop through pairs
        for (final Pair<BaseObject> p : pairs) {
            final BaseObject a = p.a();
            final BaseObject b = p.b();

            if (LunarObjects.overlaps(a, b)) {
                // Call collision event if this is the first overlap of given object pair
                if (!overlaps.contains(p)) LunarEngine.getEventManager().call(new ObjectsCollidedEvent(p));
            } else {
                overlaps.remove(p);
            }
        }

        // Convert delta to seconds
        final double seconds = delta / 1000d;

        // Scale gravity
        final Vector3 g = gravity.multiply(seconds);

        // Loop through objects
        for (final BaseObject o : objects) {
            // Apply gravity
            o.accelerate(g);

            //
            // Fluid resistance
            //

            // Initial value is set to global air density
            final AtomicReference<Double> fluidDensity = new AtomicReference<>(airDensity);

            // Apply density of highest overlapping object
            for (final Pair<BaseObject> p : overlaps) {
                if (!p.contains(o)) continue;
                fluidDensity.getAndUpdate(d -> Math.max(d, p.other(o).getPhysics().density()));
            }

            // Calculate drag force
            final double dragForce = o.getPhysics().dragCoefficient(o.getAcceleration().negate())
                    * fluidDensity.get()
                    * o.getPhysics().crossSection(o.getAcceleration().negate())
                    * o.getAcceleration().magnitude2();

            // Filter out illegal values
            if (!Double.isFinite(dragForce)) continue;
            if (dragForce <= 0) continue;

            // Calculate kinetic energy
            final double kineticEnergy = 0.5d * o.getPhysics().mass() * o.getAcceleration().magnitude();
            if (kineticEnergy == 0) continue; // No need to apply resistance

            // Calculate ratio of deceleration
            final double decelerationRatio = Math.max(Math.min(1, 1 - ((dragForce * seconds) / kineticEnergy)), 0);

            // Apply drag force
            o.setAcceleration(o.getAcceleration().multiply(decelerationRatio));

        }

        // Tick objects
        for (final BaseObject o : objects) {
            o.tick(delta);
        }
    }

    /**
     * Gets a list of overlapping object pairs in this world.
     *
     * @return List of overlapping pairs
     */
    @Nonnull
    public List<Pair<BaseObject>> getOverlaps() {
        return overlaps;
    }

    /**
     * Gets the gravity of this world.
     *
     * @return Gravity
     */
    @Nonnull
    public Vector3 getGravity() {
        return gravity;
    }

    /**
     * Gets the air density of this world.
     *
     * @return Air density
     */
    public double getAirDensity() {
        return airDensity;
    }

    /**
     * Sets the gravity of this world.
     *
     * @param gravity Gravity
     */
    public void setGravity(@Nonnull Vector3 gravity) {
        this.gravity = gravity;
    }

    /**
     * Sets the air density of this world.
     *
     * @param airDensity Air density
     */
    public void setAirDensity(double airDensity) {
        this.airDensity = airDensity;
    }
}
