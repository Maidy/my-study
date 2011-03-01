package ch01;

public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("못 날아요~");
	}

}
