package controlador;
import java.awt.Color;



import vista.Ventana;

public class Colores  implements Runnable{
	private Ventana ventana;
	private Thread hilo;
	//private boolean pausa;

	public Colores(Ventana ventana,Boolean pausa) {
		this.ventana=ventana;
		//this.pausa=pausa;
		hilo=new Thread(this);
		hilo.start();
		//hilo.suspend();
	}
	//-----------------------------------------------------
	//Hilo que cambia el color de los elementos de forma gradual por incrementos de rgb para cada objeto
	//---------------------------------------------------------
	@Override
	public void run() {
		int abajo=180;
		int[] r =new int[ventana.getBoton().length];
		int[] g =new int[ventana.getBoton().length];
		int[] b =new int[ventana.getBoton().length];
		int[] ir=new int[ventana.getBoton().length];
		int[] ig =new int[ventana.getBoton().length];
		int[] ib =new int[ventana.getBoton().length];
		for (int bo=0;bo<ventana.getBoton().length;bo++) {
			r[bo]=ventana.getBoton()[bo].getBackground().getRed();
			g[bo]=ventana.getBoton()[bo].getBackground().getGreen();
			b[bo]=ventana.getBoton()[bo].getBackground().getBlue();
			ir[bo]=(int)(Math.random()*2);
			ig[bo]=(int)(Math.random()*2);
			ib[bo]=(int)(Math.random()*2);
			if (ir[bo]==0) ir[bo]=1;else ir[bo]=-1;
			if (ig[bo]==0) ig[bo]=1;else ig[bo]=-1;
			if (ib[bo]==0) ib[bo]=1;else ib[bo]=-1;
		}
		int[] rb =new int[ventana.getlBase().length];
		int[] gb =new int[ventana.getlBase().length];
		int[] bb =new int[ventana.getlBase().length];
		int[] irb=new int[ventana.getlBase().length];
		int[] igb =new int[ventana.getlBase().length];
		int[] ibb =new int[ventana.getlBase().length];
		
		for (int ba=0;ba<ventana.getlBase().length;ba++) {
			rb[ba]=ventana.getlBase()[ba].getBackground().getRed();
			gb[ba]=ventana.getlBase()[ba].getBackground().getGreen();
			bb[ba]=ventana.getlBase()[ba].getBackground().getBlue();
			irb[ba]=(int)(Math.random()*2);
			igb[ba]=(int)(Math.random()*2);
			ibb[ba]=(int)(Math.random()*2);
			if (irb[ba]==0) ir[ba]=1;else ir[ba]=-1;
			if (igb[ba]==0) ig[ba]=1;else ig[ba]=-1;
			if (ibb[ba]==0) ib[ba]=1;else ib[ba]=-1;
		}
		while (true) {
				try {
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int bo=0;bo<ventana.getBoton().length;bo++) {				
					r[bo]+=ir[bo];
					g[bo]+=ig[bo];
					b[bo]+=ib[bo];
					if (r[bo]>253 || r[bo]<abajo) ir[bo]*=-1;
					if (g[bo]>253|| g[bo]<abajo) ig[bo]*=-1;
					if (b[bo]>253|| b[bo]<abajo) ib[bo]*=-1;
					ventana.getBoton()[bo].setBackground(new Color(r[bo],g[bo],b[bo]));
				}
				for (int bo=0;bo<ventana.getlBase().length;bo++) {				
					rb[bo]+=irb[bo];
					gb[bo]+=igb[bo];
					bb[bo]+=ibb[bo];
					if (rb[bo]>253|| rb[bo]<abajo) irb[bo]*=-1;
					if (gb[bo]>253|| gb[bo]<abajo) igb[bo]*=-1;
					if (bb[bo]>253|| bb[bo]<abajo) ibb[bo]*=-1;
					ventana.getlBase()[bo].setBackground(new Color(rb[bo],gb[bo],bb[bo]));
				}
				ventana.getCajaTexto().setBackground(new Color(r[5], g[4], b[8]));
				ventana.getContentPane().setBackground(new Color(r[1]-99,g[2]-99,b[3]-99));
				ventana.getBarra().setBackground(new Color(r[10],g[11],b[12]));
				ventana.getEstandar().setBackground(new Color(r[10],g[11],b[12]));
				ventana.getCientifica().setBackground(new Color(r[10],g[11],b[12]));
				ventana.getProgramador().setBackground(new Color(r[10],g[11],b[12]));
			}
	}
	//esto era un intento de pausar el hilo pero no lo consegui
//	public void setPausa(boolean pausa) {
//		this.pausa = pausa;
//	}
	//remedios chapu
	@SuppressWarnings("deprecation")
	public synchronized void pausar() {
			hilo.suspend();
	}
	@SuppressWarnings("deprecation")
	public synchronized void reanudar() {
		hilo.resume();
	}
}
