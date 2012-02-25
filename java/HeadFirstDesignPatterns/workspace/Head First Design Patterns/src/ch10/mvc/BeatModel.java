package ch10.mvc;

import java.util.ArrayList;
import java.util.Iterator;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class BeatModel implements BeatModelInterface, MetaEventListener {

	private Sequencer sequencer;
	private ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	private ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	private int bpm = 90;
    Sequence sequence;
    Track track;
	
	@Override
	public void meta(MetaMessage message) {
		
		if (message.getType() == 47) {
			beatEvent();
			sequencer.start();
			setBPM(getBPM());
		}
	}

	private void beatEvent() {
		notifyBeatObservers();		
	}

	@Override
	public void initialize() {
		setUpMidi();
		buildTrackAndStart();
	}

	@Override
	public void on() {
		sequencer.start();
		setBPM(90);
	}

	@Override
	public void off() {
		setBPM(0);
		sequencer.stop();
	}

	@Override
	public void setBPM(int bpm) {
		this.bpm = bpm;
		sequencer.setTempoInBPM(bpm);
		notifyBPMObservers();
	}

	@Override
	public int getBPM() {
		return this.bpm;
	}

	@Override
	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);
	}

	@Override
	public void removeObserver(BeatObserver o) {
		beatObservers.remove(o);
	}

	@Override
	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o);
	}

	@Override
	public void removeObserver(BPMObserver o) {
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
	
	void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.addMetaEventListener(this);
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void buildTrackAndStart() {
		int[] trackList = {35, 0, 46, 0};
	    
        sequence.deleteTrack(null);
        track = sequence.createTrack();

      	makeTracks(trackList);
		track.add(makeEvent(192,9,1,0,4));      
	 	try {
			sequencer.setSequence(sequence);                    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makeTracks(int[] trackList) {
		for (int i = 0; i < trackList.length; i++) {
          int key = trackList[i];

          if (key != 0) {
             track.add(makeEvent(144,9,key, 100, i));
             track.add(makeEvent(128,9,key, 100, i+1));
          }
       }		
	}

	public  MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch(Exception e) {
			e.printStackTrace(); 
		}
		return event;
	}

}
