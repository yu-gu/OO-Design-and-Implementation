
public class FirstClassBooking extends Booking{
	private String mealPlan;
	
	
	public FirstClassBooking(){
		super(null,null);
		mealPlan = null;
	}
	
	public void addMealPlan(String meal){
		mealPlan=meal;
	}
	
	public void changeMealPlan(String newPlan){
		mealPlan=newPlan;
	}
	
	
	public static void main(String[] args){
		
	}
	
}
