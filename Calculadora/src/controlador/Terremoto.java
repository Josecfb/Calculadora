package controlador;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import vista.Ventana;

public class Terremoto implements Runnable{
	private Ventana ventana;
	private Thread hilo;
	
	
	public Terremoto(Ventana ventana) {
		this.ventana = ventana;
		hilo=new Thread(this);
		hilo.start();
	}

	
	//Este hilo mueve la ventana y los botones cuando el resultado de una operación es infinito o aproximado
	//provocando la furia de Thanos que nos transmite que no poseemos las gemas del infinito
	
	@Override
	public void run() {
		Clip sonido1=null;
		if (ventana.isVoz())
		try {
			sonido1 = AudioSystem.getClip();
			sonido1.open(AudioSystem.getAudioInputStream(new File("src/sonidos/thanos1.wav")));
			sonido1.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int vx=ventana.getX();
		int vy=ventana.getY();
		int pix[]=new int[ventana.getBoton().length];
		int piy[]=new int[ventana.getBoton().length];
		int x[]=new int[ventana.getBoton().length];
		int y[]=new int[ventana.getBoton().length];
		double a[]=new double[ventana.getBoton().length];
		double[] desfase =new double[ventana.getBoton().length];
		for (int b=0;b<ventana.getBoton().length;b++) {
			desfase[b]=Math.random()*2*Math.PI;
			a[b]+=desfase[b];
			pix[b]=ventana.getBoton()[b].getX();
			piy[b]=ventana.getBoton()[b].getY();
		}
		double ia=0.1;
		double v=0;
		ventana.getCajaTexto().setText("No posees las gemas");
		while (v<=(int)(10*2*Math.PI)) {
			v+=ia;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int b=0;b<ventana.getBoton().length;b++) {
				a[b]+=ia;
				x[b]=(int)(Math.cos(a[b])*(60-(int)v));
				y[b]=(int)(Math.sin(a[b])*(30-(int)v/2));
				
				ventana.getBoton()[b].setLocation(pix[b]+x[b]*2,piy[b]+y[b]*2);	
				
				if (v<55)
				ventana.setLocation(vx+x[1]*(15-(int)v/2), vy+y[1]*(15-(int)v/2));
			}
		}
			for (int b=0;b<ventana.getBoton().length;b++)
				ventana.getBoton()[b].setLocation(pix[b],piy[b]);
			ventana.getCajaTexto().setText("0");
		}	
	}

