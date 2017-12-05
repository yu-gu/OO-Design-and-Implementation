package commands;

import containers.AccountSetAccess;
import systemEntities.Clock;
import systemEntities.Printer;

/**
 * Command to obtain the balance for an account.
 */
public class BalanceCommand extends Command {
    /**
     * Access the balance in the account with the specified account number.
     * 
     * @param acctId the account number
     */
    public void accessBalance(int acctId) {
        date = Clock.getDate();
        acct = AccountSetAccess.dictionary().get(acctId);
        if (acct != null) {
            successful = true;
            amount = acct.getBalance();
            // print receipt
            Printer.printBalance(acct.getNumber(), amount, date);

        } else {
            successful = false;
            errorMessage = "Account number " + acctId + " not found.";
        }
    }
}
