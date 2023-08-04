package civitas.celestis.graphics;

import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * <h2>Colors</h2>
 * <p>Contains utility methods related to {@link Color}.</p>
 */
public final class Colors {
    /**
     * Mixes two colors with different weights.
     *
     * @param c1 First color
     * @param c2 Second color
     * @param w1 Weight of first color
     * @param w2 Weight of second color
     * @return Resulting color
     * @throws IllegalArgumentException When {@code w1 + w2} is zero
     */
    @Nonnull
    public static Color mix(@Nonnull Color c1, @Nonnull Color c2, double w1, double w2) throws IllegalArgumentException {
        final double denominator = w1 + w2;
        if (denominator == 0) throw new IllegalArgumentException("Cannot mix colors when the sum of weights is zero.");

        final double r1 = c1.getRed();
        final double g1 = c1.getGreen();
        final double b1 = c1.getBlue();
        final double a1 = c1.getAlpha();

        final double r2 = c2.getRed();
        final double g2 = c2.getGreen();
        final double b2 = c2.getBlue();
        final double a2 = c2.getAlpha();

        final double r = r1 * w1 + r2 * w2;
        final double g = g1 * w1 + g2 * w2;
        final double b = b1 * w1 + b2 * w2;
        final double a = a1 * w1 + a2 * w2;

        final int red = (int) Math.round(r / denominator);
        final int blue = (int) Math.round(b / denominator);
        final int green = (int) Math.round(g / denominator);
        final int alpha = (int) Math.round(a / denominator);

        return new Color(red, green, blue, alpha);
    }
}
