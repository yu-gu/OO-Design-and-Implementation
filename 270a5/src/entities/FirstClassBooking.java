package entities;

/**
 * A Booking that has a meal plan and seat selection at booking time.
 */
public class FirstClassBooking extends Booking 
{
	/**
	 * The meal plan of the Booking.
	 */
	private String mealPlan;
	
	/**
	 * Initialize an instance with the specified person, flight, seat and mealPlan.
	 * @param person  the passenger for the booking
	 * @param flight  the flight for the booking
	 * @param seat  the seat for the booking
	 * @param mealPlan  the meal plan for the booking
	 */
	public FirstClassBooking(Person person, BasicFlight flight, String seat, String mealPlan)
	{
		super(person, flight);
		setSeat(seat);
		this.mealPlan = mealPlan;
	}
	
	/**
	 * @return  the meal plan associated with the booking
	 */
	public String getMealPlan()
	{
		return mealPlan;
	}
	
	/**
	 * Change the meal plan associated with this booking.
	 * @param newPlan  the new value for the meal plan
	 */
	public void setMealPlan(String newPlan)
	{
		mealPlan = newPlan;
	}
	
	/**
	 * @return the String representation of the first-class booking.
	 */
	@Override
	public String toString()
	{
		String result = super.toString();
		if (mealPlan != null)
			result = result.substring(0, result.length()-1)  // remove the newline char
			         + " with meal plan " + mealPlan;
		return result + "\n";
	}
	
	/**
	 * A simple method to test the class, assuming that Booking is correct.
	 * @param args  not used
	 */
	public static void main(String[] args)
	{
		BasicFlight f = new BasicFlight(513, 4, 12);
		Passenger p = new Passenger("Pete", "249-5418");
		FirstClassBooking b = new FirstClassBooking(p, f, "1A", "shrimp");
		if (!b.getMealPlan().equals("shrimp"))
			System.out.println("Constructor or getMealPlan() for FirstClassBooking failed");
		if (b.getFlight() != f)
			System.out.println("Problems with constructor.");
		if (b.getPerson() != p)
			System.out.println("Problems with constructor.");
		if (! b.getSeat().equals("1A"))
			System.out.println("Problems with constructor.");
		b.setMealPlan("beef");
		if (!b.getMealPlan().equals("beef"))
			System.out.println("setMealPlan() for FirstClassBooking failed");
		System.out.println("The information for Pete on flight 513 " +
				           "with meal plan beef is " + b);
	}
}
