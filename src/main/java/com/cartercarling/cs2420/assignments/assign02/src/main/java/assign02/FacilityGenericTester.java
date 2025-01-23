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
@SuppressWarnings("MagicConstant")
public class FacilityGenericTester {

    private FacilityGeneric<Integer> uIDFacility, emptyFacility, phase3Facility;
    private FacilityGeneric<UHealthID> uHIDFacility;
    private FacilityGeneric<String> nameFacility;

    private UHealthID[] uHIDs, uHIDsBig;
    private GregorianCalendar[] dates, datesBig;
    private String[] uIDFirstNames, uIDLastNames, uIDPhysicianNames, bigFirstNames, bigLastNames, bigPhysicianNames;
    // For phase 3
    private UHealthID p3id1, p3id2, p3id3, p3id4;
    private GregorianCalendar p3date1, p3date2, p3date3, p3date4;

    // For BigFacility
    private FacilityGeneric<Integer> uIDBigFacility, emptyBigFacility, bigFacility;
    private FacilityGeneric<UHealthID> uHIDBigFacility;
    private FacilityGeneric<String> nameBigFacility;

    @BeforeEach
    public void setUp() throws Exception {

        //***********************************************************************
        //* Original Test Stuff
        //***********************************************************************

        // Modifying these numbers will affect the provided tests
        int nPatients = 20;
        int nPhysicians = 8;

        this.uHIDs = this.generateUHIDs(nPatients + nPhysicians);
        this.dates = this.generateDates(nPatients);
        this.uIDFirstNames = this.generateNames(nPatients);
        this.uIDLastNames = this.generateNames(nPatients);
        this.uIDPhysicianNames = this.generateNames(nPhysicians);

        this.uIDFacility = new FacilityGeneric<Integer>();
        this.uHIDFacility = new FacilityGeneric<UHealthID>();
        this.nameFacility = new FacilityGeneric<String>();
        this.emptyFacility = new FacilityGeneric<Integer>();
        this.phase3Facility = new FacilityGeneric<Integer>();

        for (int i = 0; i < nPatients; i++) {
            this.uIDFacility.addPatient(new CurrentPatientGeneric<Integer>(
                    this.uIDFirstNames[i], this.uIDLastNames[i],
                    this.uHIDs[i], 1234567 + i % nPhysicians, this.dates[i]));
            this.uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(
                    this.uIDFirstNames[i], this.uIDLastNames[i],
                    this.uHIDs[i], this.uHIDs[nPatients + i % nPhysicians], this.dates[i]));
            this.nameFacility.addPatient(new CurrentPatientGeneric<String>(
                    this.uIDFirstNames[i], this.uIDLastNames[i],
                    this.uHIDs[i], this.uIDPhysicianNames[i % nPhysicians], this.dates[i]));
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

        //***********************************************************************
        //* Big Facility
        //***********************************************************************


        //* Create the facilities
        this.uIDBigFacility = new FacilityGeneric<Integer>();
        this.uHIDBigFacility = new FacilityGeneric<UHealthID>();
        this.nameBigFacility = new FacilityGeneric<String>();
        this.emptyBigFacility = new FacilityGeneric<Integer>();
        this.bigFacility = new FacilityGeneric<Integer>();

        //* Create the patient data
        nPatients = 100;
        nPhysicians = 100;
        this.uHIDsBig = this.generateUHIDs(nPatients + nPhysicians);
        this.datesBig = this.generateDates(nPatients);
        this.bigFirstNames = this.generateNames(nPatients);
        this.bigLastNames = this.generateNames(nPatients);
        this.bigPhysicianNames = this.generateNames(nPhysicians);

        //* Add the patients to the facilities
        for (int i = 0; i < nPatients; i++) {
            this.uIDBigFacility.addPatient(new CurrentPatientGeneric<Integer>(
                    this.bigFirstNames[i], this.bigLastNames[i],
                    this.uHIDsBig[i], 1234567 + i, this.datesBig[i]));

            this.uHIDBigFacility.addPatient(new CurrentPatientGeneric<UHealthID>(
                    this.bigFirstNames[i], this.bigLastNames[i],
                    this.uHIDsBig[i], this.uHIDsBig[nPatients + i], this.datesBig[i]));

            this.nameBigFacility.addPatient(new CurrentPatientGeneric<String>(
                    this.bigFirstNames[i], this.bigLastNames[i],
                    this.uHIDsBig[i], this.bigPhysicianNames[i], this.datesBig[i]));
        }

        this.bigFacility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("XXXX-1111"), 7, new GregorianCalendar(2019, 1, 5)));
        this.bigFacility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("BBBB-1111"), 7, new GregorianCalendar(2019, 1, 4)));
        this.bigFacility.addPatient(new CurrentPatientGeneric<Integer>("A", "C", new UHealthID("FFFF-1111"), 7, new GregorianCalendar(2019, 1, 3)));
        this.bigFacility.addPatient(new CurrentPatientGeneric<Integer>("R", "T", new UHealthID("BBBB-2222"), 7, new GregorianCalendar(2019, 1, 2)));
        this.bigFacility.addPatient(new CurrentPatientGeneric<Integer>("K", "C", new UHealthID("CCCC-1234"), 7, new GregorianCalendar(2019, 1, 1)));
        this.bigFacility.addPatient(new CurrentPatientGeneric<Integer>("C", "C", new UHealthID("DDDD-5678"), 7, new GregorianCalendar(2018, 11, 31)));
        this.bigFacility.addPatient(new CurrentPatientGeneric<Integer>("J", "K", new UHealthID("EEEE-1011"), 7, new GregorianCalendar(2018, 11, 30)));

    }

    //***********************************************************************
    //* empty Facility tests
    //***********************************************************************

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

    //***********************************************************************
    //* uID Facility tests
    //***********************************************************************

    @Test
    public void testUIDLookupPhysicianCount() {
        ArrayList<CurrentPatientGeneric<Integer>> actualPatients = this.uIDFacility.lookupByPhysician(1234568);
        assertEquals(3, actualPatients.size());
    }

    @Test
    public void testUIDLookupPhysicianPatient() {
        int expectedPhysician = 1234568;
        CurrentPatientGeneric<Integer> expectedPatient = new CurrentPatientGeneric<>(this.uIDFirstNames[1], this.uIDLastNames[1], new UHealthID(this.uHIDs[1].toString()), expectedPhysician, this.dates[1]);
        ArrayList<CurrentPatientGeneric<Integer>> actualPatients = this.uIDFacility.lookupByPhysician(expectedPhysician);
        assertEquals(expectedPatient.toString(), actualPatients.get(0)
                                                               .toString());
    }

    @Test
    public void testUIDLookupPhysicianCountBiggerArray() {
        ArrayList<CurrentPatientGeneric<Integer>> actualPatients = this.uIDFacility.lookupByPhysician(12345678);
        assertEquals(0, 0);
    }

    //***********************************************************************
    //* UHealthID facility tests
    //***********************************************************************

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

    //***********************************************************************
    //* name facility tests
    //***********************************************************************

    @Test
    public void testNameLookupPhysician() {
        Patient expectedPatient1 = new Patient(this.uIDFirstNames[1], this.uIDLastNames[1], new UHealthID(this.uHIDs[1].toString()));
        Patient expectedPatient2 = new Patient(this.uIDFirstNames[9], this.uIDLastNames[9], new UHealthID(this.uHIDs[9].toString()));

        ArrayList<CurrentPatientGeneric<String>> actualPatients = this.nameFacility.lookupByPhysician(this.uIDPhysicianNames[1]);

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

    //***********************************************************************
    //***********************************************************************
    //***********************************************************************
    //* Big Facility tests
    //***********************************************************************
    //***********************************************************************
    //***********************************************************************

    //* Test the uId big facility
    @Test
    public void testUIDBigPhysicianCount() {
        ArrayList<Integer> physicianList = this.uIDBigFacility.getPhysicianList();
        assertEquals(100, physicianList.size());
    }

    @Test
    public void testUIDBigLookupByUHID() {
        CurrentPatientGeneric<Integer> expected = new CurrentPatientGeneric<>(this.bigFirstNames[1], this.bigLastNames[1], new UHealthID(this.uHIDsBig[1].toString()), 1234567, this.datesBig[1]);
        CurrentPatientGeneric<Integer> actual = this.uIDBigFacility.lookupByUHID(this.uHIDsBig[1]);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testUIDBigLookupByPhysician() {
        CurrentPatientGeneric<Integer> expected = new CurrentPatientGeneric<>(this.bigFirstNames[0], this.bigLastNames[0], new UHealthID(this.uHIDsBig[0].toString()), 1234567, this.datesBig[0]);
        ArrayList<CurrentPatientGeneric<Integer>> actualPatients = this.uIDBigFacility.lookupByPhysician(1234567);
        assertEquals(expected.toString(), actualPatients.getFirst()
                                                        .toString());
    }

    @Test
    public void testUIDBigSetVisit() {
        this.uIDBigFacility.lookupByUHID(this.uHIDsBig[0])
                           .updateLastVisit(this.datesBig[1]);

        CurrentPatientGeneric<Integer> actual = this.uIDBigFacility.lookupByUHID(this.uHIDsBig[1]);

        assertEquals(this.datesBig[1].toString(), actual.getLastVisit()
                                                        .toString());
    }

    @Test
    public void testUIDBigSetPhysician() {
        this.uIDBigFacility.lookupByUHID(this.uHIDsBig[0])
                           .updatePhysician(1234567);
        CurrentPatientGeneric<Integer> actual = this.uIDBigFacility.lookupByUHID(this.uHIDsBig[0]);
        assertEquals(1234567, actual.getPhysician());
    }

    @Test
    public void testUIDBigGetRecentPatients() {
        ArrayList<CurrentPatientGeneric<Integer>> actual = this.uIDBigFacility.getRecentPatients(this.datesBig[4]);
        assertEquals(77, actual.size());
    }

    //* Test the uHealthID big facility
    @Test
    public void testUHIDBigPhysicianCount() {
        ArrayList<UHealthID> physicianList = this.uHIDBigFacility.getPhysicianList();
        assertEquals(100, physicianList.size());
    }

    @Test
    public void testUHIDBigLookupByUHID() {
        CurrentPatientGeneric<UHealthID> expected = new CurrentPatientGeneric<>(this.bigFirstNames[0], this.bigLastNames[0], new UHealthID(this.uHIDsBig[0].toString()), this.uHIDsBig[0], this.datesBig[0]);
        CurrentPatientGeneric<UHealthID> actual = this.uHIDBigFacility.lookupByUHID(this.uHIDsBig[0]);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testUHIDBigLookupByPhysician() {
        CurrentPatientGeneric<UHealthID> expected = new CurrentPatientGeneric<>(this.bigFirstNames[0], this.bigLastNames[0], new UHealthID(this.uHIDsBig[0].toString()), this.uHIDsBig[0], this.datesBig[0]);
        ArrayList<CurrentPatientGeneric<UHealthID>> actualPatients = this.uHIDBigFacility.lookupByPhysician(this.uHIDsBig[100]);
        assertEquals(expected.toString(), actualPatients.getFirst()
                                                        .toString());
    }

    @Test
    public void testUHIDBigSetVisit() {
        this.uHIDBigFacility.lookupByUHID(this.uHIDsBig[0])
                            .updateLastVisit(this.datesBig[1]);
        CurrentPatientGeneric<UHealthID> actual = this.uHIDBigFacility.lookupByUHID(this.uHIDsBig[0]);
        assertEquals(this.datesBig[1].toString(), actual.getLastVisit()
                                                        .toString());
    }

    @Test
    public void testUHIDBigSetPhysician() {
        this.uHIDBigFacility.lookupByUHID(this.uHIDsBig[0])
                            .updatePhysician(this.uHIDsBig[0]);
        CurrentPatientGeneric<UHealthID> actual = this.uHIDBigFacility.lookupByUHID(this.uHIDsBig[0]);
        assertEquals(this.uHIDsBig[0], actual.getPhysician());
    }

    @Test
    public void testUHIDBigGetRecentPatients() {
        ArrayList<CurrentPatientGeneric<UHealthID>> actual = this.uHIDBigFacility.getRecentPatients(this.datesBig[4]);
        assertEquals(77, actual.size());
    }

    //* test the name big facility
    @Test
    public void testNameBigPhysicianCount() {
        ArrayList<String> physicianList = this.nameBigFacility.getPhysicianList();
        assertEquals(100, physicianList.size());
    }

    @Test
    public void testNameBigLookupByUHID() {
        CurrentPatientGeneric<String> expected = new CurrentPatientGeneric<>(this.bigFirstNames[0], this.bigLastNames[0], new UHealthID(this.uHIDsBig[0].toString()), this.bigPhysicianNames[0], this.datesBig[0]);
        CurrentPatientGeneric<String> actual = this.nameBigFacility.lookupByUHID(this.uHIDsBig[0]);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testNameBigLookupByPhysician() {
        CurrentPatientGeneric<String> expected = new CurrentPatientGeneric<>(this.bigFirstNames[0], this.bigLastNames[0], new UHealthID(this.uHIDsBig[0].toString()), this.bigPhysicianNames[0], this.datesBig[0]);
        ArrayList<CurrentPatientGeneric<String>> actualPatients = this.nameBigFacility.lookupByPhysician(this.bigPhysicianNames[0]);
        assertEquals(expected.toString(), actualPatients.getFirst()
                                                        .toString());
    }

    @Test
    public void testNameBigSetVisit() {
        this.nameBigFacility.lookupByUHID(this.uHIDsBig[0])
                            .updateLastVisit(this.datesBig[1]);
        CurrentPatientGeneric<String> actual = this.nameBigFacility.lookupByUHID(this.uHIDsBig[0]);
        assertEquals(this.datesBig[1].toString(), actual.getLastVisit()
                                                        .toString());
    }

    @Test
    public void testNameBigSetPhysician() {
        this.nameBigFacility.lookupByUHID(this.uHIDsBig[0])
                            .updatePhysician(this.bigPhysicianNames[0]);
        CurrentPatientGeneric<String> actual = this.nameBigFacility.lookupByUHID(this.uHIDsBig[0]);
        assertEquals(this.bigPhysicianNames[0], actual.getPhysician());
    }

    @Test
    public void testNameBigGetRecentPatients() {
        ArrayList<CurrentPatientGeneric<String>> actual = this.nameBigFacility.getRecentPatients(this.datesBig[4]);
        assertEquals(77, actual.size());
    }

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
