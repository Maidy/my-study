package main;

public class Account3 {

	private int balance;

	public Account3(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return this.balance;
	}

	public void deposit(int income) {
		this.balance +=  income;	
	}

}
