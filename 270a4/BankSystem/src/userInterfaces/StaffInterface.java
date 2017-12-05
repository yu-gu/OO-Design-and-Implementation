package userInterfaces;

/**
 * This class contains the interface features to obtain an account number and to obtain an amount
 * from the console.
 */
public class StaffInterface extends LoginInterface {
    /**
     * Read in an int value for an account number.
     * 
     * @return the int read in
     */
    public int getAcctID() {
        int result = readInt("Enter the account number: ");
        consoleIn.nextLine(); // read the end of line after the account number
        return result;
    }

    /**
     * Read in an amount of type float.
     * 
     * @return the float value read in
     */
    public float getAmount() {
        float result = (float) readDouble("Enter the amount: ");
        consoleIn.nextLine(); // read the end of line after the amount
        return result;
    }
}
