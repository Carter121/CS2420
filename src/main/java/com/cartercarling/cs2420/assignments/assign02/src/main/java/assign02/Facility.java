package assign02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a record of patients that have visited a UHealth
 * facility. It maintains a collection of CurrentPatients.
 *
 * @author CS 2420 course staff, Carter Carling, and Nathan Montoya
 * @version 01/16/2025
 */
public class Facility {

    private final ArrayList<CurrentPatient> patientList;

    /**
     * Creates an empty facility record.
     */
    public Facility() {
        this.patientList = new ArrayList<>();
    }

    /**
     * Adds the given patient to the list of patients, avoiding duplicates.
     *
     * @param patient - patient to be added to this record
     *
     * @return true if the patient was added,
     * false if the patient was not added because they already exist in the record
     */
    public boolean addPatient(CurrentPatient patient) {
        if (this.patientList.contains(patient))
            return false;

        this.patientList.add(patient);
        return true;
    }

    /**
     * Adds all patients from the given list to the list of patients.
     *
     * @param patients - list of patients to be added to this record
     */
    public void addAll(ArrayList<CurrentPatient> patients) {
        this.patientList.addAll(patients);
    }

    /**
     * Retrieves the patient with the given UHealthID.
     *
     * @param patientID - ID of patient to be retrieved
     *
     * @return the patient with the given ID, or null if no such patient
     * exists in the record
     */
    public CurrentPatient lookupByUHID(UHealthID patientID) {
        for (CurrentPatient pt : this.patientList) {
            if (pt.getUHealthID()
                  .equals(patientID))
                return pt;
        }
        return null;
    }

    /**
     * Retrieves the patient(s) with the given physician.
     *
     * @param physician - physician of patient(s) to be retrieved
     *
     * @return a list of patient(s) with the given physician (in any order),
     * or an empty list if no such patients exist in the record
     */
    public ArrayList<CurrentPatient> lookupByPhysician(int physician) {
        ArrayList<CurrentPatient> patients = new ArrayList<>();
        for (CurrentPatient pt : this.patientList) {
            if (pt.getPhysician() == physician)
                patients.add(pt);
        }

        return patients;
    }

    /**
     * Retrieves the patient(s) with last visits newer than a given date.
     * Note that GregorianCalendar includes a compareTo method that may be useful.
     *
     * NOTE: If the last visit date equals this date, do not add the patient.
     *
     * @param date - cutoff date earlier than visit date of all returned patients
     *
     * @return a list of patient(s) with last visit date after cutoff (in any order),
     * or an empty list if no such patients exist in the record
     */
    public ArrayList<CurrentPatient> getRecentPatients(GregorianCalendar date) {
        ArrayList<CurrentPatient> patients = new ArrayList<>();
        for (CurrentPatient pt : this.patientList) {
            if (pt.getLastVisit()
                  .compareTo(date) > 0)
                patients.add(pt);
        }

        return patients;
    }

    /**
     * Retrieves a list of physicians assigned to patients at this facility.
     *
     * NOTE: Do not put duplicates in the list. Make sure each physician
     * is only added once.
     *
     * @return a list of physician(s) assigned to current patients,
     * or an empty list if no patients exist in the record
     */
    public ArrayList<Integer> getPhysicianList() {
        Set<Integer> physicians = new HashSet<>();

        for (CurrentPatient pt : this.patientList) {
            int currentPhysician = pt.getPhysician();

            physicians.add(currentPhysician);
        }

        ArrayList<Integer> physicianList = new ArrayList<>(physicians);

        physicianList.addAll(physicians);

        return physicianList;
    }

    /**
     * Sets the physician of a patient with the given UHealthID.
     *
     * NOTE: If no patient with the ID exists in the collection, then this
     * method has no effect.
     *
     * @param patientID - UHealthID of patient to modify
     * @param physician - identifier of patient's new physician
     */
    public void setPhysician(UHealthID patientID, int physician) {
        for (CurrentPatient pt : this.patientList) {
            if (pt.getUHealthID() == patientID)
                pt.updatePhysician(physician);
        }
    }

    /**
     * Sets the last visit date of a patient with the given UHealthID.
     *
     * NOTE: If no patient with the ID exists in the collection, then this
     * method has no effect.
     *
     * @param patientID - UHealthID of patient to modify
     * @param date      - date of last visit
     */
    public void setLastVisit(UHealthID patientID, GregorianCalendar date) {
        for (CurrentPatient pt : this.patientList) {
            if (pt.getUHealthID() == patientID)
                pt.updateLastVisit(date);
        }
    }

}