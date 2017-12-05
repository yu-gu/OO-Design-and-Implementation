//Name:YU GU Student#:11195367 NSID:yug242 Course: 01 Tutorial:T03w
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * A container of Passenger that stores all booking of certain passenger,
 * where the bookings and seat on certain flight could be checked
 * */
public class Passenger extends Person{
	/**a linked list the contains bookings of a certain passenger*/
	LinkedList<Booking> PassBooking;

	/**
	 * Initialize the Passenger class with specific
	 *  Passenger name and Phone Number
	 *  @param name - the name of a passenger
	 *  @param number -  the phone number of a passenger
	 * */
	public Passenger(String name,String number){
		super(name,number);
		PassBooking = new LinkedList<Booking>();
	}
	
	/**
	 * Mutator to add bookings into the linked list
	 * @param b - the booking that 
	 * @precond b != null
	 * */
	public void addBooking(Booking b){
		if (b==null){
			throw new RuntimeException("Booking cannot be null");
		}
		
		PassBooking.addFirst(b);
	}
	/**get the Booking type linked list
	 * @return PassBooking
	 * */
	public LinkedList<Booking> getLinkedList(){
		return PassBooking;
	}
	
	/**
	 * Check whether a passenger has booking on the flight
	 * @precond f != null
	 * @param f - the Flight type variable
	 * @return true/false - true if a flight has bookings on it, false otherwise
	 * */
	public boolean hasBooking(Flight f){
		if (f==null){
			throw new RuntimeException("Cannot check if a flight has booking "
					+ "when the flight doe not exist");
		}
		ListIterator<Booking> iter = PassBooking.listIterator();
		while(iter.hasNext()){
			Booking booking = iter.next();
			if(booking.getFlight().equals(f)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check whether a passenger has seat reserved on the flight
	 * @precond f != null
	 * @param f - type flight variable 
	 * @return true/false - true if the flight has seat on it, false otherwise
	 * */
	public boolean hasSeat(Flight f){
		if (f==null){
			throw new RuntimeException("Cannot check if a flight has seat "
					+ "when the flight doe not exist");
		}
		ListIterator<Booking> iter = PassBooking.listIterator();
		while (iter.hasNext()){
			if (iter.next().getFlight() == f){
				return true;
			}
		}
			return false;
	}
	
	/**a String representation of passenger information
	 * @return a string representation of passenger information
	 *  */
	public String toString(){
		String booking_string = "Name: " + this.getName() + "Telephone: " + this.getTelephoneNumber() + "\n" + "All bookings:\n";
		ListIterator<Booking> it = PassBooking.listIterator();
		while(it.hasNext()){
			Booking booking = it.next();
			String a_booking_flight = "Flight number: " + String.valueOf(booking.getFlight().getNumber());
			String a_booking_seat;
			if(booking.getSeat() != null){
				a_booking_seat = "\tSeat number: " + booking.getSeat() + "\n";
			}
			else{
				a_booking_seat = "The passenger have not book a seat" + "\n";
			}
			booking_string = booking_string  + a_booking_flight + a_booking_seat ;
		}
		return booking_string;
	}
	
	/**a method to test Passenger class */
	public static void main(String[] args){
		Passenger p = new Passenger("Bob","123");
		Flight f= new Flight(123,4,30);
		Booking b = new Booking(p,f);

		p.addBooking(b);
		int numErrors = 0;
		if (p.getName().equals("Bob")) {
            System.out.println("The constructor succeed");
		}else{
            numErrors++;
        }
		if (p.getLinkedList().isEmpty()){
			System.out.println("getLinkedList() method failed");
			numErrors++;
		}
		if (p.hasBooking(f)){
			System.out.println("hasBooking() Running succeed");
			}
		else{
			System.out.println("hasBooking method failed");
			numErrors++;
		}
		
		if(p.hasSeat(f)){
			System.out.println("hasSeat() Running succeed");
			}
		else{
			System.out.println("The hasSeat method failed");
            numErrors++;
		}
		System.out.println(p.toString());
		
		System.out.println("The number of errors found in Person class is "
                + numErrors);
	}
}