package assign02.timing;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Abstract base class for running timing experiments.
 *
 * @author CS 2420 course staff
 * @version January 17, 2025
 */
public abstract class TimingExperiment {

    // These attributes control the problem size of and number of times to iterate
    // each experiment.
    protected String problemSizeDescription;
    protected int problemSizeMin;
    protected int problemSizeCount;
    protected int problemSizeStep;
    protected int experimentIterationCount;

    private final HashMap<Integer, Long> results;

    /**
     * Constructs a general timing experiment.
     *
     * @param problemSizeDescription   - description of the problem size for the experiment
     * @param problemSizeMin           - minimum array size
     * @param problemSizeCount         - number of array sizes to use in the experiment
     * @param problemSizeStep          - step size between consecutive array sizes
     * @param experimentIterationCount - number of times to run computation for a given array size
     */
    public TimingExperiment(String problemSizeDescription, int problemSizeMin, int problemSizeCount,
                            int problemSizeStep, int experimentIterationCount) {
        this.problemSizeDescription = problemSizeDescription;
        this.problemSizeMin = problemSizeMin;
        this.problemSizeCount = problemSizeCount;
        this.problemSizeStep = problemSizeStep;
        this.experimentIterationCount = experimentIterationCount;
        this.results = new HashMap<>();
    }

    public void printResults(boolean last) {
        System.out.flush();
        System.out.println("===========================================");
        if (last)
            System.out.println("Not done yet... Please wait...");
        else
            System.out.println("!!! Complete !!!");
        System.out.println("===========================================");
        System.out.println(this.problemSizeDescription + "\ttime (ns)");
        this.results.forEach((pt, ns) -> {
            System.out.println(pt + "\t" + ns);
        });
    }

    /**
     * Runs the timing experiment and prints the results.
     */
    public void calculateResults() {
        int size = this.problemSizeMin;
        for (int i = 0; i < this.problemSizeCount; i++) {
            long medianElapsedTime = this.computeMedianElapsedTime(size);
            this.results.put(size, medianElapsedTime);
            size += this.problemSizeStep;

            if (i % 10 == 0)
                this.printResults(true);
        }
        this.printResults(true);
    }

    /**
     * Computes the median time elapsed to run the computation for a given problem
     * size.
     *
     * @param problemSize - the problem size for one experiment
     * @return the median elapsed time of the experiment iterations
     */
    protected long computeMedianElapsedTime(int problemSize) {
        long[] elapsedTimes = new long[this.experimentIterationCount];
        for (int i = 0; i < this.experimentIterationCount; i++) {
            elapsedTimes[i] = this.computeElapsedTime(problemSize);
        }
        Arrays.sort(elapsedTimes);
        return elapsedTimes[this.experimentIterationCount / 2];
    }

    /**
     * Computes the time elapsed to run the computation once for a given problem
     * size.
     *
     * @param problemSize - the problem size for one experiment
     * @return the time elapsed
     */
    protected long computeElapsedTime(int problemSize) {
        this.setupExperiment(problemSize);
        long startTime = System.nanoTime();
        this.runComputation();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * Abstract method for setting up the infrastructure for the experiment for a
     * given problem size.
     *
     * @param problemSize - the problem size for one experiment
     */
    protected abstract void setupExperiment(int problemSize);

    /**
     * Abstract method to run the computation to be timed.
     */
    protected abstract void runComputation();

}