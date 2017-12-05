package entities;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * A Person with a list of bookings for the person.
 */
public class Passenger extends Person 
{
	/**
	 * A list of the bookings for the passenger.
	 */
	private LinkedList<Booking> bookings;
	
	/**
	 * Initialize an instance with the specified name and telephone number.
	 * @param name    the name for the passenger
	 * @param number  the telephone number for the passenger
	 * @precond name != null && !name.equals("")
	 */
	public Passenger(String name, String number)
	{
		super(name, number);     // must invoke super first; before the precondition check
		if (name == null || name.equals(""))
			throw new RuntimeException("The name for a passenger must be a non-empty string.");
		bookings = new LinkedList<Booking>();
	}
	
	/**
	 * Add a booking to the list of bookings for this passenger.
	 * @param b  the booking to add for this passenger
	 * @precond  !hasFlightBooking(b.getFlight())
	 */
	public void addBooking(Booking b)
	{
		if (hasBookingOn((Flight) b.getFlight()))
			throw new RuntimeException("A Passenger cannot have two bookings " +
					"for the same flight.");
		bookings.addFirst(b);
	}
	
	/**
	 * @param f  the flight to check if the passenger has a booking for it
	 * @return does this passenger already have a booking for flight f?
	 */
	public boolean hasBookingOn(Flight f)
	{
		for (Booking b : bookings)
			if (b.getFlight() == f)
				return true;
		return false;
	}
	
	/**
	 * @param f  the flight to check if the passenger has a seat booked on it
	 * @return does this passenger already have a seat on flight f?
	 */
	public boolean hasSeatOn(Flight f)
	{
		for (Booking b : bookings)
			if (b.getFlight() == f)
				return b.getSeat() != null;
		return false;
	}
	
	/**
	 * @return the String representation of the passenger,
	 *         including the flights for which the passenger has a booking.
	 */
	@Override
	public String toString()
	{
		String result = super.toString() + "With bookings:";
		Iterator<Booking> iter = bookings.iterator();
		while (iter.hasNext())
		{
			Booking b = iter.next();
			result = result + "\nFlight: " + b.getFlight().getNumber();
			if (b.getSeat() != null)
				result = result + " in seat " + b.getSeat();
		}
		return result + "\n";
	}
	
	/**
	 * A simple method to test the class.
	 * @param args  not used
	 */
	public static void main(String[] args)
	{
		Flight f = new Flight(513, 4, 12);
		Passenger p = new Passenger("Pete", "249-5418");
		f.makeBooking(p);
		if (! p.hasBookingOn(f))
			System.out.println("Didn't find the booking for Pete on flight 513");
		f.assignSeat(p, "1A");
		if (! p.hasSeatOn(f))
			System.out.println("Didn't find the seat for Pete on flight 513");
		f = new Flight(727, 3, 21);
		f.makeBooking(p);
		if (! p.hasBookingOn(f))
			System.out.println("Didn't find the booking for Pete on flight 727");
		f = new Flight(678, 2, 12);
		f.makeBooking(p);
		if (! p.hasBookingOn(f))
			System.out.println("Didn't find the booking for Pete on flight 678");
		System.out.println("The information for Pete with telephone number 249-5418, " +
				"seat 1A on flight 513, and booked on flights 727 and 678 is \n"
				+ p);
	}
}
