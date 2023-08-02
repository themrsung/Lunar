package civitas.celestis.ui.component.camera;

import civitas.celestis.graphics.scene.Scene;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.base.BaseObject;
import civitas.celestis.object.world.World;
import civitas.celestis.ui.LPanel;
import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * <h2>FreeCamera</h2>
 * <p>A camera object which is not bound to a physical object.</p>
 */
public class FreeCamera extends LPanel {
    public FreeCamera(@Nonnull World world) {
        this.world = world;
        this.scene = new Scene();

        this.origin = Vector3.ZERO;
        this.angle = Rotation.NO_ROTATION;
    }

    @Nonnull
    private final World world;
    @Nonnull
    private final Scene scene;

    @Nonnull
    public World getWorld() {
        return world;
    }

    @Nonnull
    public Scene getScene() {
        return scene;
    }

    @Nonnull
    private Vector3 origin;
    @Nonnull
    private Rotation angle;

    @Nonnull
    public Vector3 getOrigin() {
        return origin;
    }

    @Nonnull
    public Rotation getAngle() {
        return angle;
    }

    public void setOrigin(@Nonnull Vector3 origin) {
        this.origin = origin;
    }

    public void setAngle(@Nonnull Rotation angle) {
        this.angle = angle;
    }

    /**
     * Renders the scene, then repaints this panel.
     */
    public void renderAndRepaint() {
        render();
        repaint();
    }

    /**
     * Renders the scene.
     */
    public void render() {
        // Prevent concurrent re-renders
        if (rendering) return;

        // Prevent modification of scene object while painting
        if (painting) return;

        // Mark state as rendering
        rendering = true;

        // Clear scene
        scene.clearVertices();

        // Add vertices to scene
        for (final BaseObject o : world.getObjects()) {
            scene.addVertices(o.getModel().vertices(o.getLocation(), o.getRotation()));
        }

        // Render scene
        scene.render();

        // Mark state as no longer rendering
        rendering = false;
    }

    /**
     * Paints the rendered scene on-screen.
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(@Nonnull Graphics g) {
        // Check if camera is already painting
        if (painting) return;

        // Check if camera is rendering
        if (rendering) return;

        // Mark state as painting
        painting = true;

        // Clear screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Move origin
        g.translate(getWidth() / 2, getHeight() / 2);

        // Paint polygons
        scene.getVertices().stream()
                .map(v -> v.apply(p -> p.rotate(angle)).apply(p -> p.subtract(origin)))
                .filter(v -> v.centroid().z() >= 0)
                .sorted((v1, v2) -> Double.compare(v2.centroid().magnitude2(), v1.centroid().magnitude2()))
                .forEach(v -> {
                    g.setColor(v.color());
                    g.fillPolygon(v.polygon(500));
                });

        // Mark state as no longer painting
        painting = false;
    }

    private boolean painting = false;
    private boolean rendering = false;
}
