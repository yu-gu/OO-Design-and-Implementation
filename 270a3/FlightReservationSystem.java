//Name:YU GU Student#:11195367 NSID:yug242 Course: 01 Tutorial:T03
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
/**
 * A container that stores passengers, flights. In addition to add passengers 
 * flights to the system, and add bookings including refgular and first class
 * bookings. And make a control of these.
 * @author yugu
 */
public class FlightReservationSystem {
	/**
	 * A map to store all passenger elements in the reservation system 
	 */
	Map<String,Passenger> PassengerMap;
	/**
	 * A map to store all flight elements in the reservation system
	 */
	Map<Integer,Flight> FlightMap;
	

	/**
	 * Initialize the HashMap that stores passengers and flights needed
	 * to be reserved in the flight reservation system
	 */
	public FlightReservationSystem(){
		PassengerMap = new HashMap<String,Passenger>();
		FlightMap = new HashMap<Integer,Flight>();
	}
	/**
	 * A method to add passenger to the system
	 * @precond p!=null
	 * @param p - a passenger variable
	 */
	public void addPassenger(Passenger p){
		if(p==null){
			throw new RuntimeException("Passenger cannot be empty");
		}
		PassengerMap.put(p.getName(), p);
	}
	/**
	 * A method to add flight to the system
	 * @precond f!=null
	 * @param f
	 */
	public void addFlight(Flight f){
		if (f==null){
			throw new RuntimeException("Flight variable cannot be empty");
		}
		FlightMap.put(f.getNumber(), f);
	}
	/**
	 * A method to make first class booking for certain passenger
	 * with certain flight. Distinguish from regular bookings with 
	 * a meal plan choice
	 * @precond p!=null
	 * @precond f!=null
	 * @precond seat!=null
	 * @param p
	 * @param f
	 * @param meal
	 */
	public void FirstClassBooking(Passenger p, Flight f,String meal,String seat){
		if (p==null){
			throw new RuntimeException("Passenger cannot be empty");
		}
		if (f==null){
			throw new RuntimeException("Flight cannot be empty");
		}
		if(seat == null){
			throw new RuntimeException("Must choose a seat as first class booking");
		}
		
		f.setFirstBooking(p,meal,seat);
	}
	/**
	 * A method to make regular booking for certain passenger with 
	 * certain flight
	 * @precond p!=null
	 * @precond f!=null
	 * @param p
	 * @param f
	 */
	public void RegularBooking(Passenger p, Flight f){
		if (p==null){
			throw new RuntimeException("Passenger cannot be empty");
		}
		if (f==null){
			throw new RuntimeException("Flight cannot be empty");
		}
		
		f.setRegularBooking(p);
	}
	/**
	 * A mutator method to assign seat to a booking given the person name,
	 * flight number, and the seat in string format
	 * @precond name != null
	 * @precond flightnum != null
	 * @precond seat != null
	 * @precond the passenger must have booking on certain flight
	 * @precond the seat index cannot exceed capacity of the flight
	 * @param name
	 * @param flightnum
	 * @param seat
	 */
	public void assignSeat(String name,int flightnum,String seat){
		if(PassengerMap.get(name) == null ){
			throw new RuntimeException("Cannot assign seat to a passenger when it does not exist in Passenger list.");
		}
		if(FlightMap.get(flightnum) == null){
			throw new RuntimeException("Cannot assign seat to a passenger when flight number does not exist in flight list.");
		}
		if (PassengerMap.get(name).hasBooking(FlightMap.get(flightnum)) != true){
			throw new RuntimeException("Cannot assign seat to a passenger who has not had a booking on a flight.");
		}
		if (FlightMap.get(flightnum).indexFromSeat(seat)>=FlightMap.get(flightnum).getCapacity()){
			throw new RuntimeException("seat number exceeds caoacity of this flight");
		}
		
		if (FlightMap.get(flightnum).isSeatAvailable(seat)){
			throw new RuntimeException("This seat is not available");
		}
		
		Person p = (PassengerMap.get(name));
		Flight f = (FlightMap.get(flightnum));
		Booking b = new Booking(p,f);
		b.setSeat(seat);
		f.setSeat(b);
	}
	/**
	 * A method to display a list of all empty seats of a certain flight in
	 * the flight reservation system
	 * @precond f != null
	 * @param f
	 */
	public void displayEmptySeat(Flight f){
		if (f==null){
			throw new RuntimeException("Flight cannot be empty");
		}
		System.out.println(f.EmptySeat());
	}
	/**
	 * A method to display a list of all passengers being created in the system
	 */
	public void displayPassenger(){
		String info = "Passenger list: ";
		for(String key: PassengerMap.keySet()){
			info = info + "Name: " 
					+ PassengerMap.get(key).getName() + " Phone: " 
					+ PassengerMap.get(key).getTelephoneNumber() + "\n";
		}
		System.out.println(info);
	} 
	/**
	 * A method to display a list of all flights being created in the system
	 */
	public void displayFlight(){
		String info = "Flight list: \n";
		for(Integer key: FlightMap.keySet()){
			info = info + "Number: " 
			+ Integer.toString(FlightMap.get(key).getNumber()) 
			+" Width: " + Integer.toString(FlightMap.get(key).getWidth()) 
			+ " Capacity: " + Integer.toString(FlightMap.get(key).getCapacity())
			+"\n";
		}
		System.out.println(info);
	}
	
