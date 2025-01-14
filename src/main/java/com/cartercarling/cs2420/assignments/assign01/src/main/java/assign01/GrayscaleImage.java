package assign01;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel"
 * brightness values: 255 is "white", 127 is "gray", and 0 is "black" with
 * intermediate values in between.
 *
 * @author CS 2420 course staff and Carter Carling
 * @version 01/09/2025
 */
@SuppressWarnings("unused")
public class GrayscaleImage {

    private final double[][] imageData; // array of pixel brightness values

    /**
     * Initializes an image from a 2D array of doubles. This constructor creates a
     * copy of the input array.
     *
     * @param data initial pixel values
     *
     * @throws IllegalArgumentException if the input array is empty or "jagged"
     *                                  meaning not all rows are the same length
     */
    @SuppressWarnings("ManualArrayCopy")
    public GrayscaleImage(double[][] data) {
        if (data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Image is empty");
        }

        this.imageData = new double[data.length][data[0].length];
        for (int row = 0; row < this.imageData.length; row++) {
            if (data[row].length != this.imageData[row].length) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
            for (int col = 0; col < this.imageData[row].length; col++) {
                this.imageData[row][col] = data[row][col];
            }
        }
    }

    /**
     * Fetches an image from the specified URL and converts it to grayscale. Uses
     * the AWT Graphics2D class to do the conversion, so it may add an item to your
     * dock/menu bar as if you are loading a GUI program.
     *
     * @param url from which to download the image
     *
     * @throws IOException if the image cannot be downloaded for some reason
     */
    public GrayscaleImage(URL url) throws IOException {
        BufferedImage inputImage = ImageIO.read(url);
        // Converts input image to grayscale based on information from
        // https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models
        BufferedImage grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = grayImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();
        this.imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

        // Raster is basically a width x height x 1 3D array
        WritableRaster grayRaster = grayImage.getRaster();
        for (int row = 0; row < this.imageData.length; row++) {
            for (int col = 0; col < this.imageData[0].length; col++) {
                // getSample parameters are x (our column) and y (our row); i.e., "backwards"
                this.imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
            }
        }
    }

    /**
     * Mirrors the given image
     *
     * @param image The image to mirror
     */
    public static void mirrored(GrayscaleImage image) {
        int imageWidth = image.imageData[0].length;
        int imageHeight = image.imageData.length;

        int centerX = imageWidth / 2;

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < centerX; x++) {
                int otherX = imageWidth - 1 - x;

                double current = image.getPixel(x, y);
                double other = image.getPixel(otherX, y);

                image.setPixel(x, y, other);
                image.setPixel(otherX, y, current);

            }
        }

    }

    /**
     * Saves the image as a PNG file.
     *
     * @param filename of the created image file
     *
     * @throws IOException if the file cannot be written
     */
    public void savePNG(File filename) throws IOException {
        BufferedImage outputImage = new BufferedImage(this.imageData[0].length, this.imageData.length,
                BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = outputImage.getRaster();
        for (int row = 0; row < this.imageData.length; row++) {
            for (int col = 0; col < this.imageData[0].length; col++) {
                raster.setSample(col, row, 0, this.imageData[row][col]);
            }
        }
        ImageIO.write(outputImage, "png", filename);
    }

    /**
     * Gets the pixel brightness at the specified location
     *
     * @param x The x coordinate of the pixel
     * @param y The y coordinate of the pixel
     *
     * @return The brightness value of the pixel
     */
    public double getPixel(int x, int y) {
        int maxHeight = this.imageData.length;
        int maxWidth = this.imageData[0].length;

        if (y >= maxHeight || y < 0 || x >= maxWidth || x < 0)
            throw new IllegalArgumentException("Pixel out of bounds");

        return this.imageData[y][x];
    }

    /**
     * Sets the brightness value of the pixel at the specified location
     *
     * @param x               The x coordinate of the pixel
     * @param y               The y coordinate of the pixel
     * @param brightnessValue The brightness value to set the pixel to
     */
    public void setPixel(int x, int y, double brightnessValue) {
        int maxHeight = this.imageData.length;
        int maxWidth = this.imageData[0].length;

        if (y >= maxHeight || y < 0 || x >= maxWidth || x < 0)
            throw new IllegalArgumentException("Pixel out of bounds");

        if (brightnessValue > 255 || brightnessValue < 0)
            throw new IllegalArgumentException("Brightness value out of bounds");

        this.imageData[y][x] = brightnessValue;
    }

    /**
     * Checks if other object is equal to current one
     *
     * @param other The object to compare with
     *
     * @return True if the objects are equal, false if not.
     */
    public boolean equals(Object other) {
        if (!(other instanceof GrayscaleImage otherImage))
            return false;

        boolean sameSize = this.imageData.length == otherImage.imageData.length && this.imageData[0].length == otherImage.imageData[0].length;

        if (!sameSize)
            return false;

        boolean allPixelsSame = true;

        int imageWidth = this.imageData[0].length;
        int imageHeight = this.imageData.length;

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                if (this.getPixel(x, y) != otherImage.getPixel(x, y)) {
                    allPixelsSame = false;
                    break;
                }
            }
        }

        return allPixelsSame;
    }

    /**
     * Gets the average brightness of the image
     *
     * @return The average brightness of the image
     */
    public double averageBrightness() {
        int imageWidth = this.imageData[0].length;
        int imageHeight = this.imageData.length;
        int totalPixels = imageWidth * imageHeight;

        double totalBrightnessValue = 0;
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                totalBrightnessValue += this.getPixel(x, y);
            }
        }

        return totalBrightnessValue / totalPixels;
    }

    /**
     * Creates a new GrayscaleImage with an average brightness value of 127
     *
     * @return a GrayscaleImage with an average brightness of 127
     *
     * @see <a href="https://en.wikipedia.org/wiki/Feature_scaling#Methods">Feature scaling (Wikipedia)</a>
     */
    public GrayscaleImage normalized() {
        int desiredAverage = 127;
        double averageBrightness = this.averageBrightness();

        double scaleFactor = desiredAverage / averageBrightness;

        int imageWidth = this.imageData[0].length;
        int imageHeight = this.imageData.length;

        double[][] newImageData = new double[imageHeight][imageWidth];

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                newImageData[y][x] = this.getPixel(x, y) * scaleFactor;
            }
        }

        return new GrayscaleImage(newImageData);
    }

    /**
     * Inverts the brightness of the image
     */
    public void invert() {
        int imageWidth = this.imageData[0].length;
        int imageHeight = this.imageData.length;

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                double currentValue = this.imageData[y][x];
                this.imageData[y][x] = 255 - currentValue;
            }
        }
    }

    /**
     * Creates an exact copy of the current object
     *
     * @return A copy of the current object
     */
    public GrayscaleImage copy() {
        return new GrayscaleImage(this.imageData);
    }

}