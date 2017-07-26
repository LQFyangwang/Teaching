package com.gs.game;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class GameMusic {

	
	private Sequencer sr;
	
	public void play(String path) {
		try {
			// 歌曲信息
			Sequence s = MidiSystem.getSequence(GameMusic.class.getResourceAsStream(path));
			// 获取能够处理歌曲信息的对象
			sr = MidiSystem.getSequencer();
			// 打开处理歌曲的对象
			sr.open();
			// 添加歌曲到播放器
			sr.setSequence(s);
			// 开始播放
			sr.start();
		} catch (InvalidMidiDataException | IOException e) {
			e.printStackTrace();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		sr.stop();
	}
	
}
