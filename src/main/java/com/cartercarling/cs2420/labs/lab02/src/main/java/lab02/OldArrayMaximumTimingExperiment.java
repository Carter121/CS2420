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
public class OldArrayMaximumTimingExperiment {

    private final static Random rng = new Random();
    private Integer[] array;
    public static void main(String[] args) {
        // Instantiate the timing experiment.
        OldArrayMaximumTimingExperiment timingExperiment = new OldArrayMaximumTimingExperiment();

        // Run the experiment and print the results.
        timingExperiment.printResults();
    }

    /**
     * Runs the timing experiment and prints the results.
     */
    public void printResults() {
        int arraySize = 200000;

        // Number of times to repeat the same experiment
        int experimentIterationCount = 10;

        // Compute the time elapsed experimentIterationCount number of times.
        long[] elapsedTimes = new long[experimentIterationCount];
        for (int i = 0; i < experimentIterationCount; i++) {
            elapsedTimes[i] = this.computeElapsedTime(arraySize);
        }

        // Print all the elapsed times.
        System.out.println("Elapsed times: " + Arrays.toString(elapsedTimes));

        // Compute and print the average elapsed time.
        double averageElapsedTime = 0;
        for (long elapsedTime : elapsedTimes) {
            averageElapsedTime += elapsedTime;
        }
        averageElapsedTime /= experimentIterationCount;
        System.out.println("average elapsed time: " + averageElapsedTime);

        // Sort the elapsed times.
        Arrays.sort(elapsedTimes);

        // Print out the min, max, and median elapsed times.
        System.out.println("minimum elapsed time: " + elapsedTimes[0]);
        System.out.println("median  elapsed time: " + elapsedTimes[experimentIterationCount / 2]);
        System.out.println("maximum elapsed time: " + elapsedTimes[experimentIterationCount - 1]);


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