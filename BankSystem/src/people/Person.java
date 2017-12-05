package people;

/**
 * A general person with only a name.
 */
public class Person {
    /** The name of this person */
    private String name;

    /**
     * Construct a person with a name.
     * 
     * @param n the name of this person
     */
    public Person(String n) {
        name = n;
    }

    /**
     * Set the name of this person to be "n"
     * 
     * @param n the new name for this person
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Return this person's name.
     * 
     * @return this person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Return a string representation of this person.
     * 
     * @return a string representation of this person
     */
    @Override
    public String toString() {
        return name + "\n";
    }
}
