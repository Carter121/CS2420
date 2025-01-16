package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for Facility.
 *
 * @author CS 2420 course staff, Carter Carling, and Nathan Montoya
 * @version 01/16/2025
 */
public class FacilityTester {

    private Facility emptyFacility, verySmallFacility, smallFacility;
    private UHealthID uHID1, uHID2, uHID3;
    private GregorianCalendar date1, date2, date3;

    @BeforeEach
    public void setUp() throws Exception {

        this.uHID1 = new UHealthID("AAAA-1111");
        this.uHID2 = new UHealthID("BCBC-2323");
        this.uHID3 = new UHealthID("HRHR-7654");

        this.date1 = new GregorianCalendar(2023, 0, 1);
        this.date2 = new GregorianCalendar(2023, 3, 17);
        this.date3 = new GregorianCalendar(2022, 8, 21);

        this.emptyFacility = new Facility();

        this.verySmallFacility = new Facility();
        this.verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", this.uHID1, 1010101, this.date1));
        this.verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", this.uHID2, 3232323, this.date2));
        this.verySmallFacility.addPatient(new CurrentPatient("Riley", "Nguyen", this.uHID3, 9879876, this.date3));

        this.smallFacility = new Facility();
        String absolutePath = "/home/carter/Dev/CS2420/src/main/java/com/cartercarling/cs2420/assignments/assign02/src/main/java/assign02/small_patient_list.txt";
        String defaultPath = "src/assign02/small_patient_list.txt";
        this.smallFacility.addAll(this.readFromFile(absolutePath));

        // Extend this tester to add more tests for the facilities above,
        // as well as to create and test larger facilities.
        // (HINT: For a larger facility, use the helpers at the end of this file to
        //        generate names, IDs, and dates.)
    }

    // Empty Facility tests --------------------------------------------------------

    @Test
    public void testEmptyLookupUHID() {
        assertNull(this.emptyFacility.lookupByUHID(this.uHID1));
    }

    @Test
    public void testEmptyLookupPhysician() {
        ArrayList<CurrentPatient> patients = this.emptyFacility.lookupByPhysician(1010101);
        assertEquals(0, patients.size());
    }

    @Test
    public void testEmptySetVisit() {
        // ensure no exceptions thrown
        this.emptyFacility.setLastVisit(this.uHID2, this.date3);
    }

    @Test
    public void testEmptySetPhysician() {
        // ensure no exceptions thrown
        this.emptyFacility.setPhysician(this.uHID2, 1010101);
    }

    @Test
    public void testEmptyGetRecentPatients() {
        ArrayList<CurrentPatient> patients = this.emptyFacility.getRecentPatients(this.date3);
        assertEquals(0, patients.size());
    }

    // Very small facility tests ---------------------------------------------------

    @Test
    public void testVerySmallLookupUHID() {
        Patient expected = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
        CurrentPatient actual = this.verySmallFacility.lookupByUHID(new UHealthID("BCBC-2323"));
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testVerySmallLookupPhysicianCount() {
        ArrayList<CurrentPatient> actualPatients = this.verySmallFacility.lookupByPhysician(9879876);
        assertEquals(1, actualPatients.size());
    }

    @Test
    public void testVerySmallLookupPhysicianPatient() {
        Patient expectedPatient = new Patient("Riley", "Nguyen", new UHealthID("HRHR-7654"));
        ArrayList<CurrentPatient> actualPatients = this.verySmallFacility.lookupByPhysician(9879876);
        assertEquals(expectedPatient.toString(), actualPatients.get(0)
                                                               .toString());
    }

    @Test
    public void testVerySmallAddNewPatient() {
        assertTrue(this.verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", new UHealthID("BBBB-2222"), 1010101, this.date1)));
    }

    @Test
    public void testVerySmallUpdatePhysician() {
        this.verySmallFacility.lookupByUHID(this.uHID1)
                              .updatePhysician(9090909);
        CurrentPatient patient = this.verySmallFacility.lookupByUHID(this.uHID1);
        assertEquals(9090909, patient.getPhysician());
    }

    // Small facility tests -------------------------------------------------------------------------

    @Test
    public void testSmallGetRecentPatients() {
        ArrayList<CurrentPatient> actual = this.smallFacility.getRecentPatients(new GregorianCalendar(2020, 0, 0));
        assertEquals(2, actual.size());
    }

    //TODO:
    // Add more tests for small facility


    // Helper methods ------------------------------------------------------------

    /**
     * Generates unique UHealthIDs (valid for up to 260,000 IDs).
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
     * Generates dates.
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
     * Generate names.
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

    /**
     * Adds the patients specified by the input file to a list.
     *
     * Assumes a strict file format:
     * (each line) FirstName LastName ABCD-0123 u0123456 2023 05 16
     *
     * Also assumes there are no duplicate patients in the file.
     *
     * @param filename - full or relative path to file containing patient data
     */
    public ArrayList<CurrentPatient> readFromFile(String filename) {
        ArrayList<CurrentPatient> patients = new ArrayList<CurrentPatient>();
        try {
            Scanner fileIn = new Scanner(new File(filename));
            int lineNumber = 0;

            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                lineNumber++;
                patients.add(this.parsePatient(line, lineNumber));
            }
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + "  Patient file couldn't be opened.");
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
                    + ". Not all patients added to list.");
        }
        return patients;
    }

    /**
     * Parses the information about a patient from file.
     *
     * @param line - string to be parsed
     * @param lineNumber - line number in file, used for error reporting (see above)
     * @return the Patient constructed from the information
     * @throws ParseException if file containing line is not properly formatted (see above)
     */
    private CurrentPatient parsePatient(String line, int lineNumber) throws ParseException {
        Scanner lineIn = new Scanner(line);
        lineIn.useDelimiter(" ");

        if (!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("First name", lineNumber);
        }
        String firstName = lineIn.next();

        if (!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("Last name", lineNumber);
        }
        String lastName = lineIn.next();

        if (!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("UHealth ID", lineNumber);
        }
        String patientIDString = lineIn.next();

        if (!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("physician", lineNumber);
        }
        String physicianString = lineIn.next();
        int physician = Integer.parseInt(physicianString.substring(1, 8));

        if (!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("year of last visit", lineNumber);
        }
        String yearString = lineIn.next();
        int year = Integer.parseInt(yearString);

        if (!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("month of last visit", lineNumber);
        }
        String monthString = lineIn.next();
        int month = Integer.parseInt(monthString);

        if (!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("day of last visit", lineNumber);
        }
        String dayString = lineIn.next();
        int day = Integer.parseInt(dayString);

        GregorianCalendar lastVisit = new GregorianCalendar(year, month, day);

        lineIn.close();

        return new CurrentPatient(firstName, lastName, new UHealthID(patientIDString),
                physician, lastVisit);
    }

}