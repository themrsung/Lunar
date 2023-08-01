package civitas.celestis.ui.element;

import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * <h2>Polygon2</h2>
 * <p>A 2D polygon which accepts non-integer input.</p>
 */
public class Polygon2 extends Polygon {
    /**
     * Creates an empty polygon.
     */
    public Polygon2() {
    }

    /**
     * Adds a point to this polygon.
     *
     * @param point Point to add
     */
    public void addPoint(@Nonnull Vector2 point) {
        final int x = (int) Math.round(point.x());
        final int y = (int) Math.round(point.y());

        addPoint(x, y);
    }
}
