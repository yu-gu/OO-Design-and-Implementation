package containers;

import java.util.TreeMap;
import entities.Passenger;

/**	
 * A singleton class to access the dictionary containing the passengers. 
 */
public class PassengerSetAccess
{
	/** 
	 * Private constructor to ensure that no instance is created. 
	 */
	private PassengerSetAccess() {}

	/**	The dictionary for passengers. */
	private static TreeMap<String, Passenger> dictionary;

	/**	
	 * @return the dictionary that maps names to passengers 
	 */ 
	public static TreeMap<String, Passenger> dictionary()
	{
		if (dictionary == null)
		{
			/* Create the initial dictionary. */
			dictionary = new TreeMap<String, Passenger>();
		}
		return dictionary;
	}
	
	/**
	 * A simple method to test the class.
	 * @param args  not used
	 */
	public static void main(String[] args)
	{
		Passenger p = new Passenger("Pete", "249-5418");
		if (! PassengerSetAccess.dictionary().isEmpty())
			System.out.println("The dictionary didn't start empty.");
		PassengerSetAccess.dictionary().put("Pete", p);
		if (PassengerSetAccess.dictionary().isEmpty())
			System.out.println("The dictionary shouldn't be empty after a put.");
		if (PassengerSetAccess.dictionary().get("Pete") != p)
			System.out.println("Insert or access isn't working for the dictionary.");
	}
}
