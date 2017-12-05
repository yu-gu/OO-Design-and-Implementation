import java.util.LinkedList;
import java.util.ListIterator;

public class Passenger extends Person{
	private LinkedList<Booking> PassBooking;
	//private Booking b;
	/***/
	public Passenger(){
		super("","");
		//LinkedList<Booking> PassBooking = new LinkedList<Booking>();
	}
	
	/***/
	public void addBooking(Booking b){
		PassBooking.addLast(b);
	}
	
	/***/
	public boolean hasBooking(BasicFlight f){
		ListIterator<Booking> iter = PassBooking.listIterator();
		while (iter.hasNext()){	
			if (iter.next().getPerson().getName() == this.getName() ){
				return true;
			}
		}
		System.out.println("No record in linked list");
		return false;
	}
	
	/***/
	public boolean hasSeat(BasicFlight f){
		ListIterator<Booking> iter = PassBooking.listIterator();
		while (iter.hasNext()){
			if (f.getPerson(iter.next().getSeat()).getName() == this.getName() ){
				return true;
			}
		}
			return false;
	}
	public String toString(){ 
		String x = this.getName()+this.getTelephoneNumber();
		return x;
	}
	
	public static void main(String[] args){
		
	}
}
