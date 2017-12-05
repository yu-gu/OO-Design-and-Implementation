package accounts;

import people.Customer;

/** A regular account that allows deposits. */
public abstract class RegularAccount extends Account {
    /** Constructs a RegularAccount from a Customer and an account number */
    public RegularAccount(Customer c, int n) {
        super(c, n);
    }

    /**
     * Deposit `a' dollars into the account
     * 
     * @precond a >= 0.00f
     */
    public void deposit(float a) {
        if (a < 0.00f)
            throw new RuntimeException("A deposit amount must be positive. "
                    + "\nThe current amount is " + a);
        balance += a;
    }

}
