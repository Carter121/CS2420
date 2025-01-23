package lab02;

import timing.TimingExperiment;

import java.util.Random;

/**
 * This class defines a timing experiment to measure the running time for
 * finding the maximum integer in arrays of various sizes.
 *
 * @author CS 2420 course staff
 * @version January 17, 2025
 */
public class ArrayMaximumTimingExperimentV2 extends TimingExperiment {

    private static final String problemSizeDescription = "arraySize";
    private static final int problemSizeMin = 200000;
    private static final int problemSizeCount = 30;
    private static final int problemSizeStep = 10000;
    private static final int experimentIterationCount = 50;
    private final static Random rng = new Random();
    private Integer[] array;

    public ArrayMaximumTimingExperimentV2() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }
    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ArrayMaximumTimingExperimentV2();
        timingExperiment.printResults();
    }
    /**
     * Populates the array with random integers.
     *
     * @param problemSize - the size of the array
     */
    @Override
    protected void setupExperiment(int problemSize) {
        this.array = new Integer[problemSize];
        for (int i = 0; i < problemSize; i++) {
            this.array[i] = rng.nextInt();
        }
    }

    /**
     * Runs the computeMaximum method for the array.
     */
    @Override
    protected void runComputation() {
        ArrayUtility.computeMaximum(this.array);
    }

}