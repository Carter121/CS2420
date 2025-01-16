package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for FacilityGeneric.
 *
 * @author CS 2420 course staff and, Carter Carling, and Nathan Montoya
 * @version 01/16/2024
 */
public class FacilityGenericTester {

    private FacilityGeneric<Integer> uIDFacility, emptyFacility, phase3Facility;
    private FacilityGeneric<UHealthID> uHIDFacility;
    private FacilityGeneric<String> nameFacility;

    private UHealthID[] uHIDs;
    private GregorianCalendar[] dates;
    private String[] firstNames, lastNames, physicianNames;
    // For phase 3
    private UHealthID p3id1, p3id2, p3id3, p3id4;
    private GregorianCalendar p3date1, p3date2, p3date3, p3date4;

    @BeforeEach
    public void setUp() throws Exception {
        // Modifying these numbers will affect the provided tests
        int nPatients = 20;
        int nPhysicians = 8;

        this.uHIDs = this.generateUHIDs(nPatients + nPhysicians);
        this.dates = this.generateDates(nPatients);
        this.firstNames = this.generateNames(nPatients);
        this.lastNames = this.generateNames(nPatients);
        this.physicianNames = this.generateNames(nPhysicians);

        this.uIDFacility = new FacilityGeneric<Integer>();
        this.uHIDFacility = new FacilityGeneric<UHealthID>();
        this.nameFacility = new FacilityGeneric<String>();
        this.emptyFacility = new FacilityGeneric<Integer>();
        this.phase3Facility = new FacilityGeneric<Integer>();

        for (int i = 0; i < nPatients; i++) {
            this.uIDFacility.addPatient(new CurrentPatientGeneric<Integer>(
                    this.firstNames[i], this.lastNames[i],
                    this.uHIDs[i], 1234567 + i % nPhysicians, this.dates[i]));
            this.uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(
                    this.firstNames[i], this.lastNames[i],
                    this.uHIDs[i], this.uHIDs[nPatients + i % nPhysicians], this.dates[i]));
            this.nameFacility.addPatient(new CurrentPatientGeneric<String>(
                    this.firstNames[i], this.lastNames[i],
                    this.uHIDs[i], this.physicianNames[i % nPhysicians], this.dates[i]));
        }

        this.p3id1 = new UHealthID("XXXX-1111");
        this.p3id2 = new UHealthID("BBBB-1111");
        this.p3id3 = new UHealthID("FFFF-1111");
        this.p3id4 = new UHealthID("BBBB-2222");
        this.p3date1 = new GregorianCalendar(2019, 1, 5);
        this.p3date2 = new GregorianCalendar(2019, 1, 4);
        this.p3date3 = new GregorianCalendar(2019, 1, 3);
        this.p3date4 = new GregorianCalendar(2019, 1, 2);

