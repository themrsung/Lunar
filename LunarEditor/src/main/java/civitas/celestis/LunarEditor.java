package civitas.celestis;

import civitas.celestis.ui.LFrame;
import jakarta.annotation.Nonnull;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

/**
 * <h2>LunarEditor</h2>
 * <p>
 *     The editor of Lunar engine.
 * </p>
 */
public final class LunarEditor {
    public static void main(@Nonnull String[] args) {
        start();
    }

    //
    // Lifecycle
    //

    public static void start() {
        mainFrame.setSize(1600, 900);
        mainFrame.setVisible(true);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
            }
        });
    }

    public static void stop() {
        mainFrame.dispose();
    }

    //
    // UI
    //

    /**
     * Gets the main frame of Lunar editor.
     * @return Main frame
     */
    @Nonnull
    public static LFrame getMainFrame() {
        return mainFrame;
    }

    private static final LFrame mainFrame = new LFrame("LunarEditor");

    //
    // Logging
    //

    /**
     * Gets the logger instance.
     * @return {@link Logger}
     */
    @Nonnull
    public static Logger getLogger() {
        return logger;
    }

    private static final Logger logger = Logger.getLogger("LunarEditor");
}