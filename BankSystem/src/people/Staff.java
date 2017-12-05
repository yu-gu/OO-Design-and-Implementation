package people;

/**
 * A staff member with a unique identification number and password.
 */
public abstract class Staff extends Person {
    /** The staff member's unique id */
    private int id;

    /** The staff member's password */
    private String password;

    /**
     * Construct a Staff from a name, an id number, and password string.
     * 
     * @param n the name of this person
     * @param i the int identifier for this person
     * @param pd the password for this person
     */
    public Staff(String n, int i, String pd) {
        super(n);
        id = i;
        password = pd;
    }

    /**
     * Return the id for this staff member.
     * 
     * @return the id for this staff member
     */
    public int getId() {
        return id;
    }

    /**
     * Return the password for this staff member.
     * 
     * @return the password for this staff member
     */
    public String getPassword() {
        return password;
    }
}
