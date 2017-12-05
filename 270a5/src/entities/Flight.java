package entities;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * A BasicFlight that keeps a list of Bookings that have not yet been assigned a seat.
 * A Booking can be made for a first-class passenger (with a seat and meal plan),
 * and a passenger with no assigned seat.  A passenger with no seat can be assigned a seat.
 */
public class Flight extends BasicFlight 
{
	/**
	 * The list of bookings that have not yet been assigned a seat.
	 */
	private LinkedList<Booking> waitingSeatList;
	
	/**
	 * The total number of bookings for this flight.
	 */
	private int numberBooked;
	
	/**
	 * Initialize an instance with the specified number, width and capacity.
	 * @param number the flight number for the flight
	 * @param width  the width (number of seats per row) of the flight
	 * @param capacity the total number of seats
	 * @precond number > 0 && capacity > 0 && width > 0 && width <= capacity
	 */
	public Flight(int number, int width, int capacity)
	{
		super(number, width, capacity);
		if (number <= 0)
			throw new RuntimeException("Flight number must be positive. " +
					"It cannot be " + number + ".");
		if (capacity <= 0)
			throw new RuntimeException("Flight capacity must be positive."+
					"It cannot be " + capacity + ".");
		if (width <= 0 || width > capacity)
			throw new RuntimeException("Flight width must be positive and no larger " +
					"than the capacity. Its value is " + width +
					" which must be positive and <= " + capacity + ".");
		
		waitingSeatList = new LinkedList<Booking>();
		numberBooked = 0;
	}

	/**
	 * @return a list of the Passengers on the flight waiting for a seat.
	 */
	public LinkedList<Passenger> getWaitingList()
	{
		// Make a shallow clone so that the client cannot change the list.
		LinkedList<Passenger> result = new LinkedList<Passenger>();
		for (Booking b: waitingSeatList)
			result.addLast((Passenger) b.getPerson());
		return result;
	}
	
	/**
	 * Create a booking for a regular passenger with no seat specified.
	 * @param p    the passenger for whom a booking is to be made
	 * @precond p != null && !p.hasBookingOn(this) && remainingCapacity() > 0
	 */
	public void makeBooking(Passenger p)
	{
		if (p == null)
			throw new RuntimeException("Cannot book a flight for a null passenger.");
		if (p.hasBookingOn(this))
			throw new RuntimeException("Cannot book a Person twice on a Flight.");
		if (remainingCapacity() <= 0)
			throw new RuntimeException("Cannot make a booking on a full flight.");
		
		Booking b = new Booking(p, this);
		waitingSeatList.addLast(b);
		p.addBooking(b);
		numberBooked = numberBooked + 1;
	}
	
	/**
	 * Book a seat for a first-class passenger.
	 * @param p    the passenger for whom a booking is to be made
	 * @param seat the seat for the passenger
	 * @param meal the meal plan for the passenger
	 * @precond p != null && !p.hasBookingOn(this) && isSeatAvailable(seat)
	 */
	public void makeFirstClassBooking(Passenger p, String seat, String meal)
	{
		if (p == null)
			throw new RuntimeException("Cannot book a flight for a null passenger.");
		if (p.hasBookingOn(this))
			throw new RuntimeException("Cannot book a Person twice on a Flight.");
		if (! isSeatAvailable(seat))
			throw new RuntimeException("Seat " + seat + " is invalid or already occupied " +
					"so cannot book it.");
		
		Booking b = new FirstClassBooking(p, this, seat, meal);
		setSeat(b);
		p.addBooking(b);
		numberBooked = numberBooked + 1;
	}
	
