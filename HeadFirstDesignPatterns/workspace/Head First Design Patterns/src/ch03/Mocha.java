package ch03;

public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
    	this.beverage = beverage;
    }

    public int getSize() {
    	return beverage.getSize();
    }
    
    public String getDescription() {
    	return beverage.getDescription() + ".Mocha";
    }

    public double cost() {
    	double cost = beverage.cost();
    	int size = beverage.getSize();
    	if (size == Beverage.TALL) {
    		cost += 10.00;
    	} else if (size == Beverage.GRANDE) {
    		cost += 20.00;
    	} else {
    		cost += 30.00;
    	}
    	return cost;
    }
}
