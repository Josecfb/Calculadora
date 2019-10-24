package controlador;
import java.awt.Color;



import vista.Ventana;

public class Colores  implements Runnable{
	private Ventana ventana;
	private Thread hilo;
	private boolean pausa;

	public Colores(Ventana ventana,Boolean pausa) {
		this.ventana=ventana;
		this.pausa=pausa;
		hilo=new Thread(this);
		hilo.start();
		//hilo.suspend();
	}

	@SuppressWarnings("deprecation")
	public synchronized void pausar() {
			hilo.suspend();
	}
	@SuppressWarnings("deprecation")
	public synchronized void reanudar() {
		hilo.resume();
	}
	
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
			r[bo]=(int)(Math.random()*(254-abajo)+abajo);
			g[bo]=(int)(Math.random()*(254-abajo)+abajo);
			b[bo]=(int)(Math.random()*(254-abajo)+abajo);
			ir[bo]=(int)(Math.random()*2);
			ig[bo]=(int)(Math.random()*2);
			ib[bo]=(int)(Math.random()*2);
			if (ir[bo]==0) ir[bo]=1;else ir[bo]=-1;
			if (ig[bo]==0) ig[bo]=1;else ig[bo]=-1;
			if (ib[bo]==0) ib[bo]=1;else ib[bo]=-1;
		}
		int c=0;
		while (true) {
			c++;
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("pausa"+pausa);
				synchronized (this) {
				while (pausa) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				}

				for (int bo=0;bo<ventana.getBoton().length;bo++) {				
					r[bo]+=ir[bo];
					g[bo]+=ig[bo];
					b[bo]+=ib[bo];
					if(c==20) {
						ir[bo]=(int)(Math.random()*2);
						ig[bo]=(int)(Math.random()*2);
						ib[bo]=(int)(Math.random()*2);
						if (ir[bo]==0) ir[bo]=1;else ir[bo]=-1;
						if (ig[bo]==0) ig[bo]=1;else ig[bo]=-1;
						if (ib[bo]==0) ib[bo]=1;else ib[bo]=-1;
						c=0;
					}
					if (r[bo]>253 || r[bo]<abajo) ir[bo]*=-1;
					if (g[bo]>253|| g[bo]<abajo) ig[bo]*=-1;
					if (b[bo]>253|| b[bo]<abajo) ib[bo]*=-1;
					//System.out.println(bo+" "+ir[bo]+" "+ig[bo]+" "+ib[bo]+" "+r[bo]+" "+g[bo]+" "+b[bo]);
					ventana.getBoton()[bo].setBackground(new Color(r[bo],g[bo],b[bo]));
					ventana.getCajaTexto().setBackground(new Color(r[5], g[4], b[8]));
					ventana.getContentPane().setBackground(new Color(r[1]-99,g[2]-99,b[3]-99));
					ventana.getBarra().setBackground(new Color(r[10],g[11],b[12]));
					ventana.getEstandar().setBackground(new Color(r[10],g[11],b[12]));
					ventana.getCientifica().setBackground(new Color(r[10],g[11],b[12]));
					ventana.getProgramador().setBackground(new Color(r[10],g[11],b[12]));
				}
			}
	}

	public void setPausa(boolean pausa) {
		this.pausa = pausa;
	}

}
