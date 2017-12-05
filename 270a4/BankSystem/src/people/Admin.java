package people;

/**
 * A person with bank administrator privileges.
 */
public class Admin extends Staff {
    /**
     * Construct a new Admin object from a name, an employee id, and a password String.
     * 
     * @param n the name of the administrator
     * @param i the int identifier for the person
     * @param pd the password for the person
     */
    public Admin(String n, int i, String pd) {
        super(n, i, pd);
    }
}
