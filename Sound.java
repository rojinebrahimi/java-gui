package imageviewer;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	Clip clip;

	// Sound Player
	// --------------------------------------------------------------------------------------------------------------------------
	public void soundPlayer() throws LineUnavailableException {
		try {

			File soundFile = new File("C:\\Users\\Rojin Ebrahimi\\Downloads\\Music\\01 Truth Of Touch.wav");

			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			clip.loop(4);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Stop Sound
	// --------------------------------------------------------------------------------------------------------------------------
	public void stopSound() {
		BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
		if(muteControl != null){
		    muteControl.setValue(true); 
		}

		clip.loop(0); 
		clip.flush();

	}
}
