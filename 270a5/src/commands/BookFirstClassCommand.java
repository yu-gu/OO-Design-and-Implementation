package commands;

import entities.Passenger;
import entities.Flight;
import containers.PassengerSetAccess;
import containers.FlightSetAccess;

/**	
 * Command to book a first-class passenger on a flight.  The person is specified 
 * by their name, and the flight by its number.
 */
public class BookFirstClassCommand extends CommandStatus
{
	/**	
	 * Book the passenger with the specified name on the flight with the specified number
	 * in the specified seat and specified meal plan for a first-class passenger.  
	 * @param name    the name of the passenger to be booked on the flight
	 * @param number  the number for the fLight
	 * @param seat    the seat for the passenger
	 * @param meal    the meal plan for the passenger
	 */
	public void bookFirstClassPassenger(String name, int number, String seat, String meal)
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
					f.makeFirstClassBooking(p, seat, meal);
					successful = true;
				}  catch (Exception e)
				{
					successful = false;
					errorMessage = e.getMessage();
		
				}		
		}
	}
}
