package civitas.celestis.ui;

import jakarta.annotation.Nonnull;

import javax.swing.*;
import java.awt.*;

/**
 * <h2>LPanel</h2>
 * <p>A panel object for Lunar UI components.</p>
 */
public class LPanel extends JPanel {
    /**
     * Creates a new panel.
     * @param layout Layout manager object
     * @param isDoubleBuffered Whether this panel is double buffered
     */
    public LPanel(@Nonnull LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    /**
     * Creates a new panel.
     * @param layout Layout manager object
     */
    public LPanel(@Nonnull LayoutManager layout) {
        super(layout);
    }

    /**
     * Creates a new panel.
     * @param isDoubleBuffered Whether this panel is double buffered
     */
    public LPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    /**
     * Creates a new panel.
     */
    public LPanel() {
    }
}
