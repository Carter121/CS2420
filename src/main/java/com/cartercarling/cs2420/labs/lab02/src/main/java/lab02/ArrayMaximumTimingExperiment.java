package lab02;

import java.util.Arrays;
import java.util.Random;

/**
 * This class defines a timing experiment to measure the running time for
 * finding the maximum integer in arrays of various sizes.
 *
 * @author CS 2420 course staff
 * @version January 17, 2025
 */
public class ArrayMaximumTimingExperiment {

    private final static Random rng = new Random();
    private final int arraySizeMin;
    private final int arraySizeCount;
    private final int arraySizeStep;
    private final int experimentIterationCount;

    private Integer[] array;
    public static void main(String[] args) {
        int arraySizeMin = 50000;
        int arraySizeCount = 100;
        int arraySizeStep = 1000;
        int experimentIterationCount = 50;

        // Instantiate the timing experiment.
        ArrayMaximumTimingExperiment timingExperiment = new ArrayMaximumTimingExperiment(arraySizeMin, arraySizeCount, arraySizeStep, experimentIterationCount);

        // Run the experiment and print the results.
        timingExperiment.printResults();
    }

    /**
     * Constructs a timing experiment for computing the maximum integer in an array.
     *
     * @param arraySizeMin - minimum array size
     * @param arraySizeCount - number of array sizes to use in the experiment
     * @param arraySizeStep - step size between consecutive array sizes
     * @param experimentIterationCount - number of times to run computation for a given array size
     */
    public ArrayMaximumTimingExperiment(int arraySizeMin, int arraySizeCount, int arraySizeStep, int experimentIterationCount) {
        this.arraySizeMin = arraySizeMin;
        this.arraySizeCount = arraySizeCount;
        this.arraySizeStep = arraySizeStep;
        this.experimentIterationCount = experimentIterationCount;
    }

    /**
     * Runs the timing experiment and prints the results.
     */
    public void printResults() {
        //TODO: Print as CSV???
        System.out.println();
        System.out.println("array size\ttime (ns)");

        // Initialize the array size at the minimum value.
        int arraySize = this.arraySizeMin;

        // Iterate through the array sizes.
        for (int i = 0; i < this.arraySizeCount; i++) {
            // Compute the median elapsed time for the given array size.
            long medianElapsedTime = this.computeMedianElapsedTime(arraySize);

            // Print the results.
            System.out.println(arraySize + "\t\t" + medianElapsedTime);

            // Increment the array size.
            arraySize += this.arraySizeStep;
        }
    }

    /**
     * Computes the median time elapsed for finding the maximum of an array.
     *
     * @param arraySize - the size of the array
     * @return the median elapsed time
     */
    private long computeMedianElapsedTime(int arraySize) {
        long[] elapsedTimes = new long[this.experimentIterationCount];
        for (int i = 0; i < this.experimentIterationCount; i++) {
            elapsedTimes[i] = this.computeElapsedTime(arraySize);
        }
        Arrays.sort(elapsedTimes);
        return elapsedTimes[this.experimentIterationCount / 2];
    }

    /**
     * Computes the time elapsed for finding the maximum of an array.
     *
     * @param arraySize - size of the array
     * @return the time elapsed
     */
    private long computeElapsedTime(int arraySize) {
        this.setupArray(arraySize);

        long startTime = System.nanoTime();
        this.runComputation();
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    /**
     * Populates the array with random integers.
     *
     * @param arraySize - size of the array
     */
    private void setupArray(int arraySize) {
        this.array = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            this.array[i] = rng.nextInt();
        }
    }

    /**
     * Runs the computeMaximum method for the array.
     */
    private void runComputation() {
        ArrayUtility.computeMaximum(this.array);
    }

}