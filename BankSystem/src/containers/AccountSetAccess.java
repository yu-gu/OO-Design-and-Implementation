package containers;

import java.util.HashMap;

import accounts.Account;

/**
 * A singleton class to access the dictionary of accounts.
 */
public class AccountSetAccess {
    /**
     * Private constructor to ensure that no instance is created.
     */
    private AccountSetAccess() {}

    /** The dictionary for accounts. */
    private static HashMap<Integer, Account> dictionary;

    /**
     * Return the dictionary that maps account numbers to account objects.
     * 
     * @return the dictionary that maps account numbers to account objects
     */
    public static HashMap<Integer, Account> dictionary() {
        if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new HashMap<Integer, Account>(10);
        }
        return dictionary;
    }
}
