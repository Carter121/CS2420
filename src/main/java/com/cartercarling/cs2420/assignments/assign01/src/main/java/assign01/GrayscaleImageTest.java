package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * For testing the GrayscaleImage class.
 * 
 * @author CS 2420 course staff and Carter Carling
 * @version 01/09/2025
 */
public class GrayscaleImageTest {

	private GrayscaleImage smallSquare;
	private GrayscaleImage smallWide;

	/**
	 * A helper method that checks each pixel against an expected array of values.
	 * This assumes that the getPixel method is working correctly.
	 * 
	 * @param expected array of values
	 * @param actual GrayscaleImage to compare to the expected values
	 */
	private void assertPixelValuesEqual(double[][] expected, GrayscaleImage actual) {
		for(int row = 0; row < expected.length; row++) {
			for(int col = 0; col < expected[0].length; col++) {
				assertEquals(expected[row][col], actual.getPixel(col, row), expected[row][col] * .0001,
						"pixel at row: " + row + " col: " + col + " incorrect");
			}
		}
	}

	@BeforeEach
	public void setUp() {
        this.smallSquare = new GrayscaleImage(new double[][] { { 1, 2 }, { 3, 4 } });
        this.smallWide = new GrayscaleImage(new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
	}

	@Test
	public void testGetPixel() {
		assertEquals(1, this.smallWide.getPixel(0, 0));
		assertEquals(2, this.smallWide.getPixel(1, 0));
		assertEquals(3, this.smallWide.getPixel(2, 0));
		assertEquals(4, this.smallWide.getPixel(0, 1));
		assertEquals(5, this.smallWide.getPixel(1, 1));
		assertEquals(6, this.smallWide.getPixel(2, 1));
	}

	@Test
	public void testEqualsSelf() {
		assertEquals(this.smallSquare, this.smallSquare, "Image was not equal to itself");
	}

	@Test
	public void testEqualsEquivalent() {
		GrayscaleImage equivalent = new GrayscaleImage(new double[][] { { 1, 2 }, { 3, 4 } });
		assertEquals(this.smallSquare, equivalent, "Image was not equal to a distinct but equivalent image");
	}

	@Test
	public void testNormalized() {
		GrayscaleImage smallNorm = this.smallSquare.normalized();
		double scale = 127 / 2.5;
		double[][] expected = new double[][] { { scale, 2 * scale }, { 3 * scale, 4 * scale } };
        this.assertPixelValuesEqual(expected, smallNorm);
	}

	@Test
	public void testInvert() {
        this.smallSquare.invert();
		double[][] expected = new double[][] { { 254, 253 }, { 252, 251 } };
        this.assertPixelValuesEqual(expected, this.smallSquare);
	}

	@Test
	public void testMirrored() {
		double[][] expected = new double[][] { { 2, 1 }, { 4, 3 } };
		GrayscaleImage.mirrored(this.smallSquare);
        this.assertPixelValuesEqual(expected, this.smallSquare);
	}
}