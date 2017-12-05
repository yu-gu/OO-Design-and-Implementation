package accounts;

import people.Customer;
import util.FormatUos;
import java.text.NumberFormat;

/**
 * A savings account.
 */
public class SavingsAccount extends RegularAccount {
    /**
     * Construct a SavingsAccount from a customer and an account number
     * 
     * @param c the customer who owns the account
     * @param n the number for the account
     */
    public SavingsAccount(Customer c, int n) {
        super(c, n);
    }

    /**
     * Return a string representation of the SavingsAccount.
     * 
     * @return a string representation of the SavingsAccount
     */
    @Override
    public String toString() {
        return "Savings account " + number + ": Owner " + owner.getName()
                + "\n" + FormatUos.pad("", 11, 'l') + "Balance "
                + NumberFormat.getCurrencyInstance().format(balance) + "\n";
    }
}
