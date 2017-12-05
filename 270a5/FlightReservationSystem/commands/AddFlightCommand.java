package commands;

import entities.Flight;
import containers.FlightSetAccess;

/**	
 * Command to add a new Flight to the dictionary of all flights. 
 */
public class AddFlightCommand extends CommandStatus
{
	/**	
	 * Add a new Flight to the dictionary of all flights.  
	 * @param number   the number for the new FLight
	 * @param width    the number of seats in a row
	 * @param capacity the total number of seats on the new Flight
	 */
	public void addFlight(int number, int width, int capacity)
	{
		if (FlightSetAccess.dictionary().containsKey(number))
		{
			successful = false;
			errorMessage = "Flight not added as there already "
				+ "is a flight with the number " + number;
		}
		else
			try
			{
				Flight f = new Flight(number, width, capacity);
				Flight sameNumberFlight = FlightSetAccess.dictionary().put(number, f);
				if (sameNumberFlight != null)
				{
					/* Put the original flight back into the dictionary. */
					FlightSetAccess.dictionary().put(number, sameNumberFlight);
					successful = false;
					errorMessage = "Name exists even though containsKey failed."
									+ " Flight "  + number + " not entered.";
				}
				else
					successful = true;
			}  catch (Exception e)
			{
				successful = false;
				errorMessage = e.getMessage();
	
			}		

	}
	
	public static void main(String[] args){
		AddFlightCommand f = new AddFlightCommand();
		f.addFlight(1,4, 20);
		System.out.println(FlightSetAccess.dictionary().get(1).toString());
		
	}
	
}
