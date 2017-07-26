package com.jh.ui.common;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class MessageMusics {

	private Sequence seq;
	private Sequencer midi;
	
	public void loadSound() {
		try {
			seq = MidiSystem.getSequence(MessageMusics.class.getResourceAsStream("/musics/message.mid")); // 获取到音乐文件
			midi = MidiSystem.getSequencer();
			midi.open();
			midi.setSequence(seq);
			midi.start();
			midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 关闭音乐
	public void myStop() {
		midi.stop();
		midi.close();
	}
}
