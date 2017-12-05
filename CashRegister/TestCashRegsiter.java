
public class TestCashRegsiter {

	public static void main(String[] args) {
		double f = 10;
		CashRegister cash = new CashRegister();
		CashRegister cash2 = new CashRegister(f,1);
		
		cash.addItem(50);
		

		System.out.println("The count is " + cash.getCount());
		System.out.println("The total is " + cash.getTotal());

		System.out.println("The count is " + cash2.getCount());
		System.out.println("The total is " + cash2.getTotal());
		cash.clear();
		System.out.println("The count is " + cash.getCount());
		System.out.println("The total is " + cash.getTotal());
		
		
	}

}
