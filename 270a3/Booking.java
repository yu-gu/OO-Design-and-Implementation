//Name:YU GU Student#:11195367 NSID:yug242 Course: 01 Tutorial:T03s
/** 
 * This is the class to record the information for a Person booking
 * a BasicFlight.  In addition to recording the Person and the BasicFlight,
 * it stores the seat, if any, that has been assigned to the Person.
 */
public class Booking 
{
	/**
	 * The Person with this Booking.
	 */
	private Person person;
	
	/**
	 * The flight for this Booking.
	 */
	private BasicFlight flight;
	
	/**
	 * The seat that has been assigned to this person, if one,
	 * or else null.
	 */
	private String seat;
	
	/**
	 * Initialize the Booking with the prescribed Person and flight.
	 * @param thePerson  the Person for the Booking
	 * @param theFlight  the flight for the Booking
	 */
	public Booking(Person thePerson, BasicFlight theFlight)
	{
		person = thePerson;
		flight = theFlight;
		seat = null;
	}
	
	/**
	 * gets the flight of this Booking
	 * @return  the flight of this Booking
	 */
	public BasicFlight getFlight()
	{
		return flight;
	}

	/**
	 * gets the Person of this Booking
	 * @return  the Person of this Booking
	 */
	public Person getPerson()
	{
		return person;
	}

	/**
	 * gets the seat of this Booking, which is null if it has not been set
	 * @return  the seat assigned to the person of this booking
	 */
	public String getSeat()
	{
		return seat;
	}

	/** 
	 * Assign the Person the specified seat on this flight.
	 * @param newSeat  the seat for the person
	 */
	public void setSeat(String newSeat)
	{
		seat = newSeat;
	}

	/**
	 * Gets a String representation of the Booking information
	 * @return a String representation of the information of this Booking
	 */
	public String toString()
	{
		String result = "\n" + person.getName() + " is on flight " + flight.getNumber();
		if (seat != null)
			result = result + " in seat " + seat;
		return result + "\n";
	}
	
	/**
	 * A method to test the Booking class.
	 */
	public static void main(String[] args)
	{
		int numErrors = 0;
  
		Person p = new Person("Pete", "249-5418");
		BasicFlight f = new BasicFlight(513, 4, 12);
		Booking b = new Booking(p, f);
		if (b.getFlight() != f){
			System.out.println("Problems with constructor or getFlight.");
			numErrors++;
		}
		if (b.getPerson() != p){
			System.out.println("Problems with constructor or getPerson.");
			numErrors++;
		}
		if (b.getSeat() != null){
			System.out.println("Problems with constructor or getSeat.");
			numErrors++;
		}
		b.setSeat("5A");
		if (! b.getSeat().equals("5A")){
			System.out.println("Problems with setSeat.");
			numErrors++;
		}
		System.out.println("The information for the booking of Pete "
							+ "in seat 5A on flight 513 is \n" + b);
		System.out.println("The number of errors found in Booking class is "
                + numErrors);
	}
}
