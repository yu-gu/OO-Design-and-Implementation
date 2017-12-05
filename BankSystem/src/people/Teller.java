package people;

/**
 * A staff member with teller privileges.
 */
public class Teller extends Staff {
    /**
     * Construct a teller from a name, an id number, and a password String
     * 
     * @param n the name of the new teller
     * @param i the int identifier for this teller
     * @param pw the password for this teller
     */
    public Teller(String n, int i, String pw) {
        super(n, i, pw);
    }
}
