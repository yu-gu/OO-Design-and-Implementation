
public abstract class BankAccount {
	
	protected double balance;
	protected int transactionsThisMonth;
	
	
	public BankAccount(double init){
		balance = init;
		transactionsThisMonth = 0;		
	}
	
	public void deposit(double amount){
		if (amount > 0){
			balance = balance + amount;
			transactionsThisMonth++;			
		}
	}
	
	
	public boolean withdraw(double amount){
		if (balance >= amount && amount >0){
			balance = balance - amount;
			transactionsThisMonth++;
			return true;
		}else return false;
		
	}

	public void monthEnd(){
		transactionsThisMonth = 0;
	}
	
	public String toString(){
		String str = "[balance: " + balance + "][transactions this month: " + transactionsThisMonth + "]";
		return str;
	}

}
