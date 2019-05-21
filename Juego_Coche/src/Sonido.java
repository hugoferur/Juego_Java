import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido {
	private Clip sonido;

	public Sonido(String fichero){
		try {
			sonido=AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File("./sonidos/"+fichero)));
		} catch (LineUnavailableException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {

			e.printStackTrace();
		}
	}


	public void play(){
		if(sonido.isRunning()){
			sonido.close();
			sonido.start();
		}else{
			sonido.start();
		}
	}

	public void detener(){
		sonido.stop();
	}

	public void playLoop(){
		sonido.loop(Clip.LOOP_CONTINUOUSLY);
	}
}











