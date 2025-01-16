package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a UHealth patient who has a unique UHealthID,
 * a first name, a last name, a physician, and a last visit date.
 *
 * @author Carter Carling, and Nathan Montoya
 * @version 01/16/2025
 */
public class CurrentPatient extends Patient {

    private int physician;
    private GregorianCalendar lastVisit;

    /**
     * Creates a patient with a given name and ID.
     *
     * @param firstName - of the patient
     * @param lastName  - of the patient
     * @param uHealthID - of the patient
     */
    public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, int physician, GregorianCalendar lastVisit) {
        super(firstName, lastName, uHealthID);
        this.physician = physician;
        this.lastVisit = lastVisit;
    }

    /**
     * Gets the patient's physician's id
     * @return The physician id
     */
    public int getPhysician() {
        return this.physician;
    }

    /**
     * Gets the patient's last visit date
     * @return the Gregorian Calendar date of the last visit
     */
    public GregorianCalendar getLastVisit() {
        return this.lastVisit;
    }

    /**
     * Update the physician id
     * @param newPhysician The new physician id
     */
    public void updatePhysician(int newPhysician) {
        this.physician = newPhysician;
    }

    /**
     * Update the last visit date
     * @param date The updated last visit date
     */
    public void updateLastVisit(GregorianCalendar date) {
        this.lastVisit = date;
    }

}
