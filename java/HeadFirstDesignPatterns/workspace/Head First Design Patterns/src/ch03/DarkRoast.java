package ch03;

public class DarkRoast extends Beverage {
    public DarkRoast(int size) {
    	description = "Dark Roast";
    	this.size = size;
    }

    public double cost() {
    	return 3.00;
    }
}
