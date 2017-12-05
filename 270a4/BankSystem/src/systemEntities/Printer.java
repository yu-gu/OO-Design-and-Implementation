package systemEntities;

import util.FormatUos;

/**
 * A class to represent the system printer. The current version just outputs to the screen.
 */
public class Printer {
    /*
     * As there are no fields to store and only one printer, there is no need for an instance and
     * all methods are static.
     */

    /**
     * Output the specified message on a new line.
     * 
     * @param message the message to be output
     */
    public static void printMessage(String message) {
        System.out.println("\n" + message);
    }

    /**
     * Output the balance information passed in via the parameters
     * 
     * @param accountId the int identifier for the account
     * @param amount the current balance for the account
     * @param date the date to be recorded with the balance
     */
    public static void printBalance(int accountId, float amount, int date) {
        String temp =
                "On date " + date + " the balance for account number "
                        + accountId + " is $"
                        + FormatUos.withDecimals(amount, 2) + ".";
        printMessage(temp);
    }

    /**
     * Output the deposit information passed in via the arguments.
     * 
     * @param accountId the int identifier for the account
     * @param amount the amount deposited
     * @param date the date to be recorded with the deposit
     */
    public static void printDeposit(int accountId, float amount, int date) {
        String temp =
                "On date " + date + " the amount $"
                        + FormatUos.withDecimals(amount, 2)
                        + " was deposited in the account with number "
                        + accountId + ".";
        printMessage(temp);
    }

    /**
     * Output the change in credit information passed in via the arguments.
     * 
     * @param accountId the int identifier for the account
     * @param amount the new line of credit
     * @param date the date to be recorded for the change
     */
    public static void printChangeLoc(int accountId, float amount, int date) {
        String temp =
                "On date " + date
                        + " the line of credit for the account with number "
                        + accountId + " was change to $"
                        + FormatUos.withDecimals(amount, 2) + ".";
        printMessage(temp);
    }

    /**
     * Output the new account information passed in via the parameters.
     * 
     * @param accountId the int identifier for the account
     * @param date the date for the opening of the account
     * @param name the name of the customer opening the account
     */
    public static void printOpenAccount(int accountId, int date, String name) {
        String temp =
                "On date " + date + " account " + accountId + " was opened by "
                        + name + ".";
        printMessage(temp);
    }
}
