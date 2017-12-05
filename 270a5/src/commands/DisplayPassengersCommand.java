package commands;

import containers.PassengerSetAccess;

/**
 * A command to yield a string that contains all the passengers in the current
 * system.
 */
public class DisplayPassengersCommand extends CommandStatus 
{
	/**
	 * The string to contain the passengers.
	 */
	private String passengersString;
	
	/**
	 * Set passengersString to contain the passengers of the system, 
	 * and record the command as successful.
	 */
	public DisplayPassengersCommand()
	{
		/* The output should be formatted much better, 
		 * but use the format from the Map. */
		passengersString = PassengerSetAccess.dictionary().values().toString();
		successful = true;
	}
	
	/**
	 * @return the string containing a list of all the passengers
	 */
	public String getPassengersString()
	{
		if (!wasSuccessful())
			throw new RuntimeException("Cannot access operation result " +
					                   "if the operation was not successful");
		return passengersString;
	}
}
