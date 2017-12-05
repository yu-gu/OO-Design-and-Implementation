//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
import java.util.Scanner;
/**
 * This class is more of a formal reservation system that response to operator's requests
 */
public class TestingDemo {
	/**Main method to utilize certain methods in all three classes
	 * @param args[] - string
	 * */
	public static void main(String[] args){
		System.out.println("Welcome to flight reservation system!");
		Scanner in = new Scanner(System.in);
		
		Person personx = new Person("James Gosling","306-1234");
		BasicFlight flightx = new BasicFlight(6637,4,35);
		Booking booking1= new Booking(personx,flightx);
		booking1.setSeat("1A");
		flightx.setSeat(booking1);
		flightx.toString();
		for (int n=1;n<flightx.getCapacity();n++){
			String a1,a2;
			System.out.print("Please enter your Name: ");
			a1=in.nextLine();
			System.out.print("Please enter your PhoneNumber: ");
			a2=in.nextLine();
			Person persony = new Person(a1,a2);
			Booking booking2= new Booking(persony,flightx);
			System.out.print("Please enter the seat you'd like to revsered: ");
			booking2.setSeat(in.nextLine());
			flightx.setSeat(booking2);
			System.out.println(flightx.toString());
		}
		in.close();
	}

}

