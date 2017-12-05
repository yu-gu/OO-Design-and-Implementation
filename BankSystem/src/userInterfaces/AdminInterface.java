package userInterfaces;

/**
 * The console interface for administrative personnel that issues the appropriate menu for an
 * administrator.
 */
public class AdminInterface extends StaffInterface {
    /**
     * Read in an integer which represents the command to be done.
     * 
     * @return the int identifier for the command to be done
     */
    @Override
    public int getCmdId() {
        int result =
                readInt("\nChoose an admin command:" + "\n11. Balance"
                        + "\n12. Deposit" + "\n21. Change line of credit"
                        + "\n22. Generate month end statements" + "\n99. Quit"
                        + "\nChoice: ");
        consoleIn.nextLine(); // read the end of line after the command id
        return result;
    }

    /**
     * Read a float value for the line of credit.
     * 
     * @return the new line of credit
     */
    public float getNewLoc() {
        float result = (float) readDouble("Enter the new line of credit: ");
        consoleIn.nextLine(); // read the end of line after the line of credit
        return result;
    }
}
