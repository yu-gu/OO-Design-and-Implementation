import java.util.LinkedList;

public class Flight extends BasicFlight {
	private LinkedList<BasicFlight> booking;

	public Flight(){
		super(0,0,0);
		LinkedList<BasicFlight> booking=new LinkedList<BasicFlight>(); 
		
	}
}
