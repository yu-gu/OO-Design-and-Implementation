package commands;

import entities.Flight;
import entities.Passenger;
import containers.FlightSetAccess;

/**	
 * Command to obtain a String that displays the empty seats of a flight.
 */
public class DisplayEmptySeatsCommand extends CommandStatus
{
	/**
	 * The field to store the String displaying the empty seats.
	 */
	private String emptySeatsString;
	
	/**	
	 * Generate a String that displays the empty seats of the Flight 
	 * with number 'number'.  
	 * @param number  the number for the FLight whose empty seats are to be displayed
	 */
	public DisplayEmptySeatsCommand(int number)
	{
		Flight f = FlightSetAccess.dictionary().get(number);
		if (f == null)
		{
			errorMessage = "There is no flight with number " + number;
			successful = false;
		}
		else
		{
			emptySeatsString = f.availableSeats();
			successful = true;
		}
	}
	
	/**
	 * @return the String that contains the display of empty seats
	 * @precond successful
	 */
	public String getEmptySeatsString()
	{
		if (!successful)
			throw new RuntimeException("Cannot access the empty seats display unless" +
					" the previous run of generateEmptySeatsDisplay was successful.");
		return emptySeatsString;
	}
	
	public static void main(String[] args){
		Flight f = new Flight(1,4,20);
		Passenger p = new Passenger("Karen", "222-2222");
		f.makeBooking(p);
		f.assignSeat(p, "1B");
		FlightSetAccess.dictionary().put(1, f);
		DisplayEmptySeatsCommand d = new DisplayEmptySeatsCommand(1);
		System.out.println(d.getEmptySeatsString());	
	}
	
}
