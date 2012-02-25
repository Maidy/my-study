package ch03;

public class Espresso extends Beverage {
    public Espresso(int size) {
    	description = "Espresso";
    	this.size = size;
    }

    public double cost() {
    	return 1.99;
    }
}
