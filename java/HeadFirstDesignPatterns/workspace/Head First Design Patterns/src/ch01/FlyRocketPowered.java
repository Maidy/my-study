package ch01;

public class FlyRocketPowered implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("로켓으로 날고 있어요. 진짜 빨라요!");
	}

}
