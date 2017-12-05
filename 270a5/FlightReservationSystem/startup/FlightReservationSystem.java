package startup;

import interfaces.ConsoleIO;
//import entities.Flight;
//import containers.FlightSetAccess;
import commands.AddPassengerCommand;
import commands.AddFlightCommand;
import commands.DisplayEmptySeatsCommand;
import commands.DisplayPassengersCommand;
import commands.DisplayFlightsCommand;
import commands.BookPassengerCommand;
import commands.BookFirstClassCommand;
import commands.AssignSeatCommand;

/**
 * A simple flight reservation system whereby passengers can be created, flights created,
 * and passengers booked on the flights.  First class passengers are initially given a seat
 * and a meal plan.  Regular passengers are booked on a flight with no seat and can later 
 * be given a seat.  The user has the choice of console or dialog input/output.
 */
public class FlightReservationSystem 
{
	/**
	 * The interface to be used to read input from the user and output results to the user.
	 */
	private ConsoleIO ioInterface;
	
	/**
	 * Initialize the system by creating the dictionaries and interface for I/O.
	 */
	public FlightReservationSystem()
	{
		ioInterface = new ConsoleIO();
		//Flight f = new Flight(1,4,20);
		//FlightSetAccess.dictionary().put(1,f);
	}
	
	/**
	 * Run the reservation system: initialize, and then accept and carry out
	 * operations.  Output the passenger and flight dictionaries when finishing.
	 */
	public void run()
	{
		String[] choices = new	String[] {"quit",            // opId 0
				"add a new passenger",                       // opId 1 
				"add a new flight",                          // opId 2
				"display the empty seats of a flight",       // opId 3
				"book a passenger on a flight",              // opId 4
				"book a first-class passenger on a flight",  // opId 5
				"assign a passenger a seat",                 // opId 6
				"display all the passengers",                 // opId 7
				"display all the flights"};                // opId 8
		int opId = ioInterface.readChoice(choices);
		while (opId != 0)
		{
			switch (opId)
			{
			case 1:
				addPassenger();
				break;
			case 2:
				addFlight();
				break;
			case 3:
				displayEmptySeats();
				break;
			case 4:
				bookPassenger();
				break;
			case 5:
				bookFirstClassPassenger();
				break;
			case 6:
				assignSeatForPassenger();
				break;
			case 7:
				outputPassengers();
				break;
			case 8:
				outputFlights();
				break;
			default:
				System.out.println("Invalid int; try again");
			}
			
			opId = ioInterface.readChoice(choices);
		}
		
		ioInterface.outputString("The system at shutdown is as follows: " + toString());
	}
	
	
	/**
	 * Read the information for a new passenger and then add the passenger 
	 * to the dictionary of all passengers.
	 */
	public void addPassenger()
	{
		String name = ioInterface.readString("Enter the name of the passenger: ");
		String address = ioInterface.readString("Enter the telephone number of the passenger: ");
		AddPassengerCommand addPassCmd = new AddPassengerCommand();
		addPassCmd.addPassenger(name, address);
		if (!addPassCmd.wasSuccessful())
			ioInterface.outputString(addPassCmd.getErrorMessage());
	}

	/**
	 * Read the information for a new flight and then add the flight 
	 * to the dictionary of all flights.
	 */
	public void addFlight()
	{
		int number = ioInterface.readInt("Enter the number of the flight: ");
		int width = ioInterface.readInt("Enter the width of the flight: ");
		int capacity = ioInterface.readInt("Enter the capacity of the flight: ");
		AddFlightCommand addFlightCmd = new AddFlightCommand();
		addFlightCmd.addFlight(number, width, capacity);
		if (!addFlightCmd.wasSuccessful())
			ioInterface.outputString(addFlightCmd.getErrorMessage());
	}
	
	/**
	 * Read the number for a flight, and then display its empty seats.
	 */
	public void displayEmptySeats()
	{
		int flightNumber = ioInterface.readInt("Enter the number of the flight: ");
		DisplayEmptySeatsCommand emptySeatCmd = new DisplayEmptySeatsCommand(flightNumber);
		if (!emptySeatCmd.wasSuccessful())
			ioInterface.outputString(emptySeatCmd.getErrorMessage());
		else 
			ioInterface.outputString(emptySeatCmd.getEmptySeatsString());
	}
	