	/**
	 * Assign passenger p who has a booking but no seat to the specified seat. 
	 * @param  p  the passenger to be given a seat
	 * @param seat the seat for the passenger
	 * @precond p != null && isSeatAvailable(seat) 
	 *          && p.hasBookingOn(this) && !p.hasSeatOn(this)
	 */
	public void assignSeat(Passenger p, String seat)
	{
		if (p == null)
			throw new RuntimeException("Cannot assign a seat for a null passenger.");
		if (! isSeatAvailable(seat))
			throw new RuntimeException("Seat " + seat + " is invalid or already occupied " +
					"so cannot book it.");
		if (p.hasSeatOn(this))
			throw new RuntimeException(p.getName() + " already has a seat on flight " +
					getNumber() + " so cannot book another one.");
		
		Iterator<Booking> waitingIter = waitingSeatList.iterator();
		while (waitingIter.hasNext())
		{
			Booking curBooking = waitingIter.next();
			if (curBooking.getPerson() == p)
			{
				waitingIter.remove();
				curBooking.setSeat(seat);
				setSeat(curBooking);
				return;
			}
		}
		throw new RuntimeException("Cannot assign a seat if not already booked and waiting.");
	}

	/**
	 * @param seat  the seat for which the passenger is to be obtained
	 * @return  the Passenger in the specified seat
	 * @precond !isSeatAvailable(seat)
	 */
	@Override
	public Passenger getPerson(String seat)
	{
		/* It would be better to have a test for a valid seat separate from the
		 * test for the seat being empty.  */
		if (isSeatAvailable(seat))
			throw new RuntimeException("Cannot obtain a passenger from seat " 
					+ seat + " if the seat is invalid or not occupied ");
		
		return (Passenger) super.getPerson(seat); // only Passengers are put in seats
	}
	
	/**
	 * @return  the number of bookings that can still be added to the flight. 
	 */
	public int remainingCapacity()
	{
		return getCapacity() - numberBooked;
	}
	
	/**
	 * @return a String with a header and a diagram showing the available seats on the flight.
	 */
	public String availableSeats()
	{
		String result = "The available seats are \n";
		int numRows = (int) Math.ceil(((double) getCapacity())/getWidth());
		int i = 0;
		for (int row = 1; row <= numRows; row++)
		{
			for (int rowIndex = 0; rowIndex < getWidth() && i < getCapacity(); rowIndex++)
			{
				String seat = String.valueOf(row) + (char)('A' + rowIndex); // i.e., seatFromIndex(i);
				if (isSeatAvailable(seat))
					if (seat.length() == 2)
						result = result + "  " + seat;
					else  //seat.length() == 3
						result = result + " " + seat;
				else
					result = result + "    ";
				i = i + 1;
			}
			result = result + "\n";
		}
		return result;
	}
	
	/**
	 * @return a String representation of the properties of the flight, including 
	 *         a list of bookings with no seat and the bookings already in a seat.
	 */
	@Override
	public String toString()
	{
		String result = super.toString() + "Passengers without seats: ";
		Iterator<Booking> iter = waitingSeatList.iterator();
		while (iter.hasNext())
			result = result + iter.next().getPerson().getName() + " ";;
		return result + "\n";
	}
	
	/**
	 * A simple method to test the class.
	 * @param args  not used
	 */
	public static void main(String[] args)
	{
		Flight f = new Flight(513, 4, 13);
		Passenger p = new Passenger("Pete", "249-5418");
		f.makeBooking(p);
		f.assignSeat(p, "1A");
		p = new Passenger("Sue", "775-9285");
		f.makeFirstClassBooking(p, "2B", "shrimp");
		p = new Passenger("Tom", "346-2917");
		f.makeBooking(p);
		p = new Passenger("Mary", "975-6735");
		Passenger mary = p;
		f.makeBooking(p);
		p = new Passenger("Bill", "775-4184");
		f.makeBooking(p);
		System.out.println("The passenger Mary at 975-6735 who is booked on flight 513, " 
		           + "but is waiting for a seat is \n" + mary);
		f.assignSeat(mary, "3D");
		p = f.getPerson("3D");
		System.out.println("The passenger Mary at 975-6735 in seat 3D of flight 513, " 
		           + " is \n" + p);
		System.out.println("The flight 513 with width 4 and capacity 13, " 
				           + "has Pete in seat 1A, Sue in 2B, "
						   + "\n     Mary in 3D, and Tom and Bill waiting for a seat is \n\n" + f);
		System.out.println("The list of passengers with no seat is \n" + f.getWaitingList());
		System.out.println("\nThere is room for " + f.remainingCapacity() + " more bookings.\n" 
				           + f.availableSeats());
	}
}
