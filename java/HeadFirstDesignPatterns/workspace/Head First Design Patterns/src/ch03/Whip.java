package ch03;

public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
    	this.beverage = beverage;
    }
    
    public int getSize() {
    	return beverage.getSize();
    }

    public String getDescription() {
    	return beverage.getDescription() + ".Whip";
    }

    public double cost() {
    	return 0.60 + beverage.cost();
    }
}
