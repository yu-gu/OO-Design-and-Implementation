package people;

import java.util.LinkedList;
import accounts.Account;

/**
 * A bank customer with a list of accounts.
 */
public class Customer extends Person {
    /** A linked list of accounts that belong to this Customer. */
    private LinkedList<Account> accounts;

    /**
     * Construct a new Customer from a name.
     * 
     * @param n the name of the new customer
     */
    public Customer(String n) {
        super(n);
        accounts = new LinkedList<Account>();
    }

    /**
     * Does this customer have an account with number num?
     * 
     * @return does this customer have an account with number num?
     */
    public boolean hasAccount(int num) {
        for (Account a : accounts)
            if (a.getNumber() == num)
                return true;
        return false;
    }

    /**
     * Add the specified Account to the list of accounts.
     * 
     * @param a the new account to be added to this customer
     * @precond ! hasAccount(a.getNumber())
     */
    public void addAccount(Account a) {
        if (hasAccount(a.getNumber()))
            throw new RuntimeException("The customer already has an "
                    + "account with number " + a.getNumber());
        accounts.addFirst(a);
    }

    /**
     * Return a string representation of the Customer.
     * 
     * @return a string representation of the Customer
     */
    @Override
    public String toString() {
        String temp = "Name: " + getName() + "\n";
        for (Account a : accounts)
            temp += a.toString();
        return temp;
    }
}
