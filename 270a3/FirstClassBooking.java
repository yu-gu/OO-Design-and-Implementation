//Name:YU GU Student#:11195367 NSID:yug242 Course: 01 Tutorial:T03
/**
 * This is a class that set meal plan to passengers
 * 	who has booking on the first class on flight
 * @author yugu
 */
public class FirstClassBooking extends Booking{
	/**The meal plan for the first class booking*/
	private String mealPlan;
	
	/**
	 * Initialize the Booking with Passenger BasicFlight inherited 
	 * in Booking class and prescribed meal plan
	 * @param passenger - the passenger for booking 
	 * @param flight - the flight for Booking 
	 *  */
	public FirstClassBooking(Passenger passenger,BasicFlight flight,String meal,String seat){
		super(passenger,flight);
		mealPlan = null;
		this.setSeat(seat);
		flight.setSeat(this);
	}

	/**get the name of meal plan
	 * @return the meal plan for this first class booking
	 * */
	public String getMealPlan(){
		return mealPlan;
	}
	/**
	 * change the content of a meal plan
	 * @param newPlan - the new meal plan for first class passenger
	 * */
	public void changeMealPlan(String newPlan){
		mealPlan=newPlan;
	}
	
	/**a method to test the FirstClassBooking class*/
	public static void main(String[] args){
		Passenger p = new Passenger("Bob","123");
		BasicFlight f = new BasicFlight(1,4,12);
		FirstClassBooking a = new FirstClassBooking(p,f,"Apple","1A");
		int numErrors=0;
		
		if(a.getFlight().getCapacity()==12){
			System.out.println("constructor works");
		}
		else{
			numErrors++;
		}
		
		a.changeMealPlan("Milk+Pork");
		if ((a.getMealPlan().equals("Milk+Pork"))){
			System.out.println("Both changeMealPlan and getMealPlan works");
		}
		else{
			numErrors++;
		}
		
		System.out.println("The number of errors found in Person class is "
                + numErrors);
	}
	
}
