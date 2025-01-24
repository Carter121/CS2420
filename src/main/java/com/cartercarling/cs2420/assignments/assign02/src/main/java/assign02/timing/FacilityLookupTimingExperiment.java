package assign02.timing;

import assign02.CurrentPatient;
import assign02.Facility;
import assign02.UHealthID;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
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
    private static final int problemSizeMin = 500;  // TODO: initialize appropriately (do not use 0)
    private static final int problemSizeCount = 100;  // TODO: initialize appropriately (do not use 0)
    private static final int problemSizeStep = 500;  // TODO: initialize appropriately (do not use 0)
    private static final int experimentIterationCount = 50;

    private Facility randomFacility;
    private UHealthID randomUHealthID;
    private final static Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new FacilityLookupTimingExperiment();

        System.out.println("\n---Computing timing results---\n");
        timingExperiment.printResults();
    }

    public FacilityLookupTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Fills the Facility with the given number of patients.
     *
     * @param problemSize - the number of patients to add
     */
    @Override
    protected void setupExperiment(int problemSize) {
        this.randomFacility = new Facility();
        ArrayList<UHealthID> ids = this.generateUHIDs(problemSize);
        for (UHealthID id : ids) {
            this.randomFacility.addPatient(new CurrentPatient("name", "name", id, 1234567, new GregorianCalendar(2025, 1, 1)));
        }
        this.randomUHealthID = this.generateUHIDs(1).get(0);
    }

    /**
     * Runs the lookupByUHID method for the facility.
     */
    @Override
    protected void runComputation() {
        this.randomFacility.lookupByUHID(this.randomUHealthID);
    }

    /**
     * Generate unique UHealthIDs.
     *
     * @param howMany - IDs to make
     * @return a list of UHealthIDs
     */
    private ArrayList<UHealthID> generateUHIDs(int howMany) {
        ArrayList<UHealthID> ids = new ArrayList<UHealthID>(howMany);
        HashSet<UHealthID> idSet = new HashSet<UHealthID>(howMany);
        char[] prefix = new char[4];
        while (idSet.size() < howMany) {
            prefix[0] = (char) ('A' + rng.nextInt(26));
            prefix[1] = (char) ('A' + rng.nextInt(26));
            prefix[2] = (char) ('A' + rng.nextInt(26));
            prefix[3] = (char) ('A' + rng.nextInt(26));
            idSet.add(new UHealthID(new String(prefix) + "-" + String.format("%04d", rng.nextInt(10000))));
        }
        for (UHealthID id : idSet) {
            ids.add(id);
        }
        return ids;
    }

}