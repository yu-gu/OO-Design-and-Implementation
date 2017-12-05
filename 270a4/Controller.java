package startup;

import interfaces.*;
import commands.*;

/**
 * Class to obtain and handle a sequence of user commands using console interface.
 */
public class Controller {
	
    /** The interface for person who make operations */
	private Interface interfaces = new Interface();
	
	/**
	 * Process the command given by the user
	 * 
	 * @param cmdId
	 * @precond cmdId != null cmdId must be number given
	 */
    public void handle(int cmdId) {
        switch (cmdId) {
            case 1:
            	addPassenger();
                break;
            case 2:
                bookPassenger();
                break;
            case 3:
                displayEmptySeats();
                break;
            case 0:
                break;
            default:
                interfaces.sendMessage("****Illegal command number ("
                        + cmdId + ") entered.  " + "Please try again.");
        }
    }
    
	
    /**
     * add a passenger to the systems
     * 
     * @precond passenger cannot exist in the system already
     */
    public void addPassenger(){
    	String name = interfaces.getName();
    	String telNumber = interfaces.getTelNumber();
    	
    	addPassenger addnewPassenger = new addPassenger();
    	addnewPassenger.addPassengers(name, telNumber);
    	if (addnewPassenger.wasSuccessful()){
    		interfaces.sendMessage("add Passenger successful");
    	}else{
    		interfaces.sendMessage(addnewPassenger.getErrorMessage());
    	}	   
    }
    
    /**
     * add a regular booking for a passenger
     */
	public void bookPassenger(){
		String name = interfaces.getName();
    	int FlightNumber = interfaces.getFlightNumber();
    	bookPassenger booknewPassenger = new bookPassenger();
    	booknewPassenger.bookPassengers(name, FlightNumber);
    	
    	if (booknewPassenger.wasSuccessful()){
    		interfaces.sendMessage("book Passenger successful");
    	}else{
    		interfaces.sendMessage(booknewPassenger.getErrorMessage());
    	}	
	}
	
	/**
	 * display the seats that are empty
	 */
	public void displayEmptySeats(){
		int flightnumber = interfaces.getFlightNumber();
		
		displayEmptySeats EmptySeats = new displayEmptySeats();
		EmptySeats.displayEmptySeat(flightnumber);
		if (EmptySeats.wasSuccessful()){
    		interfaces.sendMessage("display empty seats successful");
    	}else{
    		interfaces.sendMessage(EmptySeats.getErrorMessage());
    	}	
	}	
}
