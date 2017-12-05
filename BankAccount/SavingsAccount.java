
public class SavingsAccount extends BankAccount {
	protected double interest;
	protected double minimumMonthly;
	
	
	public SavingsAccount(double initial, double interestRate){
		super(initial);
		interest = interestRate;
		minimumMonthly = initial;
	}
	
	
	public boolean withdraw(double amount){
		if (super.withdraw(amount)){
			minimumMonthly = balance;
			return true;
		}else return false;
		
	}
	
	public void monthEnd(){
		balance = balance + balance*interest;
		super.monthEnd();
		minimumMonthly = balance;		
		
	}
}
