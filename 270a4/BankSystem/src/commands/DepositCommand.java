package commands;

import containers.AccountSetAccess;
import systemEntities.Clock;
import systemEntities.Printer;

import accounts.*;

/**
 * Command to carry out a deposit in an account.
 */
public class DepositCommand extends Command {
    /**
     * Execute the deposit.
     * 
     * @param acctId the account number
     * @param amount the amount to be deposited
     */
    public void deposit(int acctId, float amount) {
        Account accountAccessed = AccountSetAccess.dictionary().get(acctId);
        if (accountAccessed == null) {
            successful = false;
            errorMessage = "The account numbered " + acctId + " was not found.";
            return;
        } else if (accountAccessed instanceof RegularAccount) {
            try {
                RegularAccount acct = (RegularAccount) accountAccessed;
                acct.deposit(amount);
                date = Clock.getDate();
                this.amount = amount;
                this.acct = acct;
                acct.logTrans(date, "deposit", amount, 0.00f);
                successful = true;
                Printer.printDeposit(acct.getNumber(), amount, date);
            } catch (RuntimeException e) {
                successful = false;
                errorMessage = e.getMessage();
            }
        } else {
            successful = false;
            errorMessage =
                    "\nIllegal deposit: "
                            + "Cannot deposit in a non-regular account."
                            + "\n    The account with number "
                            + accountAccessed.getNumber() + " is a "
                            + accountAccessed.getClass().getName() + ".";
        }
    }
}
