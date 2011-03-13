package ch03;

public class HouseBlend extends Beverage {
    public HouseBlend(int size) {
    	description = "House Blend";
    	this.size = size;
    }

    public double cost() {
    	return 0.89;
    }
}
