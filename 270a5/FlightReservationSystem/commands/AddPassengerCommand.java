package commands;

import entities.Passenger;
import containers.PassengerSetAccess;

/**	
 * Command to add a new Passenger to the dictionary of all passengers. 
 */
public class AddPassengerCommand extends CommandStatus
{
	/**	
	 * Add a new Passenger to the dictionary of all passengers.  
	 * @param name    the name for the new Passenger
	 * @param address the address for the new Passenger
	 */
	public void addPassenger(String name, String address)
	{
		if (PassengerSetAccess.dictionary().containsKey(name))
		{
			successful = false;
			errorMessage = "Person not added as there already "
					+ "is a person by the name " + name;
		}
		else
			try
			{
				Passenger p = new Passenger(name, address);
				Passenger sameNamePerson = PassengerSetAccess.dictionary().put(name, p);
				if (sameNamePerson != null)
				{
					/* Put the original passenger back into the dictionary. */
					PassengerSetAccess.dictionary().put(name, sameNamePerson);
					successful = false;
					errorMessage = "Name exists even though containsKey failed."
									+ " Passenger " + name + " not entered.";
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
		AddPassengerCommand p = new AddPassengerCommand();
		p.addPassenger("Karen", "222-2222");
		System.out.println(PassengerSetAccess.dictionary().get("Karen").toString());
		
	}
}
