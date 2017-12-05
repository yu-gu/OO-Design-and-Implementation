//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package containers;

import java.util.TreeMap;
import entities.*;

/**
 * A singleton class to access the dictionary of passengers.
 */
public class PassengerMapAccess {
	/**
	 * Private constructor to ensure that no instance is created.
	 */
	private PassengerMapAccess() {}
	
	/** 
	 * The dictionary for storing passengers.
	 */
	private static TreeMap<String, Passenger> dictionary;
	
	
    /**
     * Return the dictionary that maps passenger names to passengers.
     * 
     * @return the dictionary that maps passenger names to passengers
     */
    public static TreeMap<String, Passenger> dictionary() {
        if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new TreeMap<String, Passenger>();
            //dictionary.put("Mr. Drysdale", new Passenger("Mr. Drysdale", "123"));
        }
        return dictionary;
    }
}
	