//Name:YU GU Student#:11195367 NSID:yug242 Course: 01 Tutorial:T03
/**
 * Booking class represent booking of a person on a flight,
 * it also record the seat assigned to the person on the flight
 * */
public class Booking{
	private Person person;		/***/
	private BasicFlight flight;	/**field used to store flight information*/
	private String seat;		/**field used to store seat number*/
	
	/**
	 * constructor to initialize all fields declared in this class
	 * @param newPerson - a Person class variable
	 * @param newBasicFlight - a BasicFlight type variable
	 */
	public Booking(Person newPerson, BasicFlight newBasicFlight){
		person=newPerson;
		flight = newBasicFlight;
		seat = "";	
	}
	/**
	 * Accessor method to get the type person variable
	 * @return person
	 * 
	 * */
	public Person getPerson(){
		return person;
	}
	/**
	 * Accessor method to get type BasicFlight variable
	 * @return flight
	 * */
	public BasicFlight getFlight(){
		return flight;
	}
	/**
	 * Accessor method to get seat number in string
	 * @return seat
	 * */
	public String getSeat(){
		return seat;
	}
	/**
	 * Mutator method to set seat being booked in string
	 * @param seatIn - seat number in string
	 * */
	public void setSeat(String seatIn){
		seat = seatIn;
	}
	/**method to transfer all outputs made by methods 
	 * in class Booking to string type 
	 * @return str
	 * */
	public String toString(){
		String str = "Name: "+this.getPerson().getName() 
					+ " "+this.getFlight().getNumber() + " " + this.getSeat();
		return str;
	}
	/**	 
	 * Main method to test all functions in Booking class
	 * @param args[]
	 * */
	public static void main(String[] args){
		Person x1 = new Person("Smith","306-1234"); 
		BasicFlight y1= new BasicFlight(1234,4,20);	
		Booking boo1 = new Booking (x1,y1);//testing Booking constructor
		boo1.setSeat("2B"); 				//testing setSeat method in Booking class
		System.out.println(boo1.getPerson().getName());//testing getPerson method
		System.out.println(boo1.getPerson().getPhoneNumber());
		System.out.println(boo1.getFlight().getNumber());//testing getFlight method
		System.out.println(boo1.getSeat());//testing getSeat method
		y1.setSeat(boo1);					//testing setSeat method in BasicFlight class
		System.out.println(boo1.toString());//testing toString method
	}
	
}
