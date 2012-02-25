package ch03;

public class SteamMilk extends CondimentDecorator {
    Beverage beverage;

    public SteamMilk(Beverage beverage) {
    	this.beverage = beverage;
    }
    
    public int getSize() {
    	return beverage.getSize();
    }

    public String getDescription() {
    	return beverage.getDescription() + ".Steam Milk";
    }

    public double cost() {
    	return 0.80 + beverage.cost();
    }
}
