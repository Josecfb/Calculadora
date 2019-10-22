package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import vista.Ventana;

public class ManejadorTeclado implements KeyListener{
	private Ventana ventana;
	
	
	public ManejadorTeclado(Ventana ventana) {
		super();
		this.ventana = ventana;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
//		if (e.paramString().contains("Intro"))
//			//System.out.println(e.getKeyChar());
//		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (int b=0;b<ventana.getBoton().length-1;b++)
			if(e.getKeyChar()==ventana.getBoton()[b].getName().charAt(0))
				ventana.getBoton()[b].doClick();
		System.out.println(e.getKeyChar());
		
	}

}
