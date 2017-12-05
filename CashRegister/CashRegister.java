/**
 * A class that simulates a cash register that stores the number of items, and the subtotal.
 * 
 */
public class CashRegister {

	private int numberOfItems;
	private double registerTotal;

	/**
	 * Creates a new CashRegister object with 0 for the number of items and total.
	 */
	public CashRegister(){
		numberOfItems = 0;
		registerTotal = 0;
	}

	/**
	 * Creates a new CashRegister object with a given price
	 * @param price: the price of the starting total.
	 */
	public CashRegister(double price, int items){
		if (price>0 && items >0){
			registerTotal = price;
			numberOfItems = items;
		} else{
			numberOfItems = 0;
			registerTotal = 0;
		}
	}
	
	
	
	/**
	 * Adds a new item to the cash register.
	 * @param price: the price of the item.
	 */
	public void addItem(double price){
		registerTotal = registerTotal + price;
		numberOfItems++;
		
	}
	
	
	/**
	 * clears the total and items
	 */
	public void clear(){
		numberOfItems = 0;
		registerTotal = 0;
	}

	/**
	 * Retieves the number of items in CashRegister
	 * @return the item count
	 */
	public int getCount(){
		return numberOfItems;
	}

	/**
	 * Retrieves the total
	 * @return the total
	 */
	public double getTotal(){
		return registerTotal;
	
	}
	
}
