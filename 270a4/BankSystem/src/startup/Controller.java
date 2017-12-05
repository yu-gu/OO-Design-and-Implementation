package startup;

import java.text.NumberFormat;

import userInterfaces.AdminInterface;
import userInterfaces.StaffInterface;
import userInterfaces.TellerInterface;
import commands.BalanceCommand;
import commands.ChangeLocCommand;
import commands.DepositCommand;
import commands.MonthEndCommand;
import commands.LoginCheckCommand;

/**
 * Class to obtain and handle a sequence of user commands using console interface.
 */
public class Controller {
    /**
     * staffType is the type of the user whose session is being controlled. StaffType is an
     * enumerated type to record the different types of staff members that can log in.
     */
    private LoginCheckCommand.StaffType staffType;

    /** The interface for the staff member who logged in. */
    private StaffInterface staffInterface;

    /**
     * Start the main control loop for a user; obtain an operation specification and carry out the
     * operation.
     * 
     * @param staffType type of the user who logging in
     * @precond staffType == LoginCheckCommand.StaffType.ADMIN | staffType ==
     *          LoginCheckCommand.StaffType.TELLER
     */
    public void processCommandsFor(LoginCheckCommand.StaffType staffType)
            throws IllegalArgumentException {
        this.staffType = staffType;
        switch (staffType) {
            case ADMIN:
                staffInterface = new AdminInterface();
                break;
            case TELLER:
                staffInterface = new TellerInterface();
                break;
            default:
                throw new IllegalArgumentException(
                        "The user must be either a teller "
                                + "or an administrator");
        }

        int cmdId = staffInterface.getCmdId();
        while (cmdId != 99) {
            handle(cmdId);
            cmdId = staffInterface.getCmdId();
        }
    }

    /**
     * Handle the command ID by executing the appropriate command.
     * 
     * @param cmdId the int identifier entered for a command
     */
    public void handle(int cmdId) {
        switch (cmdId) {
            case 11:
                balanceInquiry();
                break;
            case 12:
                deposit();
                break;
            case 21:
                changeLoc();
                break;
            case 22:
                doMonthEnd();
                break;
            default:
                staffInterface.sendMessage("****Illegal command number ("
                        + cmdId + ") entered.  " + "Please try again.");
        }
    }

    /**
     * Carry out a balance inquiry.
     */
    protected void balanceInquiry() {
        int acctId = staffInterface.getAcctID();
        BalanceCommand balCommand = new BalanceCommand();
        balCommand.accessBalance(acctId);
        if (balCommand.wasSuccessful()) {
            staffInterface.sendMessage("The balance is "
                    + NumberFormat.getCurrencyInstance().format(
                            balCommand.amount) + ".\nReceipt being printed.");
        } else
            staffInterface.sendMessage(balCommand.getErrorMessage());
    }

    /**
     * Carry out a deposit.
     */
    protected void deposit() {
        int acctId = staffInterface.getAcctID();
        float amount = staffInterface.getAmount();
        DepositCommand depositCommand = new DepositCommand();
        depositCommand.deposit(acctId, amount);
        if (depositCommand.wasSuccessful()) {
            staffInterface
                    .sendMessage("Deposit successful.\nReceipt being printed.");
        } else
            staffInterface.sendMessage(depositCommand.getErrorMessage());
    }

    /**
     * Change a line of credit.
     * 
     * @precond staffType == LoginCheckCommand.StaffType.ADMIN
     */
    protected void changeLoc() {
        if (staffType == LoginCheckCommand.StaffType.ADMIN) {
            AdminInterface adminInterface = (AdminInterface) staffInterface;
            int acctId = adminInterface.getAcctID();
            float newLoc = adminInterface.getNewLoc();
            ChangeLocCommand locCommand = new ChangeLocCommand();
            locCommand.changeLoc(acctId, newLoc);
            if (locCommand.wasSuccessful()) {
                adminInterface
                        .sendMessage("Change of line of credit successful"
                                + "\nReceipt being printed.");
            } else
                adminInterface.sendMessage(locCommand.getErrorMessage());
        } else
            staffInterface.sendMessage("*** The user must be an administrator"
                    + " to change the line of credit.");
    }

    /**
     * Carry out the month end activities.
     * 
     * @precond staffType == LoginCheckCommand.StaffType.ADMIN
     */
    protected void doMonthEnd() {
        if (staffType == LoginCheckCommand.StaffType.ADMIN) {
            MonthEndCommand monthlyCommand = new MonthEndCommand();
            monthlyCommand.runStatements();
            monthlyCommand.printStatements();
        } else
            staffInterface.sendMessage("*** The user must be an administrator"
                    + " to obtain the end of month statements");
    }
}
