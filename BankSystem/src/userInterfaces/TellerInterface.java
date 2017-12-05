package userInterfaces;

/**
 * The interface for tellers that issues the appropriate menu.
 */
public class TellerInterface extends StaffInterface {
    /**
     * Read in an the integer which represents the command to be done.
     * 
     * @return the int identifier for the command to be done
     */
    @Override
    public int getCmdId() {
        int result =
                readInt("\nChoose a teller command:" + "\n11. Balance"
                        + "\n12. Deposit" + "\n99. Quit" + "\nChoice: ");
        consoleIn.nextLine(); // read the end of line after the command id
        return result;
    }
}
