/**
 * A basic flight class with a unique number, a capacity, seats to store 
 * Bookings of passengers, and a width that specifies the number of seats per row.
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
	 * The container to store the Bookings on this flight.  A Booking is placed
	 * in the array location corresponding to the seat of the Person.
	 */
	private Booking[] seats;
	
	/**
	 * Initialize the flight with the specified number, width and capacity.
	 * @param flightNumber    the unique number of the flight
	 * @param flightWidth     the number of seats in a row
	 * @param flightCapacity  the total number of seats on the flight
	 */
	public BasicFlight(int flightNumber, int flightWidth, int flightCapacity)
	{
		number = flightNumber;
		width = flightWidth;
		seats = new Booking[flightCapacity];
		
				
	}
	
	/**
	 * gets the flight number of this flight
	 * @return  the unique number of this flight
	 */
	public int getNumber()
	{
		return number;
	}
	
	/**
	 * gets the number of seats in a row
	 * @return  the number of seats in a row
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * gets the number of seats on the flight
	 * @return  the number of seats on the flight
	 */
	public int getCapacity()
	{
		return seats.length;
	}
	
	/**
	 * Place the booking in the container according the seat specified in the booking,
	 * as long as the seat of the parameterized Booking is set.
	 * @param theBooking  the booking to be placed in the container, given its seat has been set
	 */
	public void setSeat(Booking theBooking)
	{
		if (theBooking.getSeat() == null)
			throw new RuntimeException("Cannot add a booking to a flight "
										+ "when its seat isn't set.");
		seats[indexFromSeat(theBooking.getSeat())] = theBooking;
	}
	
	/**
	 * Gets the Person at a specific seat of the flight
	 * @param seat  a seat of the flight that has been booked by a Person
	 * @return  the Person with the booking in the specified seat 	
	 */
	public Person getPerson(String seat)
	{
		return seats[indexFromSeat(seat)].getPerson();
	}
	
	/**
	 * Decides if the seat is valid and available
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
	 * @return      the array location that corresponds to the seat, 
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
		int numErrors = 0;
		BasicFlight f = new BasicFlight(513, 4, 60);
		if (f.indexFromSeat("1A") != 0){
				System.out.println("Incorrect conversion of 1A");
				numErrors++;
		}
		if (f.indexFromSeat("1C") != 2){
			System.out.println("Incorrect conversion of 1C");
			numErrors++;
		}
		if (f.indexFromSeat("15D") != 59){
			System.out.println("Incorrect conversion of 15D");
			numErrors++;
		}
		if (f.indexFromSeat("10A") != 36){
			System.out.println("Incorrect conversion of 10A");
			numErrors++;
		}
		if (f.indexFromSeat("10C") != 38){
			System.out.println("Incorrect conversion of 10C");
			numErrors++;
		}
		
		/* Since indexFromSeat has been tested, it can be used to test seatFromIndex. */
		if (f.indexFromSeat(f.seatFromIndex(0)) != 0){
			System.out.println("Incorrect conversion of 0");
			numErrors++;
		}
		if (f.indexFromSeat(f.seatFromIndex(3)) != 3){
			System.out.println("Incorrect conversion of 3");
			numErrors++;
		}
		if (f.indexFromSeat(f.seatFromIndex(59)) != 59){
			System.out.println("Incorrect conversion of 59");
			numErrors++;
		}
		if (f.indexFromSeat(f.seatFromIndex(44)) != 44){
			System.out.println("Incorrect conversion of 44");
			numErrors++;
		}
		if (f.indexFromSeat(f.seatFromIndex(47)) != 47){
			System.out.println("Incorrect conversion of 47");
			numErrors++;
		}
		
		/* Test the flight class with fewer seats. */
		f = new BasicFlight(513, 4, 12);
		if (f.getNumber() != 513){
			System.out.println("Problems with constructor or getNumber.");
			numErrors++;
		}
		if (f.getWidth() != 4){
			System.out.println("Problems with constructor or getWidth.");
			numErrors++;
		}
		if (f.seats.length != 12){
			System.out.println("Problems with constructor.");
			numErrors++;
		}
		if (f.getCapacity() != 12){
			System.out.println("Problems with constructor or getCapacity.");
			numErrors++;
		}
		Person p = new Person("Pete", "249-5418");
		Booking b = new Booking(p, f);
		b.setSeat("1A");
		f.setSeat(b);
		if (f.getPerson("1A") != p){
			System.out.println("Problems with setSeat or getPersonInSeat.");
			numErrors++;
		}
		if (f.isSeatAvailable("1A")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		if (!f.isSeatAvailable("1B")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		if (!f.isSeatAvailable("2B")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		if (f.isSeatAvailable("")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		if (f.isSeatAvailable("22")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		if (f.isSeatAvailable("2a")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		if (f.isSeatAvailable("AA")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		if (f.isSeatAvailable("2K")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		if (f.isSeatAvailable("222B")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		p = new Person("Sue", "775-9285");
		b = new Booking(p, f);
		b.setSeat("2B");
		f.setSeat(b);
		if (f.getPerson("2B") != p){
			System.out.println("Problems with setSeat or getPersonInSeat.");
			numErrors++;
		}
		if (f.isSeatAvailable("2B")){
			System.out.println("Problems with isSeatAvailable.");
			numErrors++;
		}
		p = new Person("Tom", "346-2917");
		b = new Booking(p, f);
		b.setSeat("3D");
		f.setSeat(b);
		System.out.println("The flight 513 (capacity 12 and width 4) "
							+ "with Pete in seat 1A, Sue in 2B, and Tom in 3D is \n" + f);
		
		System.out.println("The number of errors found in BasicFlight class is "
                + numErrors);
		
	}
}
 