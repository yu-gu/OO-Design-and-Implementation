package containers;

import java.util.TreeMap;
import entities.Flight;

/**	
 * A singleton class to access the dictionary containing all the flights. 
 */
public class FlightSetAccess
{
	/** 
	 * Private constructor to ensure that no instance is created. 
	 */
	private FlightSetAccess() {}

	/**	The dictionary for flights. */
	private static TreeMap<Integer, Flight> dictionary;

	/**	
	 * @return the dictionary that maps numbers to flights 
	 */ 
	public static TreeMap<Integer, Flight> dictionary()
	{
		if (dictionary == null)
		{
			/* Create the initial dictionary. */
			dictionary = new TreeMap<Integer, Flight>();
		}
		return dictionary;
	}
	
	/**
	 * A simple method to test the class.
	 * @param args  not used
	 */
	public static void main(String[] args)
	{
		Flight f = new Flight(513, 4, 13);
		if (! FlightSetAccess.dictionary().isEmpty())
			System.out.println("The dictionary didn't start empty.");
		FlightSetAccess.dictionary().put(513, f);
		if (FlightSetAccess.dictionary().isEmpty())
			System.out.println("The dictionary shouldn't be empty after a put.");
		if (FlightSetAccess.dictionary().get(513) != f)
			System.out.println("Insert or access isn't working for the dictionary.");
	}
}
