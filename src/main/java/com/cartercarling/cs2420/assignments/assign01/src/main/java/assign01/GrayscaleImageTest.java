package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * For testing the GrayscaleImage class.
 *
 * @author CS 2420 course staff and Carter Carling
 * @version 01/09/2025
 */
public class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage smallBright;
    private GrayscaleImage smallMedium;

    /**
     * A helper method that checks each pixel against an expected array of values.
     * This assumes that the getPixel method is working correctly.
     *
     * @param expected array of values
     * @param actual   GrayscaleImage to compare to the expected values
     */
    private void assertPixelValuesEqual(double[][] expected, GrayscaleImage actual) {
        for (int row = 0; row < expected.length; row++) {
            for (int col = 0; col < expected[0].length; col++) {
                assertEquals(expected[row][col], actual.getPixel(col, row), expected[row][col] * .0001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
    }

    @BeforeEach
    public void setUp() {
        this.smallSquare = new GrayscaleImage(new double[][]{ { 1, 2 }, { 3, 4 } });
        this.smallWide = new GrayscaleImage(new double[][]{ { 1, 2, 3 }, { 4, 5, 6 } });
        this.smallBright = new GrayscaleImage(new double[][]{ { 255, 254 }, { 253, 252 } });
        this.smallMedium = new GrayscaleImage(new double[][]{ { 127, 128 }, { 129, 130 } });
    }

    @Test
    public void testGetPixel() {
        assertEquals(1, this.smallWide.getPixel(0, 0));
        assertEquals(2, this.smallWide.getPixel(1, 0));
        assertEquals(3, this.smallWide.getPixel(2, 0));
        assertEquals(4, this.smallWide.getPixel(0, 1));
        assertEquals(5, this.smallWide.getPixel(1, 1));
        assertEquals(6, this.smallWide.getPixel(2, 1));

        assertThrows(IllegalArgumentException.class, () -> this.smallWide.getPixel(3, 1));
        assertThrows(IllegalArgumentException.class, () -> this.smallWide.getPixel(0, 4));
        assertThrows(IllegalArgumentException.class, () -> this.smallWide.getPixel(0, -1));
        assertThrows(IllegalArgumentException.class, () -> this.smallWide.getPixel(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> this.smallWide.getPixel(1, 2));
    }

    @Test
    public void testSetPixel() {
        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            smallSquareCopy.setPixel(0, 0, 10);
            double[][] expected = new double[][]{ { 10, 2 }, { 3, 4 } };
            this.assertPixelValuesEqual(expected, smallSquareCopy);
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            smallSquareCopy.setPixel(1, 0, 10);
            double[][] expected = new double[][]{ { 1, 10 }, { 3, 4 } };
            this.assertPixelValuesEqual(expected, smallSquareCopy);
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            smallSquareCopy.setPixel(0, 1, 10);
            double[][] expected = new double[][]{ { 1, 2 }, { 10, 4 } };
            this.assertPixelValuesEqual(expected, smallSquareCopy);
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            smallSquareCopy.setPixel(1, 1, 10);
            double[][] expected = new double[][]{ { 1, 2 }, { 3, 10 } };
            this.assertPixelValuesEqual(expected, smallSquareCopy);
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            smallSquareCopy.setPixel(0, 0, 0);
            double[][] expected = new double[][]{ { 0, 2 }, { 3, 4 } };
            this.assertPixelValuesEqual(expected, smallSquareCopy);
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            assertThrows(IllegalArgumentException.class, () -> smallSquareCopy.setPixel(1, 2, 1));
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            assertThrows(IllegalArgumentException.class, () -> smallSquareCopy.setPixel(1, 2, 1));
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            assertThrows(IllegalArgumentException.class, () -> smallSquareCopy.setPixel(2, 2, 1));
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            assertThrows(IllegalArgumentException.class, () -> smallSquareCopy.setPixel(-1, 0, 1));
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            assertThrows(IllegalArgumentException.class, () -> smallSquareCopy.setPixel(1, 1, 256));
        }

        {
            GrayscaleImage smallSquareCopy = this.smallSquare.copy();
            assertThrows(IllegalArgumentException.class, () -> smallSquareCopy.setPixel(1, 2, -1));
        }

    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testEqualsSelf() {
        assertEquals(this.smallSquare, this.smallSquare, "Image was not equal to itself");
        assertEquals(this.smallWide, this.smallWide, "Image was not equal to itself");
        assertEquals(this.smallBright, this.smallBright, "Image was not equal to itself");
        assertEquals(this.smallMedium, this.smallMedium, "Image was not equal to itself");
    }

    @Test
    public void testEqualsEquivalent() {
        {
            GrayscaleImage equivalent = new GrayscaleImage(new double[][]{ { 1, 2 }, { 3, 4 } });
            assertEquals(this.smallSquare, equivalent, "Image was not equal to a distinct but equivalent image");
        }

        {
            GrayscaleImage equivalent = new GrayscaleImage(new double[][]{ { 1, 2, 3 }, { 4, 5, 6 } });
            assertEquals(this.smallWide, equivalent, "Image was not equal to a distinct but equivalent image");
        }

        {
            GrayscaleImage equivalent = new GrayscaleImage(new double[][]{ { 255, 254 }, { 253, 252 } });
            assertEquals(this.smallBright, equivalent, "Image was not equal to a distinct but equivalent image");
        }

        {
            GrayscaleImage equivalent = new GrayscaleImage(new double[][]{ { 127, 128 }, { 129, 130 } });
            assertEquals(this.smallMedium, equivalent, "Image was not equal to a distinct but equivalent image");
        }
    }

    @Test
    public void testNormalized() {
        {
            GrayscaleImage norm = this.smallSquare.normalized();
            double scale = 127 / 2.5;
            double[][] expected = new double[][]{ { scale, 2 * scale }, { 3 * scale, 4 * scale } };
            this.assertPixelValuesEqual(expected, norm);
        }

        {
            GrayscaleImage norm = this.smallWide.normalized();
            double scale = 127 / 3.5;
            double[][] expected = new double[][]{ { scale, 2 * scale, 3 * scale }, { 4 * scale, 5 * scale, 6 * scale } };
            this.assertPixelValuesEqual(expected, norm);
        }

        {
            GrayscaleImage norm = this.smallBright.normalized();
            double scale = 127 / 253.5;
            double[][] expected = new double[][]{ { 255 * scale, 254 * scale }, { 253 * scale, 252 * scale } };
            this.assertPixelValuesEqual(expected, norm);
        }

        {
            GrayscaleImage norm = this.smallMedium.normalized();
            double scale = 127 / 128.5;
            double[][] expected = new double[][]{ { 127 * scale, 128 * scale }, { 129 * scale, 130 * scale } };
            this.assertPixelValuesEqual(expected, norm);
        }
    }

    @Test
    public void testInvert() {
        {
            this.smallSquare.invert();
            double[][] expected = new double[][]{ { 254, 253 }, { 252, 251 } };
            this.assertPixelValuesEqual(expected, this.smallSquare);
        }

        {
            this.smallWide.invert();
            double[][] expected = new double[][]{ { 254, 253, 252 }, { 251, 250, 249 } };
            this.assertPixelValuesEqual(expected, this.smallWide);
        }

        {
            this.smallBright.invert();
            double[][] expected = new double[][]{ { 0, 1 }, { 2, 3 } };
            this.assertPixelValuesEqual(expected, this.smallBright);
        }

        {
            this.smallMedium.invert();
            double[][] expected = new double[][]{ { 128, 127 }, { 126, 125 } };
            this.assertPixelValuesEqual(expected, this.smallMedium);
        }
    }

    @Test
    public void testMirrored() {
        {
            double[][] expected = new double[][]{ { 2, 1 }, { 4, 3 } };
            GrayscaleImage.mirrored(this.smallSquare);
            this.assertPixelValuesEqual(expected, this.smallSquare);
        }

        {
            double[][] expected = new double[][]{ { 3, 2, 1 }, { 6, 5, 4 } };
            GrayscaleImage.mirrored(this.smallWide);
            this.assertPixelValuesEqual(expected, this.smallWide);
        }

        {
            double[][] expected = new double[][]{ { 254, 255 }, { 252, 253 } };
            GrayscaleImage.mirrored(this.smallBright);
            this.assertPixelValuesEqual(expected, this.smallBright);
        }

        {
            double[][] expected = new double[][]{ { 128, 127 }, { 130, 129 } };
            GrayscaleImage.mirrored(this.smallMedium);
            this.assertPixelValuesEqual(expected, this.smallMedium);
        }
    }

}