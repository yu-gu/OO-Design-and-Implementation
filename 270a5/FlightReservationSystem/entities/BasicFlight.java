package entities;

/**
 * A basic flight class with a unique number, a capacity, seats to store 
 * bookings of passengers, and a width  that specifies the number of seats per row.
 */
public class BasicFlight 
{
	/** 
	 * The unique number of this flight.
	 */
	private int number;
	
	/**
	 * The number of seats in a row of the flight.
	 */
	private int width;
	
	/**
	 * The container to store the bookings on this flight.  A booking is placed
	 * in the array location corresponding to the seat of the person.
	 */
	private Booking[] seats;
	
	/**
	 * Initialize the flight with the specified number, width and capacity.
	 * @param number    the unique number of the flight
	 * @param width     the number of seats in a row
	 * @param capacity  the total number of seats on the flight
	 */
	public BasicFlight(int number, int width, int capacity)
	{
		this.number = number;
		this.width = width;
		seats = new Booking[capacity];
	}
	
	/**
	 * @return  the unique number of this flight
	 */
	public int getNumber()
	{
		return number;
	}
	
	/**
	 * @return  the number of seats in a row
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * @return  the number of seats on the flight
	 */
	public int getCapacity()
	{
		return seats.length;
	}
	
	/**
	 * Place the booking in the container according the seat specified in the booking.
	 * @param b  the booking to be placed in the container, given its seat has been set
	 */
	public void setSeat(Booking b)
	{
		if (b.getSeat() == null)
			throw new RuntimeException("Cannot add a booking to a flight "
										+ "when its seat isn't set.");
		seats[indexFromSeat(b.getSeat())] = b;
	}
	
	/**
	 * @param seat  a seat of the flight that has been booked by a person
	 * @return  the person with the booking in the specified seat
	 */
	public Person getPerson(String seat)
	{
		return seats[indexFromSeat(seat)].getPerson();
	}
	
	/**
	 * @return is the specified seat valid and available?
	 */
	public boolean isSeatAvailable(String seat)
	{
		int index = 0;
		try
		{
			index = indexFromSeat(seat);
		} catch (Exception e)
		{
			return false;
		}
		if (index < 0 || index >= seats.length)
			return false;
		else
			return seats[index] == null;
	}
	
	/**
	 * @param seat  a specified seat on the flight
	 * @return      the array location that corresponds to the seat
	 */ 
	public int indexFromSeat(String seat)
	{
		char rowPosition = seat.charAt(seat.length()-1);
		int positionIndex = rowPosition - 'A';  // index of position in row
		int rowNum = Integer.parseInt(seat.substring(0, seat.length()-1));
		return (rowNum - 1)*width + positionIndex;
	}
	
	/**
	 * @param i   a specified location in the array
	 * @return    the seat that corresponds to the index
	 */
	public String seatFromIndex(int i)
	{
		int positionIndex = i%width;  // index of position in row
		int rowNum = i/width + 1;
		char rowPosition = (char)('A' + positionIndex);
		return "" + rowNum + rowPosition;
	}
	
	/**
	 * @return a String representation of the properties of the flight
	 */
	public String toString()
	{
		String result = "\nFlight " + number + " with capacity " + seats.length
						+ " and width " + width + " has passengers: ";
		for (int i = 0; i < seats.length; i++)
		{
			result = result + "\nseat " + seatFromIndex(i) + ": ";
			if (seats[i] != null)
				result = result + seats[i].getPerson().getName();
		}
		return result + "\n";
	}
	
	/**
	 * A method to test the class.
	 * @param args  not used
	 */
	public static void main(String[] args)
	{
		/* Test the conversion methods from seat to index. */
		BasicFlight f = new BasicFlight(513, 4, 60);
		if (f.indexFromSeat("1A") != 0)
				System.out.println("Incorrect conversion of 1A");
		if (f.indexFromSeat("1C") != 2)
			System.out.println("Incorrect conversion of 1C");
		if (f.indexFromSeat("15D") != 59)
			System.out.println("Incorrect conversion of 15D");
		if (f.indexFromSeat("10A") != 36)
			System.out.println("Incorrect conversion of 10A");
		if (f.indexFromSeat("10C") != 38)
			System.out.println("Incorrect conversion of 10C");
		
		/* Since indexFromSeat has been tested, it can be used to test seatFromIndex. */
		if (f.indexFromSeat(f.seatFromIndex(0)) != 0)
			System.out.println("Incorrect conversion of 0");
		if (f.indexFromSeat(f.seatFromIndex(3)) != 3)
			System.out.println("Incorrect conversion of 3");
		if (f.indexFromSeat(f.seatFromIndex(59)) != 59)
			System.out.println("Incorrect conversion of 59");
		if (f.indexFromSeat(f.seatFromIndex(44)) != 44)
			System.out.println("Incorrect conversion of 44");
		if (f.indexFromSeat(f.seatFromIndex(47)) != 47)
			System.out.println("Incorrect conversion of 47");
		
		/* Test the flight class with fewer seats. */
		f = new BasicFlight(513, 4, 12);
		if (f.getNumber() != 513)
			System.out.println("Problems with constructor or getNumber.");
		if (f.getWidth() != 4)
			System.out.println("Problems with constructor or getWidth.");
		if (f.seats.length != 12)
			System.out.println("Problems with constructor.");
		if (f.getCapacity() != 12)
			System.out.println("Problems with constructor or getCapacity.");
		Person p = new Person("Pete", "249-5418");
		Booking b = new Booking(p, f);
		b.setSeat("1A");
		f.setSeat(b);
		if (f.getPerson("1A") != p)
			System.out.println("Problems with setSeat or getPersonInSeat.");
		if (f.isSeatAvailable("1A"))
			System.out.println("Problems with isSeatAvailable.");
		if (!f.isSeatAvailable("1B"))
			System.out.println("Problems with isSeatAvailable.");
		if (!f.isSeatAvailable("2B"))
			System.out.println("Problems with isSeatAvailable.");
		if (f.isSeatAvailable(""))
			System.out.println("Problems with isSeatAvailable.");
		if (f.isSeatAvailable("22"))
			System.out.println("Problems with isSeatAvailable.");
		if (f.isSeatAvailable("2a"))
			System.out.println("Problems with isSeatAvailable.");
		if (f.isSeatAvailable("AA"))
			System.out.println("Problems with isSeatAvailable.");
		if (f.isSeatAvailable("2K"))
			System.out.println("Problems with isSeatAvailable.");
		if (f.isSeatAvailable("222B"))
			System.out.println("Problems with isSeatAvailable.");
		p = new Person("Sue", "775-9285");
		b = new Booking(p, f);
		b.setSeat("2B");
		f.setSeat(b);
		if (f.getPerson("2B") != p)
			System.out.println("Problems with setSeat or getPersonInSeat.");
		if (f.isSeatAvailable("2B"))
			System.out.println("Problems with isSeatAvailable.");
		p = new Person("Tom", "346-2917");
		b = new Booking(p, f);
		b.setSeat("3D");
		f.setSeat(b);
		System.out.println("The flight 513 (capacity 12 and width 4) "
							+ "with Pete in seat 1A, Sue in 2B, and Tom in 3D is \n" + f);		
	}
}
