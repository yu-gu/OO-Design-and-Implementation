package startup;
import interfaces.Interface;

/**
 * A class to run the flight reservation system using console I/O.
 */
public class Startup {
	/**
     * Run the flight reservation system.
     */
    public static void main(String[] args) {
    
    /*
     * For testing purposes, initialize the interfaces and 
     * controller for later use
     */
    Interface inter = new Interface();
    Controller control = new Controller();

    /*
     * Invoke methods from interface and controller for testing purposes
     */
    control.handle(inter.readCommand());
    while (inter.getCommand() != 0){
        System.out.println("Only flight f1 (flight number 123) is available");
    	control.handle(inter.readCommand());
    }
    
    }
}
