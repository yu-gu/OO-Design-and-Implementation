package commands;

import containers.AccountSetAccess;
import systemEntities.Clock;
import accounts.CheckingAccount;
import accounts.RegularAccount;
import accounts.SavingsAccount;

/**
 * Command to access an account so that its fields can be accessed and/or set.
 */
public class AccountAccessCommand extends Command {
    /**
     * Access the account with the specified identifier.
     * 
     * @param acctId the account number
     */
    public void accessAccount(int acctId) {
        date = Clock.getDate();
        acct = AccountSetAccess.dictionary().get(acctId);
        if (acct != null) {
            successful = true;
            amount = acct.getBalance();
        } else {
            successful = false;
            errorMessage = "***  Account number " + acctId + " not found.";
        }
    }

    /**
     * Obtain the String specification of the type the account.
     * 
     * @precond acct != null
     * @return the String representation of type of account associated with this command
     */
    public String getAcctTypeAsString() {
        if (acct == null)
            throw new IllegalStateException(
                    "Cannot access account information "
                            + "unless the account has already been accessed.");

        String result = null;
        if (acct instanceof RegularAccount)
            result = "Regular Account";
        else if (acct instanceof CheckingAccount)
            result = "Checking Account";
        else if (acct instanceof SavingsAccount)
            result = "Savings Account";
        else
            throw new IllegalStateException("Illegal type of account");
        return result;
    }

    /**
     * Obtain the owner for the account.
     * 
     * @precond acct != null
     * @return the owner of the account associated with this command
     */
    public String getOwnerName() {
        if (acct == null)
            throw new IllegalStateException(
                    "Cannot access account information "
                            + "unless the account has already been accessed.");

        return acct.getOwner().getName();
    }

    /**
     * Obtain the identifier for the account.
     * 
     * @precond acct != null
     * @return the number of the account associated with this command
     */
    public int getId() {
        if (acct == null)
            throw new IllegalStateException("Cannot access account identifier "
                    + "unless the account has already been accessed.");

        return acct.getNumber();
    }

    /**
     * Obtain the current balance for the account.
     * 
     * @precond acct != null
     * @return the balance of the account associated with this command
     */
    public float getBalance() {
        if (acct == null)
            throw new IllegalStateException("Cannot access account balance "
                    + "unless the account has already been accessed.");

        return acct.getBalance();
    }

    /**
     * Does the account have a line of credit?
     * 
     * @return does the account have a line of credit?
     */
    public boolean accountHasLoc() {
        if (acct instanceof CheckingAccount)
            return true;
        else
            return false;
    }

    /**
     * Obtain the current line of credit for the account.
     * 
     * @precond acct != null && accountHasLoc()
     * @return the line of credit for the account associated with this command
     */
    public float getLineOfCredit() {
        if (acct == null || !accountHasLoc())
            throw new IllegalStateException(
                    "Cannot access account line of credit "
                            + "unless the account has already been accessed "
                            + "and the account is a checking account.");

        return ((CheckingAccount) acct).getLineOfCredit();
    }

}
