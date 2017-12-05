package containers;

import java.util.HashMap;

import people.*;

/**
 * A singleton class to access the dictionary containing staff members.
 */
public class StaffSetAccess {
    /**
     * Private constructor to ensure that no instance is created.
     */
    private StaffSetAccess() {}

    /** The dictionary for staff. */
    private static HashMap<Integer, Staff> dictionary;

    /**
     * Return the dictionary that maps staff ID's to staff members.
     * 
     * @return the dictionary that maps staff ID's to staff members
     */
    public static HashMap<Integer, Staff> dictionary() {
        if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new HashMap<Integer, Staff>(5);
            dictionary.put(1, new Admin("Mr. Drysdale", 1, "1"));
            dictionary.put(100, new Teller("George Bailey", 100, "100"));
        }
        return dictionary;
    }
}
