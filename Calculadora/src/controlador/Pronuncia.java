package controlador;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class Pronuncia implements Runnable{
	private Thread hilo;
	private String palabras;
	
	
	
	public Pronuncia(String palabras) {
		this.palabras = palabras;
		hilo=new Thread(this);
		hilo.start();
	}


	private void suena(String archivo) {
		Clip sonido=null;

		try {
			sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File(archivo)));
			sonido.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public void run() {
		if (palabras.equals("No posees las gemas")) {
			suena("infinito.wav");
			return;
		}
		if (palabras.equals("Matrix te posee")) {
			suena("teposee.wav");
			return;
		}
		if (palabras.equals("Bienvenido al mundo real")) {
			suena("real.wav");
			return;
		}
		if(!palabras.contains("primo"))
		for(int l=0;l<palabras.length();l++) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			if (palabras.charAt(l)=='.')
				suena("coma.wav");
			else
				suena(palabras.charAt(l)+".wav");
		
	}

}
}
