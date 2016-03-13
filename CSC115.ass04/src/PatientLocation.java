import java.util.LinkedList;
import java.util.Random;

/**
 * CSC115 Assignment 4 : BinarySearchTree.
 * PatientLocation.java
 * Created for use by CSC115 Spring 2016
 */

/**
 * PatientLocation is a class that contains the id, name, and room number
 * of a patient in a hospital.
 * Patients are uniquely identified by their last name, first name and id number combination.
 * Note that in this version, there are only 99 possible unique id numbers, but it can
 * be increased easily by altering the code.
 */
public class PatientLocation implements Comparable<PatientLocation> {

	/* The following are used to help generate 
	 * patient id numbers.
	 */
	private static final int numDigits = 2;
	private static int range = (int)(Math.pow(10,numDigits));
	private static boolean[] used = new boolean[range];
	private static int[] fresh;
	private static int currIndex = 0;
	private static boolean alive = false;

	private String id;
	private String lastName;
	private String firstName;
	private int roomNumber;

	/**
	 * Creates a PatientLocation record.
	 * @param patientNumber The unique 2-digit number of the patient between 0 and 99.
	 *				If the number is already in use, a new one is created.
	 * @param lastName The last name of the patient.
	 * @param firstName The first name of the patient.
	 * @param roomNumber The current hospital room number of the patient.
	 */
	public PatientLocation(int patientNumber,  String lastName, String firstName, int roomNumber) {
		if (!alive) {
			createUniqueIdList();
		}
		id = setUniqueId(patientNumber);
		this.lastName = lastName;
		this.firstName = firstName;
		this.roomNumber = roomNumber;
	}

	/**
	 * Creates a PatientLocation record.
	 * The unique patient id number is automatically assigned.
	 * @param lastName The last name of the patient.
	 * @param firstName The first name of the patient.
	 * @param roomNumber The current hospital room number of the patien.
	 */
	
	public PatientLocation(String lastName, String firstName, int roomNumber) {
		if (!alive) {
			createUniqueIdList();
		}
		id = createUniqueId();
		this.lastName = lastName;
		this.firstName = firstName;
		this.roomNumber = roomNumber;
	}

	/**
	 * @return The unique id number of the patient, which has the form P<i>dd</i> where <i>d</i> is a single digit.
 	 */
	public String getId() {
		return id;
	}
	/**
	 * @return The patient's last name.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Updates the patient's last name.
	 * @param lastName The patient's new last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return The patient's first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Updates the patient's first name.
	 * @param firstName The patient's new first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return The patient's room number.
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	/**
	 * Updates the patient's room number.
	 * @param roomNumber The patient's new room number.
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * Determines equality of this patient with another patient.
	 * @param other The other patient.
	 * @return True if this patient has the same first name, last name and id
	 * as the other patient.
	 */
	public boolean equals(PatientLocation other) {
		return lastName.equals(other.lastName) && firstName.equals(other.firstName)
			&& id.equals(other.id);
	}

	/**
	 * Determines an order between this patient and the other patient.
	 * Order is determined by lastname, then firstname and then id.
	 * @param other The other patient to compare.
	 * @return A value less than zero if this patient is ordered before the other patient,
	 *		Zero if equals(other) is true,
	 *		and a value greater than zero if this patient is ordered after the other patient.
	 */
	public int compareTo(PatientLocation other) {
		int diff = 0;
		diff = lastName.compareTo(other.lastName);
		if (diff == 0) {
			diff = firstName.compareTo(other.firstName);
			if (diff == 0) {
				return id.compareTo(other.id);
			}
		}
		return diff;
	}

	/**
	 * @return A string representation of all the patient location information.
 	 * The string format is as follows:<br> pid: lastName, firstName (roomnumber)
	 */
	public String toString() {
		StringBuilder s = new StringBuilder(lastName.length()+firstName.length()+10);
		s.append(id+": ");
		s.append(lastName);
		s.append(",");
		s.append(firstName);
		s.append(" ("+roomNumber+")");
		return s.toString();
	}

	/**
	 * A private helper method that generates a list of possible id numbers that can be used.
 	 * This method is only called once. If alive is true, it does not need to be called again.
 	 */
	private static void createUniqueIdList() {
		fresh = new int[range];
		for (int i=0; i<range; i++) {
			fresh[i] = i;
		}
		alive = true;
	}

	/**
	 * A helper method that sets an id number for this patient location.
	 * @param num The id.  If the id is out of range, then an id is chosen from
	 * the list of available unused id numbers.
	 */
	private String setUniqueId(int num) {
		if (num < 0 || num > range-1) {
			return createUniqueId();
		}
		if (used[num]) {
			return createUniqueId();
		}
		return createId(num);
	}

	/**
	 * A helper method that chooses an unused id number for this patient.
	 * @return The new id number.
	 */
	private String createUniqueId() {
		while (currIndex < fresh.length && used[fresh[currIndex]]) {
			currIndex++;
		}
		if (currIndex >= fresh.length) {
			throw new RuntimeException("Cannot create the new PatientLocation: No unique id numbers available.");
		}
		return createId(fresh[currIndex++]);
	}
	/**
	 * The actual id is a 2 digit number prepended by a 'P'.
	 * This will create the proper formatted id.
	 * @param The 2 digit number.
	 * @return The id, properly formatted.
	 */
	private String createId(int num) {
		StringBuilder sb = new StringBuilder(numDigits+1);
		sb.append("P");
		used[num] = true;
		int base = range/10;
		int currDigit;
		while (base >= 1) {
			currDigit = num/base;
			if (currDigit != 0) {
				num %= 10;
			}
			sb.append(currDigit);
			base /= 10;
		}
		return sb.toString();
	}

	/**
	 * Used for internal testing purposes only.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		PatientLocation p1 = new PatientLocation("Mouse", "Mickey",476);
		System.out.println("Created a new patient location called "+p1);
		// Checking to make sure the ids are unique
		PatientLocation[] bunch = new PatientLocation[90];
		for (int i=0; i<bunch.length; i++) {
			bunch[i] = new PatientLocation(""+i,""+i,i);
		}
		System.out.println("Ninety random id records created.");
		for (int i=0; i<bunch.length; i++) {
			System.out.println("\t"+bunch[i]);
		}
		// Try some homemade id numbers:
		PatientLocation[] others = new PatientLocation[5];
		for (int i=0; i<others.length; i++) {
			others[i] = new PatientLocation(i,""+i,""+i,i);
		}
		System.out.println("Five iterated id records created.");
		for (int i=0; i<others.length; i++) {
			System.out.println("\t"+others[i]);
		}
		System.out.println("Done");
	}
}
	