	/**
	 * Read the name for a passenger, the number for a flight, and then book
	 * the passenger on the flight as a regular passenger.
	 */
	public void bookPassenger()
	{
		String name = ioInterface.readString("Enter the name of the passenger: ");
		int number = ioInterface.readInt("Enter the number of the flight: ");
		BookPassengerCommand bookCmd = new BookPassengerCommand();
		bookCmd.bookPassenger(name, number);
		if (!bookCmd.wasSuccessful())
			ioInterface.outputString(bookCmd.getErrorMessage());
	}
	
	/**
	 * Read the name for a first-class passenger, the number for a flight, 
	 * the seat for the passenger, the meal plan, and then book
	 * the passenger on the flight as a first-class passenger.
	 */
	public void bookFirstClassPassenger()
	{
		String name = ioInterface.readString("Enter the name of the passenger: ");
		int number = ioInterface.readInt("Enter the number of the flight: ");
		String seat = ioInterface.readString("Enter the seat for the passenger: ");
		String meal = ioInterface.readString("Enter the meal plan of the passenger: ");
		BookFirstClassCommand bookCmd = new BookFirstClassCommand();
		bookCmd.bookFirstClassPassenger(name, number, seat, meal);
		if (!bookCmd.wasSuccessful())
			ioInterface.outputString(bookCmd.getErrorMessage());
	}
	
	/**
	 * Read the name for a regular passenger who does not have a seat on a flight, 
	 * the number of the flight, the seat for the passenger, and then book the 
	 * passenger in the seat of the flight.
	 */
	public void assignSeatForPassenger()
	{
		String name = ioInterface.readString("Enter the name of the passenger: ");
		int number = ioInterface.readInt("Enter the number of the flight: ");
		String seat = ioInterface.readString("Enter the seat for the passenger: ");
		AssignSeatCommand assignSeatCmd = new AssignSeatCommand();
		assignSeatCmd.assignSeatForPassenger(name, number, seat);
		if (!assignSeatCmd.wasSuccessful())
			ioInterface.outputString(assignSeatCmd.getErrorMessage());
	}
	
	/**
	 * Output the information for all the passengers in the system.
	 */
	public void outputPassengers()
	{
		DisplayPassengersCommand displayPassCmd = new DisplayPassengersCommand();
		if (!displayPassCmd.wasSuccessful())
			ioInterface.outputString(displayPassCmd.getErrorMessage());
		else
			ioInterface.outputString(displayPassCmd.getPassengersString());
	}
	
	/**
	 * Output the information for all the flights in the system.
	 */
	public void outputFlights()
	{
		DisplayFlightsCommand displayFlightsCmd = new DisplayFlightsCommand();
		if (!displayFlightsCmd.wasSuccessful())
			ioInterface.outputString(displayFlightsCmd.getErrorMessage());
		else
			ioInterface.outputString(displayFlightsCmd.getFlightsString());
	}
	
	/**
	 * @return a String that contains all the passengers and flights in the system.
	 */
	public String toString()
	{
		String result = "";
		DisplayPassengersCommand displayPassCmd = new DisplayPassengersCommand();
		if (!displayPassCmd.wasSuccessful())
			ioInterface.outputString(displayPassCmd.getErrorMessage());
		else
			result = result + displayPassCmd.getPassengersString();
		
		DisplayFlightsCommand displayFlightsCmd = new DisplayFlightsCommand();
		if (!displayFlightsCmd.wasSuccessful())
			ioInterface.outputString(displayFlightsCmd.getErrorMessage());
		else
			result = result + displayFlightsCmd.getFlightsString();
		return result + "\n";
	}
	
	/**
	 * Run the flight reservation system.
	 * @param args not used
	 */
	public static void main(String[] args) 
	{
		FlightReservationSystem sys = new FlightReservationSystem();
		sys.run();
	}
}
