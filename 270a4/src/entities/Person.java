//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package entities;
/**
 * The model of a person who has a name and a telephone number, where the telephone number can be
 * changed but not the name.
 */
public class Person {
    /**
     * The name of the person.
     */
    private String personName;

    /**
     * The telephone number of the Person.
     */
    private String telephoneNumber;

    /**
     * Initialize an instance with the given name and telephone number.
     * 
     * @param name the name for the Person
     * @param number the telephone number for the Person
     */
    public Person(String name, String number) {
        personName = name;
        telephoneNumber = number;
    }

    /**
     * gets the name of the Person
     * @return the name of the Person
     */
    public String getName() {
        return personName;
    }

    /**
     * gets the telephone number
     * @return the telephone number of the Person
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Change the telephone number of the person.
     * 
     * @param newNumber the new telephone number for the Person
     */
    public void setTelephoneNumber(String newNumber) {
        telephoneNumber = newNumber;
    }

    /**
     * creates a String representation
     * @return a String representation of the person
     */
    public String toString() {
        return "\nName: " + personName + "   Telephone number: " + telephoneNumber
                + "\n";
    }

    /**
     * A method to test the Person class.
     */
    public static void main(String[] args) {
        int numErrors = 0;
        Person p = new Person("Pete", "249-5418");
        System.out.println("The person called Pete at 249-5418  is " + p);
        if (!p.getName().equals("Pete")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if (!p.getTelephoneNumber().equals("249-5418")) {
            System.out.println("The constructor or getTelephoneNumber failed");
            numErrors++;
        }
        p.setTelephoneNumber("966-1134");
        if (!p.getTelephoneNumber().equals("966-1134")) {
            System.out.println("setTelephoneNumber failed");
            numErrors++;
        }

        p = new Person("Mary", "975-6735");
        System.out.println("The person called Mary at 975-6735 is " + p);
        if (!p.getName().equals("Mary")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if (!p.getTelephoneNumber().equals("975-6735")) {
            System.out.println("The constructor or getTelephoneNumber failed");
            numErrors++;
        }
        p.setTelephoneNumber("845-7294");
        if (!p.getTelephoneNumber().equals("845-7294")) {
            System.out.println("setTelephoneNumber failed");
            numErrors++;
        }
        System.out.println("The number of errors found in Person class is "
                + numErrors);
    }
}
