//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
/**
 * BasicFlight class is to store flight information 
 * and make some ticket bookings operations
 * @author YuGu
 */
public class BasicFlight {
	private int number;		 /**This field stores flight number*/
	private int width;		 /**This field stores the width of a row*/
	private int seatlimit;	 /**This field stores the limit of a seat in a specific airline*/
	private Booking ticket[];/**This field stores all bookings made by customers including their name,phone#,flight# and seat#*/
	/**
	 * A constructor to initialize all fields declared
	 * @param numberIn - the flight number variable
	 * @param widthIn - the number of seats in a row
	 * @param lim - the limit number of seats available on an airline
	 */
	public BasicFlight(int numberIn, int widthIn, int lim){
		number=numberIn;
		width=widthIn;
		seatlimit= lim;
		ticket= new Booking[seatlimit];
	}
	
	/**
	 * an accessor method to get the flight number
	 * @return number
	 */
	public int getNumber(){
		return number;
	}
	
	/**
	 * accessor method to get how many seats in a row
	 * @return width
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * accessor method to get the total number of seats in an airplane
	 * @return seatlimit (capacity of seat)
	 */
	public int getCapacity(){
		return seatlimit;
	}

	/**
	 * a mutator method to set a seat that was booked 
	 * to have it stored into the array
	 * @param boo -  
	 */
	public void setSeat(Booking boo){
		if (isSeatAvailable(boo.getSeat())){
		ticket[indexFromSeat(boo.getSeat())]= boo;
		}
		else{
			System.out.println("Sorry!This seat has been taken!");
 		}
	}	
	
	/**
	 * accessor method to get the person who booked 
	 * certain seat with seat number in string
	 * @param seatNum - seat number in string format
	 * @return p2 
	 */
	public Person getPerson(String seatNum){
		Person p2 = ticket[indexFromSeat(seatNum)].getPerson();
		return p2;
	}
	/**
	 * method to check if a given seat number has been 
	 * taken by previous booking or not
	 * @param seatNum - seat number in string format
	 * @return true/false
	 * */
	public boolean isSeatAvailable(String seatNum){
		if (ticket[indexFromSeat(seatNum)]==null){
			return true;
		}
		else{
			System.out.println("This seat has been taken by " + getPerson(seatNum));
			return false;
		}
	}
	/**method to transfer seat number from string to index 
	 * note that 1A respond to index 0
	 * @param seatNum - seat number in string format
	 * @return sum - the index transferred from a string seat numbers
	 * */
	public int indexFromSeat(String seatNum){	
		int letter=0;
		letter = (int) seatNum.charAt(seatNum.length()-1)-65;
		int calc = ((Integer.parseInt(seatNum.substring(0,seatNum.length()-1)))-1)*width;
		int sum =  calc + letter;
		if (sum>seatlimit){
			System.out.println("Please input a valid seat numebr");
			return 0;
		}
		
		return sum;
	}
	/**method to transfer seat number from string to index
	 * note that index 0 respond to 1A
	 *@param Index - the index of seat transferred from string seat number
	 *@return seatFromIndex
	 * */
	public String SeatFromIndex(int Index){
		if (Index > seatlimit){
			System.out.println("Please input a index within seat limit");
			return "";
		}
		char chara='A';
		String seatFromIndex = "";
		chara = (char)('A' + Index % width );
		seatFromIndex= (Index / width) + 1 +""+chara;		
		return seatFromIndex;
	}
	/**method to put flight number,width,capacity and 
	 * demonstrate which seat in a given airline has been 
	 * taken and his/her name
	 * @return x1
	 * */
	public String toString(){
			String x1 = "Flight Number: "+this.number +" Width: "+ 
						this.width +" Capacity: "+ this.seatlimit + "\n";
			String x2="";
			for(int i=0;i< this.seatlimit;i++){
				if(ticket[i] != null){
					x2 = ticket[i].getSeat()+": " +" Name: " + ticket[i].getPerson().getName() + "\n";
					x1 = x1 +x2;
					//System.out.println(x2);
					}
				else{
					x2= SeatFromIndex(i)+"\n";
					x1 = x1 + x2;
					//System.out.println(x2);
				}
			}
			return x1;
		}
	/**
	 * Main method to test all functions in BasicFlight class
	 * @param args[] - string 
	 * */
	public static void main(String[] args){
	Person x2 = new Person("YuGu","182-3456");
	BasicFlight f1 = new BasicFlight(7703,5,40);//testing BasicFlight constructor
	Booking boo2 = new Booking(x2,f1);			
	System.out.println(f1.getNumber());			 //testing getNumber method
	System.out.println(f1.getWidth());			 //testing getWidth method
	System.out.println(f1.getCapacity());		 //testing getCapacity method
	boo2.setSeat("4A");							 //testing setSeat method in Booking class
	f1.setSeat(boo2);							 //testing setSeat method in BasicFlight class
	System.out.println(f1.getPerson("4A").getName());//testing getPerson method
	System.out.println(f1.getPerson("4A").getPhoneNumber());//
	System.out.println(f1.isSeatAvailable("4A"));//testing isSeatAvailable method
	System.out.println(f1.indexFromSeat("4A"));	 //testing indexFromSeat method should output 15
	System.out.println(f1.SeatFromIndex(4)); 	 //testing SeatFromIndex method,should output 1E
	System.out.println(f1.toString());			 //testing toString method
	}
}
