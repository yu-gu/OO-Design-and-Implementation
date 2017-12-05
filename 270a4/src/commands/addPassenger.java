//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package commands;

import entities.Passenger;
import containers.PassengerMapAccess;
/**
 * command to add passenger to the system, 
 * including name and telephone number
 * 
 * @author yugu
 */
public class addPassenger extends CommandStatus{
	
	/**
	 * Read the information for a new passenger and then add the passenger 
	 * to the dictionary of all passengers.
	 * 
	 * @param name - the name of the passenger
	 * @param telnumber - the telephone number of the passenger
	 * @precond name does not exist in containers before adding the passenger
	 */
	public void addPassengers(String name, String telNumber)
	{
		/*System.out.print("Enter the name of the passenger: ");
		String name = consoleIn.nextLine();
		System.out.print("Enter the telephone number of the passenger: ");
		String telNumber = consoleIn.nextLine();*/
		if (PassengerMapAccess.dictionary().containsKey(name))
		{
			errorMessage = ("Person not added as there already "
								+ "is a person by the name " + name);
			successful = false;
		}
		else
		{
			Passenger p = new Passenger(name, telNumber);
			Passenger sameNamePerson = PassengerMapAccess.dictionary().put(name, p);
			if (sameNamePerson != null)
			{
				PassengerMapAccess.dictionary().put(name, sameNamePerson);  // put the original person back
				errorMessage = ("Name in the dictionary even though " +
						"containsKey failed.  Passenger " + name + " not entered.");
				successful = false;
			}
			successful = true;
		}	
	}

}
