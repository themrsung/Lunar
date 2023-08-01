package civitas.celestis.object.base;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.object.Tickable;
import civitas.celestis.object.movable.Movable;
import civitas.celestis.physics.PhysicsProfile;
import jakarta.annotation.Nonnull;

/**
 * <h2>BaseObject</h2>
 * <p>The superinterface for all placeable objects in-game.</p>
 */
public interface BaseObject extends Movable, Tickable {
    /**
     * Gets the physical profile of this object.
     *
     * @return Physical profile
     */
    @Nonnull
    PhysicsProfile getPhysics();

    /**
     * Gets the graphical model of this object.
     *
     * @return Graphical model
     */
    @Nonnull
    Model getModel();

    /**
     * Sets the physical profile of this object.
     *
     * @param physics Physical profile
     */
    void setPhysics(@Nonnull PhysicsProfile physics);

    /**
     * Sets the graphical model of this object.
     *
     * @param model Graphical model
     */
    void setModel(@Nonnull Model model);
}
