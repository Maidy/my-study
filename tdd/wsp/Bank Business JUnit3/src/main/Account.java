package main;

public class Account {
	
	int balance;
	
	public Account(int balance) {
		this.balance = balance;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void withdraw(int amount) {
		this.balance -= amount;
	}
	
	public void deposit(int amount) {
		this.balance += amount;
	}

}