        this.phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("XXXX-1111"), 7, new GregorianCalendar(2019, 1, 5)));
        this.phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("BBBB-1111"), 7, new GregorianCalendar(2019, 1, 4)));
        this.phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "C", new UHealthID("FFFF-1111"), 7, new GregorianCalendar(2019, 1, 3)));
        this.phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("R", "T", new UHealthID("BBBB-2222"), 7, new GregorianCalendar(2019, 1, 2)));

        // Extend this tester to add more tests for the facilities above,
        // as well as to create and test other facilities.
        // (HINT: For a larger facility, use the helpers at the end of this file to
        //        generate names, IDs, and dates.)
    }

    // empty Facility tests --------------------------------------------------------

    @Test
    public void testEmptyLookupUHID() {
        assertNull(this.emptyFacility.lookupByUHID(this.uHIDs[0]));
    }

    @Test
    public void testEmptyLookupPhysician() {
        ArrayList<CurrentPatientGeneric<Integer>> patients = this.emptyFacility.lookupByPhysician(1010101);
        assertEquals(0, patients.size());
    }

    @Test
    public void testEmptySetVisit() {
        // ensure no exceptions thrown
        this.emptyFacility.setLastVisit(this.uHIDs[0], this.dates[3]);
    }

    @Test
    public void testEmptySetPhysician() {
        // ensure no exceptions thrown
        this.emptyFacility.setPhysician(this.uHIDs[0], 1010101);
    }

    @Test
    public void testEmptyGetRecentPatients() {
        ArrayList<CurrentPatientGeneric<Integer>> patients = this.emptyFacility.getRecentPatients(this.dates[4]);
        assertEquals(0, patients.size());
    }

    // uID Facility tests --------------------------------------------------------

    @Test
    public void testUIDLookupPhysicianCount() {
        ArrayList<CurrentPatientGeneric<Integer>> actualPatients = this.uIDFacility.lookupByPhysician(1234568);
        assertEquals(3, actualPatients.size());
    }

    @Test
    public void testUIDLookupPhysicianPatient() {
        Patient expectedPatient = new Patient(this.firstNames[1], this.lastNames[1], new UHealthID(this.uHIDs[1].toString()));
        ArrayList<CurrentPatientGeneric<Integer>> actualPatients = this.uIDFacility.lookupByPhysician(1234568);
        assertEquals(expectedPatient.toString(), actualPatients.get(0)
                                                               .toString());
    }

    // Add more tests

    // UHealthID facility tests ---------------------------------------------------

    @Test
    public void testUHIDLookupPhysicianCount() {
        ArrayList<CurrentPatientGeneric<UHealthID>> actualPatients = this.uHIDFacility.lookupByPhysician(this.uHIDs[this.uHIDs.length - 1]);
        assertEquals(2, actualPatients.size());
    }
    @Test
    public void testUHIDUpdatePhysician() {
        this.uHIDFacility.lookupByUHID(this.uHIDs[2])
                         .updatePhysician(this.uHIDs[this.uHIDs.length - 1]);
        CurrentPatientGeneric<UHealthID> patient = this.uHIDFacility.lookupByUHID(this.uHIDs[2]);
        assertEquals(this.uHIDs[this.uHIDs.length - 1], patient.getPhysician());
    }

    // Add more tests

    // name facility tests -------------------------------------------------------------------------

    @Test
    public void testNameLookupPhysician() {
        Patient expectedPatient1 = new Patient(this.firstNames[1], this.lastNames[1], new UHealthID(this.uHIDs[1].toString()));
        Patient expectedPatient2 = new Patient(this.firstNames[9], this.lastNames[9], new UHealthID(this.uHIDs[9].toString()));

        ArrayList<CurrentPatientGeneric<String>> actualPatients = this.nameFacility.lookupByPhysician(this.physicianNames[1]);

        boolean containsPt1 = false;
        boolean containsPt2 = false;

        for (Patient pt : actualPatients) {
            if (pt.toString()
                  .equals(expectedPatient1.toString()))
                containsPt1 = true;
            if (pt.toString()
                  .equals(expectedPatient2.toString()))
                containsPt2 = true;
        }

        assertTrue(containsPt1 && containsPt2);
    }

    @Test
    public void testNameGetPhysicianList() {
        ArrayList<String> actual = this.nameFacility.getPhysicianList();
        assertEquals(8, actual.size());
    }

    // TODO:
    // Add more tests

    // phase 3 tests ---------------------------------------------------------------------------
    // Uncomment these when you get to phase 3

    @Test
    public void testOrderedByUHIDCount() {
        ArrayList<CurrentPatientGeneric<Integer>> actual = this.phase3Facility.getOrderedPatients(new OrderByUHealthID<Integer>());
        assertEquals(4, actual.size());
    }

    @Test
    public void testOrderedByUHIDOrder() {
        ArrayList<CurrentPatientGeneric<Integer>> actual = this.phase3Facility.getOrderedPatients(new OrderByUHealthID<Integer>());
        assertEquals(new CurrentPatientGeneric<Integer>("A", "B", this.p3id1, 7, this.p3date1).toString(), actual.get(3)
                                                                                                                 .toString());
        assertEquals(new CurrentPatientGeneric<Integer>("A", "B", this.p3id2, 7, this.p3date2).toString(), actual.get(0)
                                                                                                                 .toString());
        assertEquals(new CurrentPatientGeneric<Integer>("A", "C", this.p3id3, 7, this.p3date3).toString(), actual.get(2)
                                                                                                                 .toString());
        assertEquals(new CurrentPatientGeneric<Integer>("R", "T", this.p3id4, 7, this.p3date4).toString(), actual.get(1)
                                                                                                                 .toString());
    }

    // Add more tests

    // Private helper methods ---------------------------------------------------------------

    /**
     * A private helper to generate unique UHealthIDs.
     * Valid for up to 260,000 IDs.
     *
     * @param howMany - IDs to make
     * @return an array of UHealthIDs
     */
    private UHealthID[] generateUHIDs(int howMany) {
        UHealthID[] ids = new UHealthID[howMany];
        for (int i = 0; i < howMany; i++) {
            String prefix = "JKL" + (char) ('A' + (i / 10000) % 26);
            ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i % 10000));
        }
        return ids;
    }

    /**
     * A private helper to generate dates.
     *
     * @param howMany - dates to generate
     * @return an array of dates
     */
    private GregorianCalendar[] generateDates(int howMany) {
        GregorianCalendar[] dates = new GregorianCalendar[howMany];
        for (int i = 0; i < howMany; i++) {
            dates[i] = new GregorianCalendar(2000 + i % 22, i % 12, i % 28);
        }
        return dates;
    }

    /**
     * A private helper to generate names.
     *
     * @param howMany - names to generate
     * @return an array of names
     */
    private String[] generateNames(int howMany) {
        String[] names = new String[howMany];
        Random rng = new Random();
        for (int i = 0; i < howMany; i++) {
            names[i] = "" + (char) ('A' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26))
                    + (char) ('a' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26));
        }
        return names;
    }

}