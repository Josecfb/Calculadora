package controlador;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import vista.Ventana;

public class ManejadorVentana implements WindowListener {
	private Ventana ventana;

	
	public ManejadorVentana(Ventana ventana) {
		this.ventana = ventana;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	//Al cerrar la ventana Pide confirmación--------------------------------
	@Override
	public void windowClosing(WindowEvent e) {
		int respuesta=JOptionPane.showConfirmDialog(ventana.getAviso(), "Realmentre deseas salir", "Terminar",0);
		if (respuesta==0) {
			new Pronuncia("sayonara");
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			double w=326,he=410;
			int px=ventana.getX();
			int py=ventana.getY();
			 do{
				 px-=0.8;
				 py-=0.5;
				he--;
				w-=0.795;
				ventana.setSize((int)w,(int)he);
				ventana.setLocation(px, py);
			}while (he>0);
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
