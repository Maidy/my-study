package ch10.mvc;

import java.util.ArrayList;
import java.util.Iterator;

public class HeartModel {
	
	private int rate = 1;
	
	private ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	private ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	
	public int getHeartRate() {
		return rate;
	}
	
	public void registerBeatObserver(BeatObserver o) {
		beatObservers.add(o);
	}
	
	public void removeBeatObserver(BeatObserver o) {
		beatObservers.remove(o);
	}
	
	public void registerBPMObserver(BPMObserver o) {
		bpmObservers.add(o);
	}
	
	public void removeBPMObserver(BPMObserver o) {
		bpmObservers.remove(o);
	}
	
	void notifyBPMObservers() {
		Iterator<BPMObserver> iter = bpmObservers.iterator();
		while (iter.hasNext()) {
			BPMObserver o = iter.next();
			o.updateBPM();
		}
	}
	
	void notifyBeatObservers() {
		Iterator<BeatObserver> iter = beatObservers.iterator();
		while (iter.hasNext()) {
			BeatObserver o = iter.next();
			o.updateBeat();
		}
	}
	
}
