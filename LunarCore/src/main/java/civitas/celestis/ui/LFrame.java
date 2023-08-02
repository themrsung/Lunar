package civitas.celestis.ui;

import jakarta.annotation.Nonnull;

import javax.swing.*;
import java.awt.*;

/**
 * <h2>LFrame</h2>
 * <p>A Lunar frame.</p>
 */
public class LFrame extends JFrame {
    /**
     * Default constructor.
     *
     * @throws HeadlessException When the hardware environment does not support the software
     */
    public LFrame() throws HeadlessException {
    }

    /**
     * Creates a new frame.
     *
     * @param gc Graphics configuration object
     */
    public LFrame(@Nonnull GraphicsConfiguration gc) {
        super(gc);
    }

    /**
     * Creates a new frame.
     *
     * @param title Title of frame
     * @throws HeadlessException When the hardware environment does not support the software
     */
    public LFrame(@Nonnull String title) throws HeadlessException {
        super(title);
    }

    /**
     * Creates a new frame.
     *
     * @param title Title of frame
     * @param gc    Graphics configuration object
     */
    public LFrame(@Nonnull String title, @Nonnull GraphicsConfiguration gc) {
        super(title, gc);
    }
}
