package containers;

import java.util.HashMap;

import accounts.Account;
import accounts.CheckingAccount;
import accounts.SavingsAccount;

import people.Customer;

/**
 * A singleton class to access the dictionary containing the customers.
 */
public class CustomerSetAccess {
    /**
     * Private constructor to ensure that no instance is created.
     */
    private CustomerSetAccess() {}

    /** The dictionary for customers. */
    private static HashMap<String, Customer> dictionary;

    /**
     * Return the dictionary that maps names to customers.
     * 
     * @return the dictionary that maps names to customers
     */
    public static HashMap<String, Customer> dictionary() {
        if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new HashMap<String, Customer>(10);

            /* Add the customers and their accounts. */
            Customer c = new Customer("Elvis Presley");
            dictionary.put(c.getName(), c);
            Account a = new CheckingAccount(c, 1);
            c.addAccount(a);
            HashMap<Integer, Account> accounts = AccountSetAccess.dictionary();
            accounts.put(1, a);
            a = new SavingsAccount(c, 2);
            c.addAccount(a);
            accounts.put(2, a);

            c = new Customer("Wile E. Coyote");
            dictionary.put(c.getName(), c);
            a = new SavingsAccount(c, 3);
            c.addAccount(a);
            accounts.put(3, a);
        }
        return dictionary;
    }
}
