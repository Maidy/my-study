package ch10.mvc;

public class DJTestDrive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeatModelInterface model = new BeatModel();
		ControllerInterface controller = new BeatController(model);
	}

}
