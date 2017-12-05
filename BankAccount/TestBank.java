import java.util.Scanner;


public class TestBank {

	public static void menu(BankAccount b, Scanner in){
		String input = "";
		do{
			System.out.println("d)eposit");
			System.out.println("w)ithdraw");
			System.out.println("m)onth end");
			System.out.println("q)uit");
			System.out.print("Enter command: ");
			input = in.nextLine();
			if (input.equals("d")){
				System.out.print("Enter amount: ");
				double amount = Double.parseDouble(in.nextLine());
				b.deposit(amount);
				System.out.println(b.toString());
			}else if (input.equals("w")){
				System.out.print("Enter amount: ");
				double amount = Double.parseDouble(in.nextLine());
				if (b.withdraw(amount)) System.out.println("It worked.");
				else System.out.println("Not enough money.");				
				System.out.println(b.toString());				
			}else if (input.equals("m")){
				b.monthEnd();
				System.out.println(b.toString());								
			}else if (input.equals("q")){
				System.out.print("quitting");	
			}else{ 
				System.out.println("Invalid. Try again. ");				
			}
				
		}while (!input.equals("q"));

	
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BankAccount b;
		System.out.println("Would you like a c)hequing account or s)avings account: ");
		String account = in.nextLine();
		if (account.equals("c")){
			b = new ChequingAccount(0);	
			menu(b,in);
		}else if (account.equals("s")){
			b = new SavingsAccount(0,.01);//1% interest
			menu(b,in);
		}else{
			System.out.println("Not valid");
		}
		in.close();
	}

}
