package bank.thread;

public class Bank {

	private int balance;
	
	public void deposit(int amount) {
		this.balance=this.balance+amount;
	}
	
	public void withdraw(int amount) {
		this.balance=this.balance-amount;
	}
}
