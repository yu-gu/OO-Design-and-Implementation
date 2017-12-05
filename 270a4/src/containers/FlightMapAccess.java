//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package containers;

import java.util.TreeMap;
import entities.*;

/**
 * A singleton class to access the dictionary of Flights.
 */
public class FlightMapAccess {
	
	/**
	 * Private constructor to ensure that no instance is created.
	 */
	private FlightMapAccess() {}

	/**
	 * The dictionary for storing all flights.
	 */
	private static TreeMap<Integer, Flight> dictionary;

    /**
     * Return the dictionary that maps flight numbers to flights
     * 
     * @return the dictionary that maps flight numbers to flights
     */
	public static TreeMap<Integer, Flight> dictionary() {
	  if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new TreeMap<Integer, Flight>();
            Flight f1 = new Flight(123,4,28);
            dictionary.put(123,f1);
       }
        return dictionary;
	}
}
