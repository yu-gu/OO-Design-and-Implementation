package entities;

/**
 * The model of a person who has a name and a telephone number, 
 * where the telephone number can be changed but not the name.
 */
public class Person 
{
	/**
	 * The name of the person.
	 */
	private String name;
	
	/**
	 * The telephone number of the person.
	 */
	private String telephoneNumber;
	
	/**
	 * Initialize an instance with the given name and telephone number.
	 * @param name     the name for the person
	 * @param number   the telephone number for the person
	 */
	public Person(String name, String number)
	{
		this.name = name;
		this.telephoneNumber = number;
	}
	
	/**
	 * @return  the name of the person
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return  the telephone number of the person
	 */
	public String getTelephoneNumber()
	{
		return telephoneNumber;
	}
	
	/**
	 * Change the telephone number of the person.
	 * @param newNumber  the new telephone number for the person
	 */
	public void setTelephoneNumber(String newNumber)
	{
		telephoneNumber = newNumber;
	}
	
	/**
	 * @return a string representation of the person
	 */
	public String toString()
	{
		return "\nName: " + name + "   Telephone number: " + telephoneNumber + "\n";
	}
	
	/**
	 * A method to test the Person class.
	 */
	public static void main(String[] args)
	{
		int numErrors = 0;
		Person p = new Person("Pete", "249-5418");
		System.out.println("The person called Pete at 249-5418  is " + p);
		if (! p.getName().equals("Pete"))
		{
			System.out.println("The constructor or getName failed");
			numErrors++;
		}
		if (! p.getTelephoneNumber().equals("249-5418"))
		{
			System.out.println("The constructor or getTelephoneNumber failed");
			numErrors++;
		}
		p.setTelephoneNumber("966-1134");
		if (! p.getTelephoneNumber().equals("966-1134"))
		{
			System.out.println("setTelephoneNumber failed");
			numErrors++;
		}
		
		p = new Person("Mary", "975-6735");
		System.out.println("The person called Mary at 975-6735 is " + p);
		if (! p.getName().equals("Mary"))
		{
			System.out.println("The constructor or getName failed");
			numErrors++;
		}
		if (! p.getTelephoneNumber().equals("975-6735"))
		{
			System.out.println("The constructor or getTelephoneNumber failed");
			numErrors++;
		}
		p.setTelephoneNumber("845-7294");
		if (! p.getTelephoneNumber().equals("845-7294"))
		{
			System.out.println("setTelephoneNumber failed");	
			numErrors++;
		}
		System.out.println("The number of errors found in Person class is " + numErrors);
	}
}
