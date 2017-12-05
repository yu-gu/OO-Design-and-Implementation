package commands;

import entities.Passenger;
import entities.Flight;
import containers.PassengerSetAccess;
import containers.FlightSetAccess;

/**	
 * Command to assign a seat to a regular passenger on a flight where the passenger
 * has already booked the flight.  The person is specified by their name, 
 * and the flight by its number.
 */
public class AssignSeatCommand extends CommandStatus
{
	/**	
	 * Assign the passenger with the specified name to the specified seat on the flight 
	 * with the specified number.  
	 * @param name    the name of the passenger to be assigned a seat
	 * @param number  the number for the flight
	 * @param seat    the seat for the passenger
	 */
	public void assignSeatForPassenger(String name, int number, String seat)
	{
		Passenger p = PassengerSetAccess.dictionary().get(name);
		if (p == null)
		{
			successful = false;
			errorMessage = "There is no passenger with name " + name;
		}
		else
		{
			Flight f = FlightSetAccess.dictionary().get(number);
			if (f == null)
			{
				successful = false;
				errorMessage = "There is no flight with number " + number;
			}
			else 
				try
				{
					f.assignSeat(p, seat);
					successful = true;
				}  catch (Exception e)
				{
					successful = false;
					errorMessage = e.getMessage();
		
				}		
		}
	}
}
