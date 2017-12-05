package accounts;

import java.util.LinkedList;
import java.text.NumberFormat;

import people.Customer;

/**
 * Bank account with a number, balance, and owner, and keeps a log of transactions on the account.
 */
public abstract class Account {
    /** A unique number for each account */
    protected int number;

    /** Amount of money in the account */
    protected float balance;

    /** The account owner */
    protected Customer owner;

    /** The collection of transactions on this account. */
    protected LinkedList<Transaction> log;

    /**
     * Create a new account with the specified customer and number.
     * 
     * @param c the customer that owns the account
     * @param n the number for the account
     */
    public Account(Customer c, int n) {
        owner = c;
        balance = 0.00f;
        number = n;
        log = new LinkedList<Transaction>();
    }

    /**
     * Return the owner of this account.
     * 
     * @return the owner of this account.
     */
    public Customer getOwner() {
        return owner;
    }

    /**
     * Return the account number.
     * 
     * @return the account number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Return the account balance.
     * 
     * @return the account balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * Return a String representation of the account.
     * 
     * @return a String representation of the account
     */
    @Override
    public abstract String toString();

    /**
     * Add a transaction to the collection of transactions.
     * 
     * @param d the date (in int format)
     * @param des a description of the transaction
     * @param amt the amount of the transaction
     * @param loc the change (if any) in the line of credit
     */
    public void logTrans(int d, String des, float amt, float loc) {
        Transaction t = new Transaction(d, des, amt, loc);
        log.addLast(t);
    }

    /**
     * Generate a listing of all the transactions that are currently in the transaction log.
     * 
     * @return a list of transactions on the account in a format suitable for a statement
     */
    public String generateStmt() {
        String result =
                "\nTransactions for account " + number + " with owner "
                        + owner.getName() + ":" + Transaction.header();
        for (Transaction t : log)
            result = result + t.toString();
        return result + "\nFinal balance is "
                + NumberFormat.getCurrencyInstance().format(balance) + "\n";
    }
}
