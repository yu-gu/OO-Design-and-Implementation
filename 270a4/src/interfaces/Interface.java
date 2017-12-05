//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package interfaces;

import java.util.Scanner;

/**
 * The flight reservation system general interface for users that issues menus, including prompts
 * 
 * @author yugu
 *
 */
public class Interface {
	
    /** A Scanner used to obtain console input */
    protected Scanner consoleIn;
    /** A integer value used for storing commands*/
    private int code;    
    
    /**
     * Initialize code and scanner variable for the user interface
     */
    public Interface(){
    	consoleIn = new Scanner(System.in);
    	/* assign a random value to code */
    	code = 100;
    }
    
    /**
     * Output a message.
     * 
     * @param message the message to be output
     */
    public void sendMessage(String message) {
        System.out.println(message);
    }
    
    /**
     * Read in commands issued by the user
     * 
     * @return code - the appropriate command issued by user
     */
    public int readCommand(){
    	code = Integer.parseInt(consoleIn.nextLine());
    	return code;
    }
    
    /**
     * Get the value of command index
     * 
     * @return code - the code value representing index of commands
     */
    public int getCommand(){
    	return code;
    }
    
    /**
     * Read in the name of user/passenger
     * 
     * @return name - the name of the passenger inputed by user 
     */
	public String getName(){
		System.out.println("Please enter name of passenger: ");
		String name = consoleIn.nextLine();
		return name;
	}
	
	/**
	 * Read in the telephone number of the user/passenger
	 * 
	 * @return TelNumber - the telephone number of passenger
	 */
	public String getTelNumber(){
		System.out.println("Please enter Telephone number: ");
		String TelNumber = consoleIn.nextLine();
		return TelNumber;
	}
	
	/**
	 * Read in the flight number of a flight
	 * 
	 * @return FlightNumber - the flight number of a flight
	 */
	public int getFlightNumber(){
		System.out.println("Please enter Flight number: ");
		int FlightNumber = consoleIn.nextInt();
		consoleIn.nextLine();
		return FlightNumber;
	}
}