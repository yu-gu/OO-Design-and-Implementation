
public class ChequingAccount extends BankAccount {

	protected int withdrawsThisMonth;
	
	public ChequingAccount(double initial){
		super(initial);		
		withdrawsThisMonth = 0;
	}
	
	public boolean withdraw(double amount){
		if (withdrawsThisMonth >= 2 && balance >= amount + 1 && amount >= 0){
			balance = (balance - amount) -1;
			withdrawsThisMonth++;
			transactionsThisMonth++;
			return true;
		}else if (withdrawsThisMonth <2 && balance >= amount && amount >= 0){
			balance = balance - amount;
			withdrawsThisMonth++;
			transactionsThisMonth++;
			return true;
		}else return false;
	
		
	}
	
	public void monthEnd(){
		super.monthEnd();
		withdrawsThisMonth = 0;
		
	}

	
	
}
