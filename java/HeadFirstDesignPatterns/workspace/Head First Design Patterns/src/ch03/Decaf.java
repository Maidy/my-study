package ch03;

public class Decaf extends Beverage {
    public Decaf(int size) {
    	description = "Decaf";
    	this.size = size;
    }

    public double cost() {
    	return 3.99;
    }
}
