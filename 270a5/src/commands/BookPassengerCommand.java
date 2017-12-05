package commands;

import entities.Passenger;
import entities.Flight;
import containers.PassengerSetAccess;
import containers.FlightSetAccess;

/**	
 * Command to book a passenger on a flight.  The person is specified by their name,
 * and the flight by its number.
 */
public class BookPassengerCommand extends CommandStatus
{
	/**	
	 * Book the passenger with the specified name on the flight with the specified number.  
	 * @param name    the name of the passenger to be booked on the flight
	 * @param number  the number for the fLight
	 */
	public void bookPassenger(String name, int number)
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
					f.makeBooking(p);
					successful = true;
				}  catch (Exception e)
				{
					successful = false;
					errorMessage = e.getMessage();
		
				}		
		}
	}
	
	public static void main(String[] args){
		Flight f = new Flight(1,4,20);
		Passenger p = new Passenger("Karen", "222-2222");
		FlightSetAccess.dictionary().put(1, f);
		PassengerSetAccess.dictionary().put("Karen", p);
		
		BookPassengerCommand b = new BookPassengerCommand();
		b.bookPassenger("Karen",1);

		System.out.println(f.toString());	
	}
	
}
