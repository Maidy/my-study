package ch10.mvc;

public interface ControllerInterface {
	public void start();
	public void stop();
	public void setBPM(int bpm);
	public void increaseBPM();
	public void decreaseBPM();
}
