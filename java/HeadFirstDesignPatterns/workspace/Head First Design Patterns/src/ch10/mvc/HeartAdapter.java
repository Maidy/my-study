package ch10.mvc;

public class HeartAdapter implements BeatModelInterface {
	
	private HeartModel model;

	public HeartAdapter(HeartModel model) {
		this.model = mode;
	}
	
	@Override
	public void initialize() { }

	@Override
	public void on() { }

	@Override
	public void off() { }

	@Override
	public void setBPM(int bpm) { }

	@Override
	public int getBPM() {
		return model.getHeartRate();
	}

	@Override
	public void registerObserver(BeatObserver o) {
		model.registerBeatObserver(o);
	}

	@Override
	public void removeObserver(BeatObserver o) {
		model.removeBeatObserver(o);
	}

	@Override
	public void registerObserver(BPMObserver o) {
		model.registerBPMObserver(o);
	}

	@Override
	public void removeObserver(BPMObserver o) {
		model.removeBPMObserver(o);
	}

}
