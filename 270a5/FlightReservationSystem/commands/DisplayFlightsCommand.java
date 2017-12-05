package commands;

import containers.FlightSetAccess;

/**
 * A command to yield a string that contains all the flights in the current
 * system.
 */
public class DisplayFlightsCommand extends CommandStatus 
{
	/**
	 * The string to contain the flights.
	 */
	private String flightsString;
	
	/**
	 * Set flightsString to contain the flights of the system, 
	 * and record the command as successful.
	 */
	public DisplayFlightsCommand()
	{
		/* The output should be formatted much better, 
		 * but use the format from the Map. */
		flightsString = FlightSetAccess.dictionary().values().toString();
		successful = true;
	}
	
	/**
	 * @return the string containing a list of all the flights
	 */
	public String getFlightsString()
	{
		if (!wasSuccessful())
			throw new RuntimeException("Cannot access operation result " +
					                   "if the operation was not successful");
		return flightsString;
	}
}
