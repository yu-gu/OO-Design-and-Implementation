package commands;

import entities.Flight;
import containers.FlightSetAccess;

/**	
 * Command to obtain the flight identified by its number.
 */
public class GetFlightCommand extends CommandStatus
{
	/** 
	 * The flight obtained by the command.
	 */
	private Flight flight;
	
	/**	
	 * Obtain the flight with the specified number and assign it to the flight field.  
	 * @param number  the number for the flight to be accessed
	 */
	public void accessFlight(int number)
	{
		Flight f = FlightSetAccess.dictionary().get(number);
		if (f == null)
		{
			successful = false;
			errorMessage = "There is no flight with number " + number;
		}
		else
		{
			successful = true;
			flight = f;
		}	
	}
	/**	
	 * @precond wasSuccessful()
	 * @return the flight obtained by this command
	 */
	public Flight getFlight()
	{
		if (wasSuccessful())
			return flight;
		else
			throw new RuntimeException("Cannot get the flight "
					                   + "if accessFlight was not successful");
	}
}
