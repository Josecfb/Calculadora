package controlador;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import vista.Ventana;

public class Matrix implements Runnable{
	private Ventana ventana;
	Thread hilo;
	public Matrix(Ventana ventana) {
		this.ventana = ventana;
		hilo=new Thread(this);
		hilo.start();
	}
	@Override
	public void run() {
		Clip sonido=null;

		try {
			sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File("matrix.wav")));
			sonido.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String letras="a b c d e f g h i j k l m n o p q r s t u v w x y z 1 2 3 4 5 6 7 8 9 0 · $ % & / ( ) = + * - ? ";
		double w=80;
		double h=50;
		
		while (w!=0) {
			w--;
			h-=0.625;	
			try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			for(int b=0;b<ventana.getBoton().length;b++)	{
				ventana.getBoton()[b].setSize((int)w, (int)h);
			}
		}
		for (int l=0;l<ventana.getCodigoMatrix().length;l++)
			ventana.getCodigoMatrix()[l].setVisible(true);
		for (int v=1;v<=100;v++) 
			
			for (int l=0;l<ventana.getCodigoMatrix().length;l++) {
				try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				ventana.getCodigoMatrix()[l].setText("");
				for (int c=0;c<24;c++)
					ventana.getCodigoMatrix()[l].setText(ventana.getCodigoMatrix()[l].getText()+letras.charAt((int)(Math.random()*letras.length())));
		}
		for (int l=0;l<ventana.getCodigoMatrix().length;l++)
			ventana.getCodigoMatrix()[l].setVisible(false);
		while (w!=80) {
			w++;
			h+=0.625;	
			try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			for(int b=0;b<ventana.getBoton().length;b++)	{
				ventana.getBoton()[b].setSize((int)w, (int)h);
			}
		}
		ventana.getCajaTexto().setText("Bienvenido al mundo real");
		new Pronuncia("Bienvenido al mundo real");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ventana.getCajaTexto().setText("0");
	}
	
	

}
