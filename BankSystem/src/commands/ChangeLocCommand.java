package commands;

import containers.AccountSetAccess;
import systemEntities.Clock;
import systemEntities.Printer;
import accounts.Account;
import accounts.CheckingAccount;

/**
 * Command to change the line of credit for an account. The command can only be applied to checking
 * accounts.
 */
public class ChangeLocCommand extends Command {
    /**
     * The new value for the line of credit.
     */
    public float newLoc;

    /**
     * Change the line of credit for the account with number acctId.
     * 
     * @param acctId the account number
     * @param amount the new line of credit amount
     */
    public void changeLoc(int acctId, float amount) {
        Account accountAccessed = AccountSetAccess.dictionary().get(acctId);
        if (accountAccessed == null) {
            successful = false;
            errorMessage = "*** Account number " + acctId + " was not found.";
            return;
        } else if (accountAccessed instanceof CheckingAccount) {
            try {
                CheckingAccount acct = (CheckingAccount) accountAccessed;
                acct.setLineOfCredit(amount);
                newLoc = amount;
                date = Clock.getDate();
                this.amount = amount;
                this.acct = acct;
                acct.logTrans(date, "change line of credit to $", 0.0f, amount);
                successful = true;
                Printer.printChangeLoc(acct.getNumber(), newLoc, date);
            } catch (RuntimeException e) {
                successful = false;
                errorMessage = e.getMessage();
            }
        } else {
            successful = false;
            errorMessage =
                    "Illegal change of line of credit.  "
                            + "Account must be checking.  \n     The account with number "
                            + accountAccessed.getNumber() + " is a "
                            + accountAccessed.getClass().getName() + ".";
        }
    }
}
