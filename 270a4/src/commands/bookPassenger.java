//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package commands;

import entities.*;
import containers.*;
/**
 * command to book given passenger to the system
 * @author yugu
 *
 */
public class bookPassenger extends CommandStatus{
	/**
	 * Read the name for a passenger, the number for a flight, and then book
	 * the passenger on the flight as a regular passenger.
	 * @param name - name of the passenger
	 * @param number - flight number
	 * @precond p != null f != null
	 */
	public void bookPassengers(String name,int number)
	{
		Passenger p = PassengerMapAccess.dictionary().get(name);
		if (p == null){
			errorMessage = ("There is no passenger with name " + name);
			successful = false;
		}
		Flight f = FlightMapAccess.dictionary().get(number);
		if (f == null){
			errorMessage = ("There is no flight with number " + number);
			successful = false;
		}
		else{ 
			f.makeBooking(p);
			successful = true;
		}	 
	}
}
