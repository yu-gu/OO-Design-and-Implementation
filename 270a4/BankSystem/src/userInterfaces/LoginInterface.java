package userInterfaces;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A general user interface for input from and output to the console.
 */
public class LoginInterface {
    /** A Scanner used to obtain console input */
    protected Scanner consoleIn = new Scanner(System.in);

    /**
     * Read in a login id number
     * 
     * @return the integer read in to be a login id
     */
    public int getLoginID() {
        int result = readInt("Enter login ID (enter 0 to exit): ");
        consoleIn.nextLine(); // read the end of line after the id
        return result;
    }

    /**
     * Read in a String for the password of the user
     * 
     * @return the String that was read in
     */
    public String getPassword() {
        System.out.print("Enter your password: ");
        return consoleIn.nextLine();
    }

    /**
     * Read in an the integer which represents the command to be done.
     * 
     * @return 99 to indicate that there are no commands associated with this interface
     */
    public int getCmdId() {
        return 99;
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
     * Read in an int value using the specified prompt.
     * 
     * @param prompt the String to be used to prompt the user
     * @return the int value obtained
     */
    public int readInt(String prompt) {
        int result = 0; // must be initialized
        boolean successful;
        do {
            System.out.print(prompt);
            successful = true;
            try {
                result = consoleIn.nextInt();
            } catch (InputMismatchException e) {
                successful = false;
                String faultyInput = consoleIn.nextLine();
                System.out.print("Not a valid int value, you entered \""
                        + faultyInput + "\".  Please try again.\n\t");
            }
        } while (!successful);
        return result;
    }

    /**
     * Read in a double value using the specified prompt.
     * 
     * @param prompt the String to be used to prompt the user
     * @return the double value obtained
     */
    public double readDouble(String prompt) {
        double result = 0.0; // must be initialized
        boolean successful;
        do {
            System.out.print(prompt);
            successful = true;
            try {
                result = consoleIn.nextDouble();
            } catch (InputMismatchException e) {
                successful = false;
                String faultyInput = consoleIn.nextLine();
                System.out.print("Not a valid double value, you entered \""
                        + faultyInput + "\".  Please try again.\n\t");
            }
        } while (!successful);
        return result;
    }
}
