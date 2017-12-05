package accounts;

import people.Customer;
import util.FormatUos;
import java.text.NumberFormat;

/**
 * A checking account allows an overdraft up to the line of credit of the account.
 */
public class CheckingAccount extends RegularAccount {
    /** The line of credit for the account. */
    protected float lineOfCredit;

    /**
     * Initialize the account.
     * 
     * @param c the customer who owns the account
     * @param n the number for the account
     */
    public CheckingAccount(Customer c, int n) {
        super(c, n);
        lineOfCredit = 0.00f;
    }

    /**
     * Change the line of credit.
     * 
     * @param amount the new value for the line of credit
     * @precond ammount >= 0
     */
    public void setLineOfCredit(float amount) {
        if (amount < 0.0f)
            throw new RuntimeException(
                    "The new loc cannot be negative.  It is " + amount);

        lineOfCredit = amount;
    }

    /**
     * Return the line of credit for the account.
     * 
     * @return the line of credit for the account
     */
    public float getLineOfCredit() {
        return lineOfCredit;
    }

    /**
     * Generate the statement for the account listing all the transactions.
     * 
     * @return a String containing the statement
     */
    @Override
    public String generateStmt() {
        return super.generateStmt() + "The line of credit is "
                + NumberFormat.getCurrencyInstance().format(lineOfCredit)
                + "\n";
    }

    /**
     * Return a String representation of the account details.
     * 
     * @return a String representation of the account details
     */
    @Override
    public String toString() {
        return "Checking account " + number + ": Owner " + owner.getName()
                + "\n" + FormatUos.pad("", 11, 'l') + "Balance "
                + NumberFormat.getCurrencyInstance().format(balance) + "\n"
                + FormatUos.pad("", 11, 'l') + "Line of credit "
                + NumberFormat.getCurrencyInstance().format(lineOfCredit)
                + "\n";
    }
}
