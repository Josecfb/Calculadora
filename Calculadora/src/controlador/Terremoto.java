package controlador;

import vista.Ventana;

public class Terremoto implements Runnable{
	private Ventana ventana;
	private Thread hilo;
	
	
	public Terremoto(Ventana ventana) {
		this.ventana = ventana;
		hilo=new Thread(this);
		hilo.start();
	}


	@Override
	public void run() {
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
		while (v<=(int)(10*2*Math.PI)) {
			v+=ia;
		//	if()
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (int b=0;b<ventana.getBoton().length;b++) {
				a[b]+=ia;
				x[b]=(int)(Math.cos(a[b])*50);
				System.out.println(ventana.getBoton()[b].getX()+x[b]);
				y[b]=(int)(Math.sin(a[b])*25);
				ventana.getBoton()[b].setLocation(pix[b]+x[b]*2,piy[b]+y[b]*2);		
				ventana.setLocation(vx+x[1]*5, vy+y[1]*5);
			}
		}
			for (int b=0;b<ventana.getBoton().length;b++)
				ventana.getBoton()[b].setLocation(pix[b],piy[b]);
		}		
	}

