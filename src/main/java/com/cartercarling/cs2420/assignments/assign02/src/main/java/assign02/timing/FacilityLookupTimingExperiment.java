package assign02.timing;

import assign02.CurrentPatient;
import assign02.Facility;
import assign02.UHealthID;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Experiment to measure the running time for the lookupByUHID method in 
 * facilities with various numbers of patients.
 *
 * @author CS 2420 course staff and Carter Carling
 * @version 01/23/2025
 */
public class FacilityLookupTimingExperiment extends TimingExperiment {

    private static final String problemSizeDescription = "# Pt";  // TODO: fill in string appropriately
    private static final int problemSizeMin = 250;  // TODO: initialize appropriately (do not use 0)
    private static final int problemSizeCount = 100;  // TODO: initialize appropriately (do not use 0)
    private static final int problemSizeStep = 250;  // TODO: initialize appropriately (do not use 0)
    private static final int experimentIterationCount = 50;

    private UHealthID[] uHIDs;
    private Facility facility;
    private UHealthID randomUHealthID;
    private final static Random rng = new Random();

    public static void main(String[] args) {
        long globalStartTime = System.nanoTime();

        FacilityLookupTimingExperiment timingExperiment = new FacilityLookupTimingExperiment();

        timingExperiment.prepareExperiment(problemSizeMin);

        System.out.println("\n---Computing timing results---\n");
        timingExperiment.printResults();

        long globalEndTime = System.nanoTime();
        System.out.println("Total time: " + (globalEndTime - globalStartTime) / 1_000_000_000.0 + " seconds");
    }

    public FacilityLookupTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @SuppressWarnings("SameParameterValue")
    protected void prepareExperiment(int initialSize) {
        Gson gson = new Gson();
        try {
            this.uHIDs = gson.fromJson(new JsonReader(new FileReader(GenerateID.outputDirectory + "/output.json")), UHealthID[].class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.facility = new Facility();
    }

    /**
     * Fills the Facility with the given number of patients.
     *
     * @param problemSize - the number of patients to add
     */
    @Override
    protected void setupExperiment(int problemSize, int experimentIteration) {

        if (experimentIteration == 0) {
            this.facility = new Facility();
            for (int i = 0; i < problemSize; i++) {
                this.facility.addPatient(new CurrentPatient("name", "name", this.uHIDs[i], 1234567, new GregorianCalendar(2025, 1, 1)));
            }
        }

        this.randomUHealthID = this.uHIDs[rng.nextInt(problemSize)];
    }

    /**
     * Runs the lookupByUHID method for the facility.
     */
    @Override
    protected void runComputation() {
        this.facility.lookupByUHID(this.randomUHealthID);
    }

}