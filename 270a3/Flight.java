//Name:YU GU Student#:11195367 NSID:yug242 Course: 01 Tutorial:T03
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * A flight class that stores all bookings without seat being assigned to that booking
 * @author yugu
 */
public class Flight extends BasicFlight {
	/**
	 * The bookings that has not been assigned a seat
	 */
	LinkedList<Booking> bookwoseat;
	
	/**
	 * The iterator that go through bookings without seat
	 */
	ListIterator<Booking> iter;
	/**
	 * Initialize the flight with the specified number, width and capacity.
	 * Initialize the Booking linked list 
	 * @param num
	 * @param wid
	 * @param cap
	 */
	public Flight(int num,int wid,int cap){
		super(num,wid,cap);
		bookwoseat=new LinkedList<Booking>();
	}
	
	
	/**
	 * add Booking for non-first class passengers 
	 * @param p
	 * @precond p!=null
	 */
	public void setRegularBooking(Passenger p){
		if (p==null){
			throw new RuntimeException("Passenger cannot be null");
		}
		Booking b = new Booking(p,this);
		p.addBooking(b);
		bookwoseat.addLast(b);
	}
	/**
	 * Set bookings for passenger who book first class of a flight
	 * Also includes the meal plan choice
	 * @param p
	 * @param mealplan
	 * @precond p!=null
	 * @precond seat!=null
	 */
	public void setFirstBooking(Passenger p,String mealplan,String seat){
		if (p==null){
			throw new RuntimeException("passenger cannot be null");
		}
		if (seat==null){
			throw new RuntimeException("Seat must be chosen");
		}
		
		FirstClassBooking b = new FirstClassBooking(p,this,mealplan,seat);
		p.addBooking(b);
		b.changeMealPlan(mealplan);
		b.setSeat(seat);
		this.setSeat(b);
	}
	/**
	 * A mutator method that if a certain booking has a seat assigned to it,
	 * if yes, it will remove the booking from the linked list that stores
	 * all bookings without a seat assigned to it
	 * @param b
	 * @precond b!=null
	 */
	public void SeatOperation(Booking b){
		if (b==null){
			throw new RuntimeException("Booking cannot be null");
		}
		
			this.setSeat(b);
			iter = bookwoseat.listIterator(); 
			while (iter.hasNext()){
				Booking n = iter.next();
				if ( n.getPerson().getName().equals(b.getPerson().getName())){
					iter.remove();
				}
			}
	}
	/**
	 * An accessor method that 
	 * @return the remaining bookings left for certain flight
	 */
	public int getRemainCapacity(){
		return this.getCapacity() - bookwoseat.size() ;
	}
	/**
	 * A method that transfer all seats that have not been taken
	 * on a certain flight into string format
	 * @return seat - all empty seats in string format on a certain flight
	 */
	public String EmptySeat(){
		String empty_seat="";
		
		for (int i=0;i<getCapacity();i++){
			if (this.isSeatAvailable(this.seatFromIndex(i))){
				empty_seat += this.seatFromIndex(i)+" ";
			}
			else{
				empty_seat+=empty_seat+" ";
			}
		}
		return "Empty Seat List:\n"+empty_seat; 
	}
	
	public Passenger getPerson(String seat){
		Passenger p = (Passenger)seats[indexFromSeat(seat)].getPerson();
		return p;
	}
	
	/**
	 * a String representation of Flight information
	 * @return x - the People who has booking on certain flight but without
	 * seat being assigned to him/her
	 */
	public String toString(){
		String x = " ";
		ListIterator<Booking> iter = bookwoseat.listIterator();
		while (iter.hasNext()){
				x+= iter.next().getPerson().getName()+"\t";
		}
		return x;
	}
	/**
	 * A method to test Flight class
	 * @param args
	 */
	public static void main(String[] args){
		Flight f = new Flight(123,4,30);
		Passenger p1 = new Passenger("Bob","123");
		Passenger p2 = new Passenger("John","123");
		FirstClassBooking b1 = new FirstClassBooking(p1,f,"Chicken","1A");
		Booking b2=new Booking(p2,f);
		int numErrors=0;
		
		if(f.getCapacity()==30){
		}
		else{
			System.out.println("Constructor not working");
			numErrors++;
		}
		
		if(b1.getMealPlan()==null){
		}
		else{
			System.out.println("setFirstBooking method does not work");
			numErrors++;
		}
		
		f.setFirstBooking(p1, "Chicken","1A");
		f.setRegularBooking(p2);
		if(f.getRemainCapacity()==29){
			System.out.println("getRemainCapacity works");
		}
		else{
			System.out.println("getRemainCapacity method does not work");
			numErrors++;
		}
		
		System.out.println(f.EmptySeat());
		System.out.println(f.toString());
		b2.setSeat("1B");
		f.setSeat(b2);
		
		if(f.getRemainCapacity()==29){

		}
		else{
			System.out.println("getRemainCapacity method does not work");
			numErrors++;
		}
		
		if (f.getPerson("1A").getName().equals("Bob")==true){
			
		}
		else{
			System.out.println("getPerson() method not running succeed");
			numErrors++;
		}
		
		System.out.println(f.EmptySeat());
		f.SeatOperation(b2);
		if (f.getRemainCapacity()==30)
		{
		}else{
			System.out.println("SeatOpe() method not running succeed");
			numErrors++;
		}
		System.out.println(f.toString());
		System.out.println("The number of errors found in Person class is "
                + numErrors);
	}
}
