package entities;

/** 
 * This is the class to record the information for a Person booking
 * a BasicFlight.  In addition to recording the Person and the BasicFlight,
 * it stores the seat, if any, that has been assigned to the Person.
 */
public class Booking 
{
	/**
	 * The person with this booking.
	 */
	private Person person;
	
	/**
	 * The flight for this booking.
	 */
	private BasicFlight flight;
	
	/**
	 * The seat that has been assigned to this person, if one,
	 * or else null.
	 */
	private String seat;
	
	/**
	 * Initialize the booking with the prescribed person and flight.
	 * @param person  the person for the booking
	 * @param flight  the flight for the booking
	 */
	public Booking(Person person, BasicFlight flight)
	{
		this.person = person;
		this.flight = flight;
		seat = null;
	}
	
	/**
	 * @return  the flight of this booking
	 */
	public BasicFlight getFlight()
	{
		return flight;
	}

	/**
	 * @return  the person of this booking
	 */
	public Person getPerson()
	{
		return person;
	}

	/**
	 * @return  the seat assigned to the person of this booking
	 */
	public String getSeat()
	{
		return seat;
	}

	/** 
	 * Assign the person the specified seat on this flight.
	 * @param newSeat  the seat for the person
	 */
	public void setSeat(String newSeat)
	{
		seat = newSeat;
	}

	/**
	 * @return a string representation of the information of this booking
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
		Person p = new Person("Pete", "249-5418");
		BasicFlight f = new BasicFlight(513, 4, 12);
		Booking b = new Booking(p, f);
		if (b.getFlight() != f)
			System.out.println("Problems with constructor or getFlght.");
		if (b.getPerson() != p)
			System.out.println("Problems with constructor or getPerson.");
		if (b.getSeat() != null)
			System.out.println("Problems with constructor or getSeat.");
		b.setSeat("5A");
		if (! b.getSeat().equals("5A"))
			System.out.println("Problems with setSeat.");
		System.out.println("The information for the booking of Pete "
							+ "in seat 5A on flight 513 is \n" + b);
	}
}