	/**
	 * a method to test FlightReservationSystem class
	 * @param args
	 */
	public static void main(String[] args) {
		FlightReservationSystem sys = new FlightReservationSystem();
		Scanner in1 = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		Scanner in3 = new Scanner(System.in);
		Scanner in4 = new Scanner(System.in);
		int OpCode=0;
		System.out.println("operation number: ");
		OpCode = in1.nextInt();
		while(true){
			if(OpCode == 1){
				System.out.println("current persons: ");
				sys.displayPassenger();
				System.out.println("current flights: ");
				sys.displayFlight();
				in1.close();
				in2.close();
				in3.close();
				in4.close();
				break;
			}	
			else if(OpCode == 2){
				System.out.print("name: ");
				String name = in1.next();
				System.out.print("phone number: ");
				String phone = in2.next();
				Passenger passenger = new Passenger(name,phone);
				sys.addPassenger(passenger);
				System.out.println("Success");
			}
			else if(OpCode == 3){
				System.out.print("Flight number:");
				int number = in1.nextInt();
				System.out.print("Flight width:");
				int width = in2.nextInt();
				System.out.print("Flight capacity:");
				int capacity = in3.nextInt();
				Flight f = new Flight(number,width,capacity);
				sys.addFlight(f);
				System.out.println("Success");
			}
			else if(OpCode == 4){
				System.out.println("Flight number: ");
				int number = in1.nextInt();
				Flight flight = sys.FlightMap.get(number);
				System.out.println(flight.EmptySeat());
			}
			else if(OpCode == 5){
				System.out.println("Person name(Regular Booking):");
				String name = in1.next();
				Passenger passenger = sys.PassengerMap.get(name);
				System.out.println("Flight number: ");
				int number = in2.nextInt();
				Flight flight = sys.FlightMap.get(number);
				sys.RegularBooking(passenger, flight);	
				System.out.println("Success");
			}	
			else if(OpCode == 6){
				System.out.println("Person name(FirstClassBooking)");
				String name = in1.next();
				Passenger p = sys.PassengerMap.get(name);
				System.out.println("Flight Number:");
				int number = in2.nextInt();
				Flight f = sys.FlightMap.get(number);
				System.out.println("Meal Plan: ");
				String meal= in3.next();
				System.out.println("Seat: ");
				String seat = in4.next();
				sys.FirstClassBooking(p, f, meal,seat);
				System.out.println("Suceess");
			}
			else if(OpCode == 7){
				System.out.println("Person name: ");
				String name = in1.next();
				System.out.println("Flight number: ");
				int number = in2.nextInt();
				System.out.println("Seat number: ");
				String seat = in3.next();
				sys.assignSeat(name, number, seat);
				System.out.println("Success");				
			}
			else if(OpCode == 8){
				sys.displayPassenger();		
			}
			else if(OpCode == 9){
				sys.displayFlight();
			}
			System.out.println("Operation code: ");
			OpCode = in1.nextInt();
			}		
		}
}
