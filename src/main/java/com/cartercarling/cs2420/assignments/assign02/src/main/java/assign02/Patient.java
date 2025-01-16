package assign02;

/**
 * This class represents a UHealth patient who has a unique UHealthID,
 * a first name, and a last name.
 *
 * @author CS 2420 course staff, Carter Carling, and Nathan Montoya
 * @version 01/16/2025
 */
public class Patient {

    private final String firstName;
    private final String lastName;
    private final UHealthID uHealthID;

    /**
     * Creates a patient with a given name and ID.
     *
     * @param firstName - of the patient
     * @param lastName  - of the patient
     * @param uHealthID - of the patient
     */
    public Patient(String firstName, String lastName, UHealthID uHealthID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uHealthID = uHealthID;
    }

    /**
     * Getter method for the first name of this patient object.
     *
     * @return this patient's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Getter method for the last name of this patient object.
     *
     * @return this patient's last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Getter method for the UHealthID of this patient object.
     *
     * @return this patient's UHealthID
     */
    public UHealthID getUHealthID() {
        return this.uHealthID;
    }

    /**
     * Two patients are considered equal if they have the same UHealthID.
     *
     * @param other - the object being compared with this patient
     *
     * @return true if the other object is a Patient with an equal UHealthID,
     * false otherwise.
     */
    public boolean equals(Object other) {
        if (!(other instanceof Patient pt))
            return false;

        return this.uHealthID == pt.uHealthID;
    }

    /**
     * Gets a String representing this patient.
     *
     * @return a textual representation of this patient.
     */
    public String toString() {
        return this.firstName + " " + this.lastName + " (" + this.uHealthID + ")";
    }

}