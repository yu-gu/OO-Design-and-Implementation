//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package commands;

import containers.FlightMapAccess;
import entities.Flight;
/**
 * command to display all empty seats in given flight
 * @author yugu
 *
 */
public class displayEmptySeats extends CommandStatus{
	/**
	 * Read the number for a flight, and then display its empty seats.
	 * 
	 * @param number - the flight number
	 * @precond f != null
	 */
	public void displayEmptySeat(int number)
	{
		Flight f = FlightMapAccess.dictionary().get(number);
		if (f == null){
			errorMessage = ("There is no flight with number " + number);
			successful = false;
			}
		else {
			System.out.println(f.availableSeats());
			successful = true;
		}
			
	}
	
}
