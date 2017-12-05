//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
/**
 * Person class models a person, storing his/her name and phone number 
 * and also enable change to phone number
 */
public class Person {
	private String Name;		/**field to store passenger name*/
	private String PhoneNumber;	/**field to store phone number of passenger*/
	
	/**
	 * constructor of Person class,initialize all fields declared
	 * @param NameInput - name of person
	 * @param PhoneInput - phone number of person
	 */
	public Person(String NameInput, String PhoneInput){
		Name=NameInput;			
		PhoneNumber=PhoneInput;	
	}
	
	/**
	 * accessor method to get name of person
	 * @return Name
	 */
	public String getName(){
		return Name;
	}
	
	/**
	 * accessor method to get phone number of the person
	 * @return PhoneNumber
	 */
	public String getPhoneNumber(){
		return PhoneNumber;
	}
	/**
	 * mutator method to change phone number of person  
	 * @param PhoneNumberIn - Phone number of person
	 * */
	public void setPhoneNumber(String PhoneNumberIn){
		PhoneNumber = PhoneNumberIn;
		}

	/** 
	 * method to obtain all information about Person
	 * @return p
	 */
	public String toString(){
		String p = "Name: " +Name + 
				" PhoneNumber: " + PhoneNumber;
		return p;
	}
	
	
	/**
	 * Main method for testing all functions in Person class
	 * @param args[]
	 */
		public static void main(String[] args){
		Person x = new Person("James Gosling","123-4567");//test constructor
		System.out.println(x.getName());   		//test getName() method,should output name in constructor
		System.out.println(x.getPhoneNumber()); //test getPhoneNumber() method,should output number in constructor
		x.setPhoneNumber("567-890");			//test setPhoneNumber() method,should output number in brace
		System.out.println(x.toString());		//test toString() method
	}

}